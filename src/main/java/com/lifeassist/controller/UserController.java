package com.lifeassist.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lifeassist.dto.AddressRequest;
import com.lifeassist.dto.AddressResponse;
import com.lifeassist.dto.ResponseMessage;
import com.lifeassist.dto.UserDto;
import com.lifeassist.entity.User;
import com.lifeassist.service.UserAuthService;
import com.lifeassist.service.UserService;
import com.lifeassist.utility.Constants;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserAuthService userAuthService;
    private final UserService userService;
    

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userAuthService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userAuthService.getUserById(id));
    }
    
	@PostMapping("/addAddress")
	public ResponseEntity<ResponseMessage> addAddress(@RequestBody AddressRequest address, Authentication authentication){
		User user= (User)authentication.getPrincipal();
		AddressResponse savedAddress = userService.addAddress(address, user);
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Address saved sucessfully",savedAddress));
	}
}