package com.example.InfraStructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	UserService userService;
	
	@PostMapping("api/user")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto){
        Users users = userService.createUser(userRequestDto);
        UserResponseDto userResponseDto = new UserResponseDto(users);
        return ResponseEntity.ok().body(userResponseDto);
    }
	
}
