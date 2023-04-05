package com.tahademiryol.rentacar.business.dto.responses.get.Brand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetBrandResponse {
    private int id;
    private String name;
}

