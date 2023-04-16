package com.tahademiryol.rentacar.business.abstracts;

import com.tahademiryol.rentacar.business.dto.requests.create.CreateBrandRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdateBrandRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreateBrandResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Brand.GetAllBrandsResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Brand.GetBrandResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Model.GetAllModelsResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateBrandResponse;

import java.util.List;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();

    GetBrandResponse getById(int id);

    CreateBrandResponse add(CreateBrandRequest request);

    UpdateBrandResponse update(int id, UpdateBrandRequest request);

    void delete(int id);

    List<GetAllModelsResponse> showModels(int id);

    //@Configuration
    //@Service
    //@Repository
    //@Component

    // @Bean -- method
}


