package com.tahademiryol.rentacar.business.abstracts;

import com.tahademiryol.rentacar.business.dto.requests.create.CreateRentalRequest;
import com.tahademiryol.rentacar.business.dto.requests.update.UpdateRentalRequest;
import com.tahademiryol.rentacar.business.dto.responses.create.CreateRentalResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Rental.GetAllRentalsResponse;
import com.tahademiryol.rentacar.business.dto.responses.get.Rental.GetRentalResponse;
import com.tahademiryol.rentacar.business.dto.responses.update.UpdateRentalResponse;

import java.util.List;

public interface RentalService {
    List<GetAllRentalsResponse> getAll();
    GetRentalResponse getById(int id);
    CreateRentalResponse add(CreateRentalRequest request);
    UpdateRentalResponse update(int id, UpdateRentalRequest request);
    void delete(int id);
}
