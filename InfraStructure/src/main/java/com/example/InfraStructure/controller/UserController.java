package com.example.InfraStructure.controller;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.InfraStructure.dto.UserDeleteRequestDto;
import com.example.InfraStructure.dto.UserRequestDto;
import com.example.InfraStructure.dto.UserResponseDto;
import com.example.InfraStructure.dto.UserUpdateRequestDto;
import com.example.InfraStructure.dto.UsernameRequestDto;
import com.example.InfraStructure.entity.Message;
import com.example.InfraStructure.entity.Users;
import com.example.InfraStructure.service.UserService;

@RestController
public class UserController {
	private final UserService userService;
	
	public UserController(@Lazy UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/api/signup")
	public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
		Users user = userService.createUser(userRequestDto);
		UserResponseDto reponse = new UserResponseDto(user);
		return ResponseEntity.ok().body(reponse);
	}
	
	@PostMapping("/api/user")
	public ResponseEntity<UserResponseDto> findOne(@RequestBody UsernameRequestDto usernameRequestDto) {
		Users user = userService.findOne(usernameRequestDto.getUsername());
		UserResponseDto reponse = new UserResponseDto(user);
		return ResponseEntity.ok().body(reponse);
	}
	
	@GetMapping("/api/user")
	public ResponseEntity findAll(){
		List<UserResponseDto> userLists = userService.findAll();
		return ResponseEntity.ok().body(userLists);
	}
	
	@PutMapping("/api/user")
	public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserUpdateRequestDto userUpdateRequestDto){
		Users user = userService.updateUser(userUpdateRequestDto);
		UserResponseDto reponse = new UserResponseDto(user);
		return ResponseEntity.ok().body(reponse);
	}
	
	@DeleteMapping("/api/user")
	public ResponseEntity<Message> deleteUser(@RequestBody UserDeleteRequestDto userDeleteRequestDto){
		String username = userService.deleteUser(userDeleteRequestDto);
		Message message = Message.builder()
				.message("user: " + username + " has been delete successfully")
				.build();
		
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	
}