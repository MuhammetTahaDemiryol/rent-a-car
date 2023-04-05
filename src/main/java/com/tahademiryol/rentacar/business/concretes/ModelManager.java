package com.tahademiryol.rentacar.business.concretes;

import com.tahademiryol.rentacar.business.abstracts.ModelService;
import com.tahademiryol.rentacar.business.dto.requests.create.CreateModelRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdateModelRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreateModelResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Car.GetAllCarsResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Model.GetAllModelsResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Model.GetModelResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateModelResponse;
import com.tahademiryol.rentacar.entities.concretes.Model;
import com.tahademiryol.rentacar.repository.abstracts.ModelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = repository.findAll();

        return models
                .stream()
                .map(model -> mapper.map(model, GetAllModelsResponse.class))
                .toList();
    }

    @Override
    public GetModelResponse getById(int id) {
        checkIfModelExists(id);
        Model model = repository.findById(id).orElseThrow();
        return mapper.map(model, GetModelResponse.class);
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        Model model = mapper.map(request, Model.class);
        model.setId(0);
        repository.save(model);
        return mapper.map(model, CreateModelResponse.class);
    }

    @Override
    public UpdateModelResponse update(int id, UpdateModelRequest request) {
        checkIfModelExists(id);
        Model model =  mapper.map(request, Model.class);
        model.setId(id);
        repository.save(model);
        return mapper.map(model, UpdateModelResponse.class);

    }


    @Override
    public void delete(int id) {
        checkIfModelExists(id);
        repository.deleteById(id);
    }

    @Override
    public List<GetAllCarsResponse> showCars(int id) {
        Model model = repository.findById(id).orElseThrow();
        return model.getCars().stream()
                .map(car -> mapper.map(car, GetAllCarsResponse.class)).toList();
    }

    // Business rules

    private void checkIfModelExists(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("No such a Model!");
    }
}
