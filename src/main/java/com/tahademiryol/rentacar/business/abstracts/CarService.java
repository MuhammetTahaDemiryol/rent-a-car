package com.tahademiryol.rentacar.business.abstracts;

import com.tahademiryol.rentacar.business.dto.requests.create.CreateCarRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreateCarResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Car.GetAllCarsResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Car.GetCarResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Maintenance.GetAllMaintenanceResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateCarResponse;
import com.tahademiryol.rentacar.entities.enums.State;

import java.util.List;

public interface CarService {
    List<GetAllCarsResponse> getAll(boolean includeMaintenance);

    GetCarResponse getById(int id);

    CreateCarResponse add(CreateCarRequest request);

    UpdateCarResponse update(int id, UpdateCarRequest request);

    void delete(int id);

    public void changeState(int carId, State state);

    List<GetAllMaintenanceResponse> showMaintenances(int id);


    //@Configuration
    //@Service
    //@Repository
    //@Component

    // @Bean -- method
}
