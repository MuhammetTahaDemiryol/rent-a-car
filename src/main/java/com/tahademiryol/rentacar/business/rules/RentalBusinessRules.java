package com.tahademiryol.rentacar.business.rules;

import com.tahademiryol.rentacar.common.constants.Messages;
import com.tahademiryol.rentacar.core.exceptions.BusinessException;
import com.tahademiryol.rentacar.entities.enums.State;
import com.tahademiryol.rentacar.repository.abstracts.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository repository;

    // Business rules
    public void checkIfRentalExists(int id) {
        if (!repository.existsById(id))
            throw new BusinessException(Messages.Rental.NotExists);
    }

    public void checkIfCarAvailable(State state) {
        if (!state.equals(State.AVAILABLE)) {
            throw new BusinessException(Messages.Car.NotAvailable);
        }

    }
}
