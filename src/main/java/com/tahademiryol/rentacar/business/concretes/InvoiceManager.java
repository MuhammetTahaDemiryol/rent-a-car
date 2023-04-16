package com.tahademiryol.rentacar.business.concretes;

import com.tahademiryol.rentacar.business.abstracts.InvoiceService;
import com.tahademiryol.rentacar.business.dto.requests.create.CreateInvoiceRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdateInvoiceRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreateInvoiceResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Invoice.GetAllInvoicesResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Invoice.GetInvoiceResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateInvoiceResponse;
import com.tahademiryol.rentacar.business.rules.InvoiceBusinessRules;
import com.tahademiryol.rentacar.entities.concretes.Invoice;
import com.tahademiryol.rentacar.repository.abstracts.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository repository;
    private final ModelMapper mapper;
    private final InvoiceBusinessRules rules;

    @Override
    public List<GetAllInvoicesResponse> getAll() {

        return repository.findAll().stream()
                .map(invoice -> mapper.map(invoice, GetAllInvoicesResponse.class))
                .toList();
    }

    @Override
    public GetInvoiceResponse getById(int id) {
        Invoice invoice = repository.findById(id).orElseThrow();
        return mapper.map(invoice, GetInvoiceResponse.class);
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest request) {
        Invoice invoice = mapper.map(request, Invoice.class);
        invoice.setId(0);
        invoice.setTotalPrice(getTotalPrice(invoice));
        repository.save(invoice);
        return mapper.map(invoice, CreateInvoiceResponse.class);
    }

    @Override
    public UpdateInvoiceResponse update(int id, UpdateInvoiceRequest request) {
        rules.checkIfInvoiceExist(id);
        Invoice invoice = mapper.map(request, Invoice.class);
        invoice.setId(id);
        repository.save(invoice);

        return mapper.map(invoice, UpdateInvoiceResponse.class);
    }

    @Override
    public void delete(int id) {
        rules.checkIfInvoiceExist(id);
        ;
        repository.deleteById(id);

    }


    private double getTotalPrice(Invoice invoice) {
        return invoice.getDailyPrice() * invoice.getRentedForDays();
    }


}
