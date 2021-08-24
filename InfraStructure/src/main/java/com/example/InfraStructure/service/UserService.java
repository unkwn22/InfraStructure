package com.example.InfraStructure.service;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.InfraStructure.dto.UserRequestDto;
import com.example.InfraStructure.entity.Users;
import com.example.InfraStructure.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(@Lazy UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public Users createUser(UserRequestDto userRequestDto) {
		
		Users user = new Users(userRequestDto.getUsername(), userRequestDto.getPassword()
				, userRequestDto.getEmail(), userRequestDto.getRole_password());
		
		return userRepository.save(user);
	}
}