package com.tahademiryol.rentacar.business.rules;

import com.tahademiryol.rentacar.repository.abstracts.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceBusinessRules {
    private final InvoiceRepository repository;

    // Business rules
    public void checkIfInvoiceExist(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("Invoice Id does not exist!");
    }
}
