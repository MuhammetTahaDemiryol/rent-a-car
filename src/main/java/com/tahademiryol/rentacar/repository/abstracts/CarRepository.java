package com.tahademiryol.rentacar.repository.abstracts;

import com.tahademiryol.rentacar.entities.concretes.Car;
import com.tahademiryol.rentacar.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findAllByStateIsNot(State state);
}
