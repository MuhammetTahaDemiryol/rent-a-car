package com.tahademiryol.rentacar.business.dto.requests.create;

import com.tahademiryol.rentacar.common.constants.Regex;
import com.tahademiryol.rentacar.common.utils.annotations.NotFutureYear;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
    private int modelId;
    @Min(1998)
    @NotFutureYear
    private int modelYear;
    @Min(1)
    private double dailyPrice;

    // 01-81, A-AA-AAA, 0000
    @Pattern(regexp = Regex.Plate, message = "Invalid Licence Plate Code")
    private String plate;

}
