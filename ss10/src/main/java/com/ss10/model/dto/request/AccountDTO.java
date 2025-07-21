package com.ss10.model.dto.request;

import com.ss10.model.enums.AccountStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {

    @Size(min = 1, message = "Họ tên không được để trống")
    private String fullName;

    @Size(min = 12, max = 12, message = "CCCD phải có đúng 12 chữ số")
    private String cccd;

    @Email(message = "Email không hợp lệ")
    private String email;

    @Pattern(
            regexp = "^0(3[2-9]|5[6|8|9]|7[06-9]|8[1-5]|8[8-9]|9[0-9])[0-9]{7}$",
            message = "Số điện thoại không hợp lệ"
    )
    private String phone;

    @PositiveOrZero(message = "Số tiền phải lớn hơn hoặc bằng 0")
    private Double money;

    private AccountStatus status;
}
