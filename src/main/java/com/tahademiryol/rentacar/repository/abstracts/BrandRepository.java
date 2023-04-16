package com.tahademiryol.rentacar.repository.abstracts;

import com.tahademiryol.rentacar.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

//CRUD operations, manages the database
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    //custom queries
    boolean existsByNameIgnoreCase(String name);
}
