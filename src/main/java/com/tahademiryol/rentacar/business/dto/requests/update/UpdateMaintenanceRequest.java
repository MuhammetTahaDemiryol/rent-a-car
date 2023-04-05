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
public class UpdateMaintenanceRequest {
    private int carId;
    private String information;
    private boolean isCompleted;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
