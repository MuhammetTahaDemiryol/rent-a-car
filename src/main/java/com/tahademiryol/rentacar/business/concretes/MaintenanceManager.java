package com.tahademiryol.rentacar.business.concretes;

import com.tahademiryol.rentacar.business.abstracts.CarService;
import com.tahademiryol.rentacar.business.abstracts.MaintenanceService;
import com.tahademiryol.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Maintenance.GetAllMaintenanceResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Maintenance.GetMaintenanceResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import com.tahademiryol.rentacar.entities.concretes.Maintenance;
import com.tahademiryol.rentacar.entities.enums.State;
import com.tahademiryol.rentacar.repository.abstracts.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public GetMaintenanceResponse getById(int id) {
        checkIfMaintenanceExist(id);
        Maintenance maintenance = repository.findById(id).orElseThrow();
        return mapper.map(maintenance, GetMaintenanceResponse.class);
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(int carId) {
        checkIfCarIsNotUnderMaintenance(carId);
        Maintenance maintenance = repository.findMaintenanceByCarIdAndIsCompletedFalse(carId);
        maintenance.setCompleted(true);
        maintenance.setEndDate(LocalDateTime.now());
        repository.save(maintenance);
        carService.changeState(carId, State.AVAILABLE);

        return mapper.map(maintenance, GetMaintenanceResponse.class);
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        checkCarAvailabilityForMaintenance(request.getCarId());
        checkIfCarUnderMaintenance(request.getCarId());
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(0);
        maintenance.setCompleted(false);
        maintenance.setStartDate(LocalDateTime.now());
        maintenance.setEndDate(null);

        carService.changeState(request.getCarId(), State.MAINTENANCE);
        repository.save(maintenance);

        return mapper.map(maintenance, CreateMaintenanceResponse.class);
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        checkIfMaintenanceExist(id);
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(id);
        repository.save(maintenance);

        return mapper.map(maintenance, UpdateMaintenanceResponse.class);
    }

    @Override
    public void delete(int id) {
        checkIfMaintenanceExist(id);
        makeCarAvailableIfIsCompletedFalse(id);
        repository.deleteById(id);

    }


    // Business rules
    private void checkIfMaintenanceExist(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("Maintenance Id does not exist!");
    }


    private void checkIfCarUnderMaintenance(int carId) {
        if (repository.existsByCarIdAndIsCompletedFalse(carId)) {
            throw new RuntimeException("Car is already in maintenance!");
        }

    }

    private void checkIfCarIsNotUnderMaintenance(int carId) {
        if (!repository.existsByCarIdAndIsCompletedFalse(carId)) {
            throw new RuntimeException("No such a car in maintenance!");
        }
    }

    private void checkCarAvailabilityForMaintenance(int carId) {
        if (carService.getById(carId).getState().equals(State.RENTED)) {
            throw new RuntimeException("Car is rented, can not be sent to maintenance!");
        }
    }

    private void makeCarAvailableIfIsCompletedFalse(int id){
        int carId = repository.findById(id).get().getCar().getId();
        if(repository.existsByCarIdAndIsCompletedFalse(carId))
            carService.changeState(carId, State.AVAILABLE);
    }
}

