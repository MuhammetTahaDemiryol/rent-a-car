package com.tahademiryol.rentacar.business.rules;

import com.tahademiryol.rentacar.repository.abstracts.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    private final ModelRepository repository;

    // Business rules
    public void checkIfModelExists(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("No such a Model!");
    }
}
