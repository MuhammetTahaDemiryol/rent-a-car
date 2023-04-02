package com.tahademiryol.rentacar.business.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllMaintenanceResponse {
    private int id;
    private Timestamp sentDate;
    private Timestamp returnDate;
    private int carId;
}
