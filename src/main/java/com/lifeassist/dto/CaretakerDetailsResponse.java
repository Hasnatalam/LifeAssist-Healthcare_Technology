package com.lifeassist.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaretakerDetailsResponse {
	private Long id;
    private int age;
    private String bloodGroup;
    private String bp;
    @Builder.Default
    private List<CaretakerDiseaseResponse> diseases = new ArrayList<>();
}
