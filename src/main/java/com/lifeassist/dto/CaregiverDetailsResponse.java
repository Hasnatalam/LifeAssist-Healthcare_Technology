package com.lifeassist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaregiverDetailsResponse {
	
	private Long id;
	private int experienceYears;
	private String specialization;
	private int age;
	private Double fee;
}
