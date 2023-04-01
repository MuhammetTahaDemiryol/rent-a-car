package com.tahademiryol.rentacar.api.controllers;

import com.tahademiryol.rentacar.business.abstracts.CarService;
import com.tahademiryol.rentacar.business.dto.requests.create.CreateCarRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreateCarResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.GetAllCarsResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.GetCarResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateAvailableCarResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateCarResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateMaintenanceCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cars")
public class CarsController {
    private final CarService service;

    @GetMapping
    public List<GetAllCarsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/{state}")
    public List<GetAllCarsResponse> getAllByState(@PathVariable String state) {
        return service.getAllByState(state);
    }

    @GetMapping("/{id}")
    public GetCarResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCarResponse add(@RequestBody CreateCarRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateCarResponse update(@PathVariable int id, @RequestBody UpdateCarRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);

    }

    @PutMapping("/update-maintenance/{id}")
    public UpdateMaintenanceCarResponse maintain(@PathVariable int id){
        return service.maintenance(id);
    }

    @PutMapping("/update-availability/{id}")
    public UpdateAvailableCarResponse makeAvailable(@PathVariable int id){
        return service.makeAvailable(id);
    }

}
