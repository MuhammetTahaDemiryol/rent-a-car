package com.tahademiryol.rentacar.business.concretes;

import com.tahademiryol.rentacar.business.abstracts.CarService;
import com.tahademiryol.rentacar.business.abstracts.MaintenanceService;
import com.tahademiryol.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.GetAllMaintenanceResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.GetCarResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import com.tahademiryol.rentacar.entities.concretes.Maintenance;
import com.tahademiryol.rentacar.entities.enums.State;
import com.tahademiryol.rentacar.repository.abstracts.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;

    @Override
    public List<GetAllMaintenanceResponse> getAll() {
        List<Maintenance> maintenances = repository.findAll();
        return maintenances
                .stream()
                .map(maintenance -> mapper.map(maintenance, GetAllMaintenanceResponse.class))
                .toList();
    }

    @Override
    public GetMaintenanceResponse get(int id) {
        checkIfMaintenanceExist(id);
        Maintenance maintenance = repository.findById(id).orElseThrow();
        return mapper.map(maintenance, GetMaintenanceResponse.class);
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(0);

        int carId = request.getCarId();
        checkIfCarCanBeSentToMaintenance(carId);
        repository.save(maintenance);

        changeCarStatus(carId, State.MAINTENANCE);

        return mapper.map(maintenance, CreateMaintenanceResponse.class);
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        checkIfMaintenanceExist(id);
        Maintenance beforeMaintenance = repository.findById(id).orElseThrow();
        changeCarStatus(beforeMaintenance.getCar().getId(), State.AVAILABLE);

        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(id);
        changeCarStatus(maintenance.getCar().getId(), State.MAINTENANCE);

        return mapper.map(repository.save(maintenance), UpdateMaintenanceResponse.class);
    }

    @Override
    public void delete(int id) {
        checkIfMaintenanceExist(id);
        Maintenance maintenance = repository.findById(id).orElseThrow();
        changeCarStatus(maintenance.getCar().getId(), State.AVAILABLE);

    }


    // Business rules
    private void checkIfMaintenanceExist(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("Maintenance Id does not exist!");
    }


    private void checkIfCarCanBeSentToMaintenance(int carId) {
        checkIfCarInMaintenance(carId);
        checkIfCarRented(carId);
    }


    private void checkIfCarInMaintenance(int carId) {
        GetCarResponse car = carService.get(carId);
        if (car.getState() == State.MAINTENANCE) throw new RuntimeException("Car is already in maintenance!");
    }

    private void checkIfCarRented(int carId) {
        GetCarResponse car = carService.get(carId);
        if (car.getState() == State.RENTED) throw new RuntimeException("Car is rented!");
    }

    private void changeCarStatus(int carId, State state) {
        carService.changeCarStatus(carId, state);
    }
}

