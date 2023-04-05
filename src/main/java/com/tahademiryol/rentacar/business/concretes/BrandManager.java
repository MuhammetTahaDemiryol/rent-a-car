package com.tahademiryol.rentacar.business.concretes;

import com.tahademiryol.rentacar.business.abstracts.BrandService;
import com.tahademiryol.rentacar.business.dto.requests.create.CreateBrandRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdateBrandRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreateBrandResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Brand.GetAllBrandsResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Brand.GetBrandResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Model.GetAllModelsResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateBrandResponse;
import com.tahademiryol.rentacar.entities.concretes.Brand;
import com.tahademiryol.rentacar.repository.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = repository.findAll();

        return brands.stream().map(brand -> mapper.map(brand, GetAllBrandsResponse.class)).toList();
    }

    @Override
    public GetBrandResponse getById(int id) {
        checkIfBrandExists(id);
        Brand brand = repository.findById(id).orElseThrow();
        return mapper.map(brand, GetBrandResponse.class);
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {
//        Brand brand = new Brand();
//        brand.setName(request.getName());
//        repository.save(brand);
//
//        CreateBrandResponse response = new CreateBrandResponse();
//        response.setId(brand.getId());
//        response.setName(brand.getName());
//
//        return response;
        Brand brand = mapper.map(request, Brand.class);
        brand.setId(0);
        repository.save(brand);
        return mapper.map(brand, CreateBrandResponse.class);
    }

    @Override
    public UpdateBrandResponse update(int id, UpdateBrandRequest request) {
        checkIfBrandExists(id);
        Brand brand = mapper.map(request, Brand.class);
        brand.setId(id);
        repository.save(brand);
        return mapper.map(brand, UpdateBrandResponse.class);
    }

    @Override
    public void delete(int id) {
        checkIfBrandExists(id);
        repository.deleteById(id);
    }

    // Business rules
    private void checkIfBrandExists(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("No such a brand!");
    }

    @Override
    public List<GetAllModelsResponse> showModels(int id) {
        Brand brand = repository.findById(id).orElseThrow();
        return brand.getModels().stream().map(model -> mapper.map(model, GetAllModelsResponse.class)).toList();
    }
}
