package com.tahademiryol.rentacar.business.dto.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateRentalRequest {
    private int carId;
    private int rentedForDays;
    private double dailyPrice;
    private LocalDateTime startDate;

}
