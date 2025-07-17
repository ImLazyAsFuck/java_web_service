package com.ss8.service.paymentslip;

import com.ss8.exception.custom.NoResourceFoundException;
import com.ss8.model.dto.PaymentSlipDTO;
import com.ss8.model.entity.PaymentSlip;
import com.ss8.repo.PaymentSlipRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentSlipServiceImpl implements PaymentSlipService {

    private final PaymentSlipRepo paymentSlipRepo;

    @Override
    public PaymentSlipDTO create(PaymentSlipDTO dto) {
        PaymentSlip slip = PaymentSlip.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .money(dto.getMoney())
                .createdAt(LocalDateTime.now())
                .build();
        return toDTO(paymentSlipRepo.save(slip));
    }

    @Override
    public PaymentSlipDTO update(Long id, PaymentSlipDTO dto) {
        PaymentSlip existing = paymentSlipRepo.findById(id)
                .orElseThrow(() -> new NoResourceFoundException("Không tìm thấy phiếu chi có id = " + id));

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setMoney(dto.getMoney());
        return toDTO(paymentSlipRepo.save(existing));
    }

    @Override
    public void delete(Long id) {
        PaymentSlip existing = paymentSlipRepo.findById(id)
                .orElseThrow(() -> new NoResourceFoundException("Không tìm thấy phiếu chi để xóa"));
        paymentSlipRepo.delete(existing);
    }

    @Override
    public List<PaymentSlipDTO> findAll() {
        return paymentSlipRepo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private PaymentSlipDTO toDTO(PaymentSlip entity) {
        return PaymentSlipDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .money(entity.getMoney())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
