package com.tahademiryol.rentacar.business.dto.requests.create;

import com.tahademiryol.rentacar.business.dto.requests.PaymentRequest;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest extends PaymentRequest {
    @Min(value = 1)
    private double balance;
}
