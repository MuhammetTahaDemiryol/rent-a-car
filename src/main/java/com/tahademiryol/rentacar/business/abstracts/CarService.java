package com.tahademiryol.rentacar.business.abstracts;

import com.tahademiryol.rentacar.business.dto.requests.create.CreateCarRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreateCarResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.GetAllCarsResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.GetCarResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateCarResponse;

import java.util.List;

public interface CarService {

    List<GetAllCarsResponse> getAll();
    GetCarResponse getById(int id);
    CreateCarResponse add(CreateCarRequest request);
    UpdateCarResponse update(int id, UpdateCarRequest request);
    void delete(int id);

    //@Configuration
    //@Service
    //@Repository
    //@Component

    // @Bean -- method
}
