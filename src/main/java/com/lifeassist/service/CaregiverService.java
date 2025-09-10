package com.lifeassist.service;

import org.springframework.stereotype.Service;

import com.lifeassist.dto.CaregiverDetailsRequest;
import com.lifeassist.dto.CaregiverDetailsResponse;
import com.lifeassist.entity.CaregiverDetails;
import com.lifeassist.entity.User;
import com.lifeassist.repository.CaregiverRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CaregiverService {
	
	private final CaregiverRepository caregiverRepository;
	
	public CaregiverDetailsResponse saveCaregiverDetails(CaregiverDetailsRequest caregiverDetailsReq, User user) {
		
		CaregiverDetails caregiverDetails = CaregiverDetails.builder()
				.age(caregiverDetailsReq.getAge())
				.experienceYears(caregiverDetailsReq.getExperienceYears())
				.specialization(caregiverDetailsReq.getSpecialization())
				.fee(caregiverDetailsReq.getFee())
				.user(user)
				.build();
	

		CaregiverDetails savedCaregiverDetails = caregiverRepository.save(caregiverDetails);
		
		
		CaregiverDetailsResponse caregiverDetailsRes = CaregiverDetailsResponse.builder()
				.id(savedCaregiverDetails.getId())
				.age(savedCaregiverDetails.getAge())
				.specialization(savedCaregiverDetails.getSpecialization())
				.experienceYears(savedCaregiverDetails.getExperienceYears())
				.fee(savedCaregiverDetails.getFee())
				.build();
		
		return caregiverDetailsRes;
	}
	

}
