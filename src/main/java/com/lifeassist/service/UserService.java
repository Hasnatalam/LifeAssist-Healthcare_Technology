package com.lifeassist.service;

import org.springframework.stereotype.Service;

import com.lifeassist.dto.AddressRequest;
import com.lifeassist.dto.AddressResponse;
import com.lifeassist.entity.Address;
import com.lifeassist.entity.User;
import com.lifeassist.repository.AddressRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserService {
	private final AddressRepository addressRepository;
	
	public AddressResponse addAddress(AddressRequest addressReq, User user) {
		
		addressRepository.resetPrimaryForUser(user);
		
		Address address = Address.builder()
				.locality(addressReq.getLocality())
				.area(addressReq.getArea())
				.district(addressReq.getDistrict())
				.state(addressReq.getState())
				.country(addressReq.getCountry())
				.pinCode(addressReq.getPinCode())
				.user(user)
				.isPrimary(true)
				.build();
		Address savedAddress = addressRepository.save(address);
		
		AddressResponse addressRes = AddressResponse.builder()
				.id(savedAddress.getId())
				.locality(savedAddress.getLocality())
				.area(savedAddress.getArea())
				.district(savedAddress.getDistrict())
				.state(savedAddress.getState())
				.country(savedAddress.getCountry())
				.pinCode(savedAddress.getPinCode())
				.isPrimary(savedAddress.isPrimary())
				.build();
		
		return addressRes;
	}

}
