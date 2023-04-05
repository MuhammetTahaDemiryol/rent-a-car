package com.tahademiryol.rentacar.business.dto.responses.get.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllModelsResponse {
    private int id;
    private int brandId;
    private String name;

}
