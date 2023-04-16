package com.tahademiryol.rentacar.business.rules;

import com.tahademiryol.rentacar.common.constants.Messages;
import com.tahademiryol.rentacar.core.exceptions.BusinessException;
import com.tahademiryol.rentacar.repository.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    private final BrandRepository repository;

    // Business rules
    public void checkIfBrandExists(int id) {
        if (!repository.existsById(id)) throw new BusinessException(Messages.Brand.NotExists);
    }

    public void checkIfBrandExistsByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) throw new BusinessException(Messages.Brand.Exists);
    }
}
