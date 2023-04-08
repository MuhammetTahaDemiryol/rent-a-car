package com.tahademiryol.rentacar.business.dto.responses.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRentalResponse {
    private int id;
    private int carId;
    private int rentedForDays;
    private double dailyPrice;
    private double totalPrice;
    private LocalDateTime startDate;
}
