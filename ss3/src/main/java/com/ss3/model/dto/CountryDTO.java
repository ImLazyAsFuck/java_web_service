package com.ss3.model.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO{
    private Long id;

    @NotBlank(message = "Country name is required")
    private String name;

    @NotBlank(message = "Continental is required")
    private String continental;

    List<CityDTO> cities;
}
