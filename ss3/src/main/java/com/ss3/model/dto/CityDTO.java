package com.ss3.model.dto;

import com.ss3.model.entity.Season;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

    private Long id;

    @NotBlank(message = "City name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @NotNull(message = "Country ID is required")
    private Long countryId;

    @NotNull(message = "Season is required")
    private String season;

    @Min(value = 1, message = "Area must be positive")
    private float area;

    @Min(value = 1, message = "Population must be positive")
    private int population;

}
