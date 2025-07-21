package com.ss10.model.dto.request;

import com.ss10.model.enums.CreditCardStatus;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCardDTO {

    @NotNull(groups = OnCreate.class)
    private UUID accountId;

    @NotNull(groups = {OnCreate.class, OnUpdateLimit.class})
    @Positive(message = "Hạn mức phải lớn hơn 0")
    private Double spendingLimit;

    @PositiveOrZero(message = "Số tiền đã chi tiêu phải >= 0")
    private Double amountSpent;

    @NotNull(groups = OnUpdateStatus.class)
    private CreditCardStatus status;

    public interface OnCreate {}
    public interface OnUpdateLimit {}
    public interface OnUpdateStatus {}
}
