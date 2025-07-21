package com.ss10.service.transactioncredit;

import com.ss10.model.dto.request.TransactionCreditRequestDTO;
import com.ss10.model.dto.response.TransactionCreditResponseDTO;
import com.ss10.model.entity.Account;
import com.ss10.model.entity.CreditCard;
import com.ss10.model.entity.TransactionCredit;
import com.ss10.model.enums.TransactionStatus;
import com.ss10.repo.AccountRepo;
import com.ss10.repo.CreditCardRepo;
import com.ss10.repo.TransactionCreditRepo;
import com.ss10.service.notification.NotificationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionCreditServiceImpl implements TransactionCreditService {

    private final TransactionCreditRepo transactionCreditRepo;
    private final CreditCardRepo creditCardRepo;
    private final AccountRepo accountRepo;
    private final MailService mailService;
    private final NotificationService notificationService;

    @Override
    @Transactional
    public TransactionCreditResponseDTO spendWithCreditCard(TransactionCreditRequestDTO request) {
        CreditCard card = creditCardRepo.findById(request.getCreditCardId())
                .orElseThrow(() -> new EntityNotFoundException("Credit card not found"));

        Account receiver = accountRepo.findById(request.getAccountReceiverId())
                .orElseThrow(() -> new EntityNotFoundException("Receiver account not found"));

        TransactionCredit tx = TransactionCredit.builder()
                .accountReceiver(receiver)
                .creditCardSender(card)
                .note(request.getNote())
                .money(request.getMoney())
                .status(TransactionStatus.IN_PROGRESS)
                .build();

        if (card.getAmountSpent() + request.getMoney() > card.getSpendingLimit()) {
            tx.setStatus(TransactionStatus.FAILED);
            transactionCreditRepo.save(tx);
            log.warn("Giao dịch thất bại: Vượt quá hạn mức thẻ.");
            return toResponseDTO(tx);
        }

        card.setAmountSpent(card.getAmountSpent() + request.getMoney());
        creditCardRepo.save(card);

        tx.setStatus(TransactionStatus.SUCCESSFULLY);
        transactionCreditRepo.save(tx);

        log.info("Giao dịch thành công: Thẻ {} -> Tài khoản {} | Số tiền: {}",
                card.getId(), receiver.getId(), request.getMoney());

        return toResponseDTO(tx);
    }

    @Override
    @Scheduled(cron = "0 0 12 20 * ?")
    @Transactional
    public void generateMonthlySpendingReport() {
        YearMonth currentMonth = YearMonth.now();
        LocalDate start = currentMonth.atDay(1);
        LocalDate end = currentMonth.atEndOfMonth();

        List<TransactionCredit> monthlyTransactions =
                transactionCreditRepository.findAllByCreatedAtBetweenAndStatus(
                        start.atStartOfDay(), end.atTime(LocalTime.MAX), TransactionStatus.THANH_CONG
                );

        Map<UUID, List<TransactionCredit>> groupedByCard = monthlyTransactions.stream()
                .collect(Collectors.groupingBy(tx -> tx.getCreditCardSender().getId()));

        for (var entry : groupedByCard.entrySet()) {
            UUID creditCardId = entry.getKey();
            List<TransactionCredit> txs = entry.getValue();

            double total = txs.stream().mapToDouble(TransactionCredit::getMoney).sum();
            CreditCard card = creditCardRepo.findById(creditCardId).orElse(null);

            if (card == null || card.getAccount() == null || card.getAccount().getEmail() == null) continue;

            String email = card.getAccount().getEmail();
            String fullName = card.getAccount().getFullName();

            notificationService.sendNotification(card.getAccount(), "Tổng chi tiêu tháng " +
                    currentMonth + " là " + total + " VNĐ");

            StringBuilder report = new StringBuilder();
            report.append("Xin chào ").append(fullName).append(",\n\n");
            report.append("Đây là báo cáo chi tiêu bằng thẻ tín dụng tháng ").append(currentMonth).append(":\n\n");

            for (TransactionCredit tx : txs) {
                report.append("- Ngày: ").append(tx.getCreatedAt()).append("\n");
                report.append("  Số tiền: ").append(tx.getMoney()).append(" VNĐ\n");
                report.append("  Ghi chú: ").append(tx.getNote()).append("\n\n");
            }

            report.append("Tổng chi tiêu: ").append(total).append(" VNĐ\n");
            report.append("Trân trọng.");

            mailService.sendEmail(email, "Báo cáo chi tiêu tháng " + currentMonth, report.toString());
        }

        log.info("Đã gửi báo cáo chi tiêu tháng " + currentMonth + " cho " + groupedByCard.size() + " khách hàng.");
    }

    private TransactionCreditResponseDTO toResponseDTO(TransactionCredit tx) {
        return TransactionCreditResponseDTO.builder()
                .id(tx.getId())
                .creditCardSenderId(tx.getCreditCardSender().getId())
                .accountReceiverId(tx.getAccountReceiver().getId())
                .money(tx.getMoney())
                .note(tx.getNote())
                .status(tx.getStatus())
                .build();
    }
}
