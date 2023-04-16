package com.tahademiryol.rentacar.business.rules;

import com.tahademiryol.rentacar.common.constants.Messages;
import com.tahademiryol.rentacar.common.dto.CreateRentalPaymentRequest;
import com.tahademiryol.rentacar.core.exceptions.BusinessException;
import com.tahademiryol.rentacar.repository.abstracts.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentBusinessRules {
    private final PaymentRepository repository;

    // Business rules
    public void checkIfPaymentIsValid(CreateRentalPaymentRequest request) {
        if (!repository.existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
                request.getCardNumber(),
                request.getCardholder(),
                request.getCardExpirationYear(),
                request.getCardExpirationMonth(),
                request.getCardCvv()
        )) {
            throw new BusinessException(Messages.Payment.NotAValidPayment);
        }
    }

    public void checkIfCardExists(String cardNumber) {
        if (repository.existsByCardNumber(cardNumber)) {
            throw new BusinessException(Messages.Payment.CardNumberAlreadyExists);
        }
    }

    public void checkIfPaymentExists(int id) {
        if (repository.existsById(id)) {
            throw new BusinessException(Messages.Payment.NotFound);
        }
    }

    public void checkIfBalanceIsEnough(double price, double balance) {
        if (balance < price) {
            throw new BusinessException(Messages.Payment.NotEnoughMoney);
        }
    }
}
