package com.example.InfraStructure.controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.InfraStructure.dto.UserRequestDto;
import com.example.InfraStructure.dto.UserResponseDto;
import com.example.InfraStructure.entity.Users;
import com.example.InfraStructure.service.UserService;

@RestController
public class UserController {
	private final UserService userService;
	
	public UserController(@Lazy UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/api/user")
	public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
		Users user = userService.createUser(userRequestDto);
		UserResponseDto reponse = new UserResponseDto(user);
		return ResponseEntity.ok().body(reponse);
	}
}