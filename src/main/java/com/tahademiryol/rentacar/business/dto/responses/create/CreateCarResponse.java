package com.tahademiryol.rentacar.business.dto.responses.create;

import com.tahademiryol.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCarResponse {
    private int id;
    private int modelId;
    private double dailyPrice;
    private int modelYear;
    private String plate;
    private State state;
}
