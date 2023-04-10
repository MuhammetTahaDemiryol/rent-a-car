package com.tahademiryol.rentacar.business.dto.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    @NotNull
    @Min(value = 2023)
    private int cardExpirationYear;

    @NotNull
    @Max(value = 12)
    @Min(value = 1)
    private int cardExpirationMonth;

    @NotBlank(message = "Card Number is not validated.")
    @Length(min = 16, max = 16, message = "Must be 16 characters long.")
    private String cardNumber;

    @NotBlank
    @Length(min = 5)
    private String cardHolder;

    @NotBlank
    @Length(min = 3, max = 3)
    private String cardCvv;
}
