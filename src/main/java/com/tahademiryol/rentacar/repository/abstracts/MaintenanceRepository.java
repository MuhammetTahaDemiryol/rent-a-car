package com.tahademiryol.rentacar.repository.abstracts;

import com.tahademiryol.rentacar.entities.concretes.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {
    // @Nonnull
    // Maintenance findById(int id);
    Maintenance findMaintenanceByCarIdAndIsCompletedFalse(int carId);

    boolean existsByCarIdAndIsCompletedFalse(int carId);

}
