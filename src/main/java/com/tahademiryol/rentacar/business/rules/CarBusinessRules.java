package com.tahademiryol.rentacar.business.rules;

import com.tahademiryol.rentacar.repository.abstracts.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final CarRepository repository;

    // Business rules
    public void checkIfCarExists(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("No such a car!");
    }
}
