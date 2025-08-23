package com.lifeassist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/caretaker")
@RequiredArgsConstructor
public class CaretakerController {

	@GetMapping("/check")
	public String check() {
		return "Welcome to Caretaker Controller";
	}
	
	
	
}
