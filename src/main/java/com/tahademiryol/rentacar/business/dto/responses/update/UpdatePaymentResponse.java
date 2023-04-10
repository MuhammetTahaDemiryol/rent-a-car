package com.tahademiryol.rentacar.business.dto.responses.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePaymentResponse {
    private int id;
    private int cardExpirationMonth;
    private int cardExpirationYear;
    private String cardNumber;
    private String cardHolder;
    private String cardCvv;
    private double balance;
}
