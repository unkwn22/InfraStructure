package com.example.InfraStructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.InfraStructure.dto.UserRequestDto;
import com.example.InfraStructure.entity.Users;
import com.example.InfraStructure.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public Users createUser (UserRequestDto userRequestDto) {
		
		
		
		return userRepository.save(null);
	}
	
}
