package com.ss10.controller;

import com.ss10.model.dto.request.*;
import com.ss10.model.dto.response.APIResponse;
import com.ss10.model.dto.response.AccountResponseDTO;
import com.ss10.service.account.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<APIResponse<AccountResponseDTO>> create(@RequestBody @Valid AccountDTO dto) {
        var created = accountService.createAccount(dto);
        return new ResponseEntity<>(
                new APIResponse<>(created, "Tạo tài khoản thành công", 201),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<AccountResponseDTO>> update(
            @PathVariable UUID id,
            @RequestBody @Valid AccountDTO dto
    ) {
        var updated = accountService.updateAccount(id, dto);
        return ResponseEntity.ok(
                new APIResponse<>(updated, "Cập nhật tài khoản thành công", 200)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> delete(@PathVariable UUID id) {
        accountService.deleteAccount(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new APIResponse<>(null, "Đóng tài khoản thành công", 204));
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<AccountResponseDTO>>> getAll() {
        var list = accountService.getAllAccounts();
        return ResponseEntity.ok(new APIResponse<>(list, "Lấy danh sách tài khoản thành công", 200));
    }

    @GetMapping("/cccd/{cccd}")
    public ResponseEntity<APIResponse<AccountResponseDTO>> getByCccd(@PathVariable String cccd) {
        var account = accountService.getByCccd(cccd);
        return ResponseEntity.ok(new APIResponse<>(account, "Tìm thấy tài khoản", 202));
    }
}
