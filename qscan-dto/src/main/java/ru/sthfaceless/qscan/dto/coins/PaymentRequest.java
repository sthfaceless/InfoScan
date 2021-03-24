package ru.sthfaceless.qscan.dto.coins;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    private String id;

    private boolean anonymous;

    @NotNull
    private BillingMethod method;

    @Min(10)
    private long amount;

}
