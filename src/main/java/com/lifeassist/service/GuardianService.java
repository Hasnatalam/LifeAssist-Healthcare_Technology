package com.lifeassist.service;

import org.springframework.stereotype.Service;

import com.lifeassist.entity.Address;
import com.lifeassist.entity.User;
import com.lifeassist.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuardianService {


	private final AddressRepository addressRepository;
		
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
