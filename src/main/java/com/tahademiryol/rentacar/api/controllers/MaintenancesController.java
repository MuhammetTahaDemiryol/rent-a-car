package com.tahademiryol.rentacar.api.controllers;


import com.tahademiryol.rentacar.business.abstracts.MaintenanceService;
import com.tahademiryol.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.GetAllMaintenanceResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/maintenance")
public class MaintenancesController {
    private final MaintenanceService service;

    @GetMapping("/")
    public List<GetAllMaintenanceResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetMaintenanceResponse getById(@PathVariable int id) {
        return service.get(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMaintenanceResponse add(@RequestBody CreateMaintenanceRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateMaintenanceResponse update(@PathVariable int id, @RequestBody UpdateMaintenanceRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}


