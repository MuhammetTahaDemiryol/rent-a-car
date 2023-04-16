package com.tahademiryol.rentacar.adapters;

import com.tahademiryol.rentacar.business.abstracts.PosService;
import com.tahademiryol.rentacar.common.constants.Messages;
import com.tahademiryol.rentacar.core.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakePosServiceAdapter implements PosService {
    @Override
    public void pay() {
        boolean isPaymentSuccessful = new Random().nextBoolean();
        if (!isPaymentSuccessful)
            throw new BusinessException(Messages.Payment.Failed);
    }
}
