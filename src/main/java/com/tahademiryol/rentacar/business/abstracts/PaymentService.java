package com.tahademiryol.rentacar.business.abstracts;

import com.tahademiryol.rentacar.business.dto.requests.create.CreatePaymentRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdatePaymentRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreatePaymentResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Payment.GetAllPaymentsResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Payment.GetPaymentResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdatePaymentResponse;
import com.tahademiryol.rentacar.common.dto.CreateRentalPaymentRequest;

import java.util.List;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();
    GetPaymentResponse getById(int id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    UpdatePaymentResponse update(int id, UpdatePaymentRequest request);
    void delete(int id);
    void processRentalPayment(CreateRentalPaymentRequest request);
}
