package com.lifeassist.service;

import org.springframework.stereotype.Service;

import com.lifeassist.entity.Address;
import com.lifeassist.entity.CaregiverDetails;
import com.lifeassist.entity.User;
import com.lifeassist.repository.AddressRepository;
import com.lifeassist.repository.CaregiverRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CaregiverService {
	
	private final CaregiverRepository caregiverRepository;
	private final AddressRepository addressRepository;
	
	public CaregiverDetails saveCaregiverDetails(CaregiverDetails caregiverDetails, User user) {
		
		CaregiverDetails caregiverDetailsBuilder = CaregiverDetails.builder()
				.age(caregiverDetails.getAge())
				.experienceYears(caregiverDetails.getExperienceYears())
				.specialization(caregiverDetails.getSpecialization())
				.user(user)
				.build();
	

		return caregiverRepository.save(caregiverDetailsBuilder);
	}
	
	public Address addAddress(Address address, User user) {
		Address addressBuilder = Address.builder()
				.locality(address.getLocality())
				.area(address.getArea())
				.district(address.getDistrict())
				.state(address.getState())
				.country(address.getCountry())
				.pinCode(address.getPinCode())
				.user(user)
				.build();
		
		return addressRepository.save(addressBuilder);
	}

}
