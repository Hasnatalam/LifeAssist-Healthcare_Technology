package com.lifeassist.controller;

import java.net.HttpURLConnection;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lifeassist.dto.CaregiverDetailsRequest;
import com.lifeassist.dto.CaregiverDetailsResponse;
import com.lifeassist.dto.ResponseMessage;
import com.lifeassist.entity.User;
import com.lifeassist.service.CaregiverService;
import com.lifeassist.utility.Constants;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/caregiver")
@RequiredArgsConstructor
public class CaregiverController {
	private final CaregiverService careGiverService;
	

	@PostMapping("/saveDetails")
	public ResponseEntity<ResponseMessage> saveCaretakerDetails(@RequestBody CaregiverDetailsRequest caregiverDetails,Authentication authentication){
		User user= (User)authentication.getPrincipal();
		
		CaregiverDetailsResponse savesaveCaregiverDetails = careGiverService.saveCaregiverDetails(caregiverDetails, user);
		
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Caretaker details saved", savesaveCaregiverDetails));
	}
	

	
}
