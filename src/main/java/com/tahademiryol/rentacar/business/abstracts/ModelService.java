package com.tahademiryol.rentacar.business.abstracts;

import com.tahademiryol.rentacar.business.dto.requests.create.CreateModelRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdateModelRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreateModelResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.GetAllCarsResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.GetAllModelsResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.GetModelResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateModelResponse;

import java.util.List;

public interface ModelService {

    List<GetAllModelsResponse> getAll();

    GetModelResponse getById(int id);

    CreateModelResponse add(CreateModelRequest request);

    UpdateModelResponse update(int id, UpdateModelRequest request);

    void delete(int id);

    List<GetAllCarsResponse> showCars(int id);

    //@Configuration
    //@Service
    //@Repository
    //@Component

    // @Bean -- method
}
