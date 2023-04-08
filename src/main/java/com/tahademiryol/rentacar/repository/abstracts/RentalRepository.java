package com.tahademiryol.rentacar.repository.abstracts;

import com.tahademiryol.rentacar.entities.concretes.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
}
