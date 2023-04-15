package com.tahademiryol.rentacar.business.abstracts;

import com.tahademiryol.rentacar.business.dto.requests.create.CreateInvoiceRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdateInvoiceRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreateInvoiceResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Invoice.GetAllInvoicesResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Invoice.GetInvoiceResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateInvoiceResponse;

import java.util.List;

public interface InvoiceService {
    List<GetAllInvoicesResponse> getAll();
    GetInvoiceResponse getById(int id);
    CreateInvoiceResponse add(CreateInvoiceRequest request);
    UpdateInvoiceResponse update(int id, UpdateInvoiceRequest request);
    void delete(int id);
}
