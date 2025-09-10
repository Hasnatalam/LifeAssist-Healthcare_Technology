package com.lifeassist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
    private String locality;
    private String area;
    private String district;
    private String state;
    private String country;
    private int pinCode;
}
