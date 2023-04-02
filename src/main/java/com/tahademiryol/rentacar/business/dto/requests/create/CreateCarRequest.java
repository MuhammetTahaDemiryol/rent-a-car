package com.tahademiryol.rentacar.business.dto.requests.create;

import com.tahademiryol.rentacar.entities.enums.State;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCarRequest {
    private int modelYear;
    private String plate;
    @Enumerated(EnumType.STRING)
    private State state; //  Available, Rented,  Maintenance
    private double dailyPrice;
    private int modelId;

}
