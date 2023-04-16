package com.tahademiryol.rentacar.business.abstracts;

import com.tahademiryol.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Maintenance.GetAllMaintenanceResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Maintenance.GetMaintenanceResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;

import java.util.List;

public interface MaintenanceService {
    List<GetAllMaintenanceResponse> getAll();

    GetMaintenanceResponse getById(int id);

    GetMaintenanceResponse returnCarFromMaintenance(int carId);

    CreateMaintenanceResponse add(CreateMaintenanceRequest request);

    UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request);

    void delete(int id);

}