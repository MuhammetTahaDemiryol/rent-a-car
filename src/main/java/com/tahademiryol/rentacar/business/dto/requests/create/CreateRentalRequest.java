package com.tahademiryol.rentacar.business.dto.requests.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRentalRequest {
    private int carId;
    private int rentedForDays;
    private double dailyPrice;
}