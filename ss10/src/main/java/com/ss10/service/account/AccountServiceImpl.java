package com.ss10.service.account;

import com.ss10.model.mapper.AccountMapper;
import com.ss10.model.dto.request.AccountDTO;
import com.ss10.model.dto.response.AccountResponseDTO;
import com.ss10.model.entity.Account;
import com.ss10.model.enums.AccountStatus;
import com.ss10.repo.AccountRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;

    @Override
    public AccountResponseDTO createAccount(AccountDTO dto) {
        log.info("Tạo tài khoản mới với CCCD: {}", dto.getCccd());
        Account account = AccountMapper.toEntity(dto);
        Account saved = accountRepo.save(account);
        log.info("Tài khoản đã được tạo: {}", saved.getId());
        return AccountMapper.toDTO(saved);
    }

    @Override
    public AccountResponseDTO updateAccount(UUID id, AccountDTO dto) {
        Account existing = accountRepo.findById(id)
                .orElseThrow(() -> {
                    log.warn("Cập nhật thất bại - Không tìm thấy tài khoản ID: {}", id);
                    return new EntityNotFoundException("Không tìm thấy tài khoản");
                });

        log.info("Cập nhật tài khoản ID: {} - Trước cập nhật: {}", id, existing);
        log.info("Dữ liệu mới: {}", dto);

        AccountMapper.updateEntity(existing, dto);
        Account updated = accountRepo.save(existing);

        log.info("Cập nhật thành công tài khoản ID: {}", updated.getId());
        return AccountMapper.toDTO(updated);
    }


    @Override
    public void deleteAccount(UUID id) {
        Account acc = accountRepo.findById(id)
                .orElseThrow(() -> {
                    log.warn("Xóa thất bại - Không tìm thấy tài khoản ID: {}", id);
                    return new EntityNotFoundException("Không tìm thấy tài khoản");
                });

        log.warn("Đang đóng tài khoản ID: {}", id);
        acc.setStatus(AccountStatus.INACTIVE);
        accountRepo.save(acc);
        log.info("Tài khoản ID {} đã chuyển sang trạng thái INACTIVE", id);
    }

    @Override
    public List<AccountResponseDTO> getAllAccounts() {
        List<Account> list = accountRepo.findAll();
        log.info("Lấy danh sách tất cả tài khoản ({} kết quả)", list.size());
        return list.stream()
                .map(AccountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO getByCccd(String cccd) {
        Account account = accountRepo.findByCccd(cccd)
                .orElseThrow(() -> {
                    log.warn("Không tìm thấy tài khoản với CCCD: {}", cccd);
                    return new EntityNotFoundException("Không tìm thấy tài khoản CCCD: " + cccd);
                });

        log.info("Đã tìm thấy tài khoản với CCCD: {}", cccd);
        return AccountMapper.toDTO(account);
    }
}
