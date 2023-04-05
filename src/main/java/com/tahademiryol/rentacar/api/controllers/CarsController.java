package com.tahademiryol.rentacar.api.controllers;

import com.tahademiryol.rentacar.business.abstracts.CarService;
import com.tahademiryol.rentacar.business.dto.requests.create.CreateCarRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreateCarResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Car.GetAllCarsResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Maintenance.GetAllMaintenanceResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Car.GetCarResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateCarResponse;
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
    public List<GetAllCarsResponse> getAll(@RequestParam(defaultValue = "false") boolean includeMaintenance) {
        return service.getAll(includeMaintenance);
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

    @GetMapping("/maintenances-of-{id}")
    public List<GetAllMaintenanceResponse> getModels(@PathVariable int id) {
        return service.showMaintenances(id);
    }
}
