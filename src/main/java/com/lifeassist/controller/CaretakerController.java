package com.lifeassist.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lifeassist.dto.BookingRequest;
import com.lifeassist.dto.BookingResponse;
import com.lifeassist.dto.CaregiverResponse;
import com.lifeassist.dto.CaretakerDetailsRequest;
import com.lifeassist.dto.CaretakerDetailsResponse;
import com.lifeassist.dto.ResponseMessage;
import com.lifeassist.entity.User;
import com.lifeassist.service.CaretakerService;
import com.lifeassist.utility.Constants;

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
	public ResponseEntity<ResponseMessage> saveCaretakerDetails(@RequestBody CaretakerDetailsRequest caretakerDetailsReq,Authentication authentication){
		User user= (User)authentication.getPrincipal();
		CaretakerDetailsResponse saveCaretakerDetails = caretakerService.saveCaretakerDetails(caretakerDetailsReq, user);
		
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Caretaker details saved",saveCaretakerDetails));
	}
	@GetMapping("/getAllCaretaker")
	public List<CaregiverResponse> getAllCaretakers(Authentication authentication){
		User user= (User)authentication.getPrincipal();
		
		
		return caretakerService.getAllCaregiver(user);
	}
	
	@PostMapping("/book")
	public ResponseEntity<ResponseMessage> bookCaregiver(@RequestBody BookingRequest bookingRequest, Authentication authentication){
		User user= (User)authentication.getPrincipal();
		BookingResponse bookedCaregiver = caretakerService.bookCaregiver(user, bookingRequest);
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Caretaker details saved",bookedCaregiver));
	}
	
}
