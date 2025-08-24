package com.lifeassist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lifeassist.entity.Address;
import com.lifeassist.entity.User;
import com.lifeassist.service.GuardianService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/guardian")
@RequiredArgsConstructor
public class GuardianController {
	private final GuardianService guardianService;
	
	@GetMapping("/check")
	public String check() {
		return "Welcome to Guardian Controller";
	}

	
	@PostMapping("/addAddress")
	public ResponseEntity<String> addAddress(@RequestBody Address address, Authentication authentication){
		User user= (User)authentication.getPrincipal();
		Address savedAddress = guardianService.addAddress(address, user);
		return ResponseEntity.ok("Caretaker details saved "+user.getEmail()+" "+savedAddress.getLocality()+" "+savedAddress.getArea()
		+" "+savedAddress.getDistrict()+" "+savedAddress.getState()+" "+savedAddress.getCountry()+" "+savedAddress.getPinCode());
	}
	
}
