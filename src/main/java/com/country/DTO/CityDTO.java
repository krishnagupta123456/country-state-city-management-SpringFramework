package com.country.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {
    private long id;
    private String name;
    private String code;
    private long population;
    private String country;
    private String state;
}
