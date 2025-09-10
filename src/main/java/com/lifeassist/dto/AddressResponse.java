package com.lifeassist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
	private Long id;
    private String locality;
    private String area;
    private String district;
    private String state;
    private String country;
    private boolean isPrimary;
    private int pinCode;
}
