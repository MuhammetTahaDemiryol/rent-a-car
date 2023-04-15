package com.tahademiryol.rentacar.business.dto.responses.get.Invoice;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetInvoiceResponse {
    @NotNull
    private int id;
    @NotNull
    private int rentalId;
    @NotBlank
    private String cardHolder;
    @NotBlank
    private String modelName;
    @NotBlank
    private String brandName;
    @NotBlank
    private String plate;
    @NotNull
    @Min(2006)
    private int modelYear;
    @NotNull
    @Min(0)
    private double dailyPrice;
    @NotNull
    @Min(0)
    private int rentedForDays;
}
