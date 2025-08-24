package com.lifeassist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lifeassist.entity.Address;
import com.lifeassist.entity.CaretakerDetails;
import com.lifeassist.entity.User;
import com.lifeassist.service.CaretakerService;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/caretaker")
@RequiredArgsConstructor
public class CaretakerController {
	private final CaretakerService caretakerService;

	@GetMapping("/check")
	public String check() {
		return "Welcome to Caretaker Controller";
	}
	@PostMapping("/saveDetails")
	public ResponseEntity<String> saveCaretakerDetails(@RequestBody CaretakerDetails caretakerDetails,Authentication authentication){
		User user= (User)authentication.getPrincipal();
		CaretakerDetails saveCaretakerDetails = caretakerService.saveCaretakerDetails(caretakerDetails, user);
		
		return ResponseEntity.ok("Caretaker details saved "+user.getEmail()+" "+saveCaretakerDetails.getAge()+" "+saveCaretakerDetails.getBloodGroup()+" "+saveCaretakerDetails.getBp() );
	}
	
	@PostMapping("/addAddress")
	public ResponseEntity<String> addAddress(@RequestBody Address address, Authentication authentication){
		User user= (User)authentication.getPrincipal();
		Address savedAddress = caretakerService.addAddress(address, user);
		return ResponseEntity.ok("Caretaker details saved "+user.getEmail()+" "+savedAddress.getLocality()+" "+savedAddress.getArea()
		+" "+savedAddress.getDistrict()+" "+savedAddress.getState()+" "+savedAddress.getCountry()+" "+savedAddress.getPinCode());
	}
	
	
}
