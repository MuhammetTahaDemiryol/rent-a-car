package com.tahademiryol.rentacar.repository.abstracts;

import com.tahademiryol.rentacar.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

//CRUD operations
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    //custom queries
}
