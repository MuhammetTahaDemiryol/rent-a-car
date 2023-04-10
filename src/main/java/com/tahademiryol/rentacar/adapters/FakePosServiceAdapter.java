package com.tahademiryol.rentacar.adapters;

import com.tahademiryol.rentacar.business.abstracts.PosService;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class FakePosServiceAdapter implements PosService {
    @Override
    public void pay() {
        boolean isPaymentSuccessful = new Random().nextBoolean();
        if(!isPaymentSuccessful)
            throw new RuntimeException("Payment is unsuccessful!");
    }
}
