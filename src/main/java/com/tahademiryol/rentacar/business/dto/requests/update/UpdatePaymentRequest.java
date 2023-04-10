package com.tahademiryol.rentacar.business.dto.requests.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePaymentRequest {
    @NotNull
    @Min(value = 1)
    private double balance;
}
