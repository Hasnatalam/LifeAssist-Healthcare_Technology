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
public class CaretakerDetailsRequest {
    private int age;
    private String bloodGroup;
    private String bp;
    @Builder.Default
    private List<CaretakerDiseaseRequest> diseases = new ArrayList<>();
}
