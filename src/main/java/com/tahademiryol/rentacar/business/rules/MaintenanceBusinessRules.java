package com.tahademiryol.rentacar.business.rules;

import com.tahademiryol.rentacar.entities.enums.State;
import com.tahademiryol.rentacar.repository.abstracts.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MaintenanceBusinessRules {
    private final MaintenanceRepository repository;
    // Business rules

    public void checkIfMaintenanceExist(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("Maintenance Id does not exist!");
    }


    public void checkIfCarUnderMaintenance(int carId) {
        if (repository.existsByCarIdAndIsCompletedFalse(carId)) {
            throw new RuntimeException("Car is already in maintenance!");
        }

    }

    public void checkIfCarIsNotUnderMaintenance(int carId) {
        if (!repository.existsByCarIdAndIsCompletedFalse(carId)) {
            throw new RuntimeException("No such a car in maintenance!");
        }
    }

    public void checkCarAvailabilityForMaintenance(State state) {
        if (state.equals(State.RENTED)) {
            throw new RuntimeException("Car is rented, can not be sent to maintenance!");
        }
    }
}
