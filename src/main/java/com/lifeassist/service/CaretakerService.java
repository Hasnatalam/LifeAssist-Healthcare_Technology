package com.lifeassist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lifeassist.entity.Address;
import com.lifeassist.entity.CaretakerDetails;
import com.lifeassist.entity.CaretakerDisease;
import com.lifeassist.entity.User;
import com.lifeassist.repository.AddressRepository;
import com.lifeassist.repository.CaretakerDiseaseRepository;
import com.lifeassist.repository.CaretakerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CaretakerService {

	private final CaretakerRepository caretakerRepository;
	private final CaretakerDiseaseRepository caretakerDiseaseRepository;
	private final AddressRepository addressRepository;
	

	public CaretakerDetails saveCaretakerDetails(CaretakerDetails caretakerDetails, User user) {
		

		CaretakerDetails cd = CaretakerDetails.builder().age(caretakerDetails.getAge())
				.bloodGroup(caretakerDetails.getBloodGroup()).bp(caretakerDetails.getBp()).user(user).build();
		
		CaretakerDetails savedCD = caretakerRepository.save(cd);

		List<CaretakerDisease> diseases = caretakerDetails.getDiseases().stream()
				.map(d -> CaretakerDisease.builder().diseaseName(d.getDiseaseName()).caretakerDetails(savedCD).build())
				.collect(Collectors.toList());

		caretakerDiseaseRepository.saveAll(diseases);

		return savedCD;
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
