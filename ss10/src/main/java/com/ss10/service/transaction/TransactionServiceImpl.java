package com.ss10.service.transaction;

import com.ss10.model.entity.Notification;
import com.ss10.model.entity.Transaction;
import com.ss10.model.entity.Account;
import com.ss10.model.enums.NotificationStatus;
import com.ss10.model.enums.TransactionStatus;
import com.ss10.repo.NotificationRepo;
import com.ss10.repo.TransactionRepo;
import com.ss10.repo.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepo transactionRepo;
    private final NotificationRepo notificationRepo;
    private final AccountRepo accountRepo;
    private final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Override
    public Transaction create(Transaction t) {
        try {
            Account sender = accountRepo.findById(t.getSender().getId()).orElseThrow();
            Account receiver = accountRepo.findById(t.getReceiver().getId()).orElseThrow();

            if (sender.getMoney() < t.getMoney()) {
                t.setStatus(TransactionStatus.FAILED);
                t.setCreatedAt(LocalDateTime.now());
                transactionRepo.save(t);
                logger.error("Transaction failed: insufficient funds");
                return t;
            }

            sender.setMoney(sender.getMoney() - t.getMoney());
            receiver.setMoney(receiver.getMoney() + t.getMoney());

            accountRepo.save(sender);
            accountRepo.save(receiver);

            t.setStatus(TransactionStatus.SUCCESSFULLY);
            t.setCreatedAt(LocalDateTime.now());
            Transaction saved = transactionRepo.save(t);

            Notification n1 = Notification.builder()
                    .account(sender)
                    .content("Bạn đã chuyển " + t.getMoney() + " đến tài khoản " + receiver.getFullName() + ". Số dư còn lại: " + sender.getMoney())
                    .status(NotificationStatus.UNREAD)
                    .createdTime(LocalDateTime.now())
                    .build();

            Notification n2 = Notification.builder()
                    .account(receiver)
                    .content("Bạn đã nhận được " + t.getMoney() + " từ tài khoản " + sender.getFullName() + ". Số dư hiện tại: " + receiver.getMoney())
                    .status(NotificationStatus.UNREAD)
                    .createdTime(LocalDateTime.now())
                    .build();

            notificationRepo.saveAll(List.of(n1, n2));

            return saved;
        } catch (Exception e) {
            logger.error("Transaction error: ", e);
            t.setStatus(TransactionStatus.FAILED);
            t.setCreatedAt(LocalDateTime.now());
            return transactionRepo.save(t);
        }
    }
}