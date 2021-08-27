package com.example.InfraStructure.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.InfraStructure.dto.UserDeleteRequestDto;
import com.example.InfraStructure.dto.UserRequestDto;
import com.example.InfraStructure.dto.UserResponseDto;
import com.example.InfraStructure.dto.UserUpdateRequestDto;
import com.example.InfraStructure.entity.Users;
import com.example.InfraStructure.exception.ApiRequestException;
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
	
	public Users findOne(String username) {
		Users user;
		if(!userRepository.existsByUsername(username)) {
			throw new ApiRequestException("User does not exist");
		}else {
			user = userRepository.findByUsername(username);
		}
		return user;
	}
	
	public List<UserResponseDto> findAll(){
		List<Users> userList;
		List<UserResponseDto> userResponseList = new ArrayList<>();
		if(userRepository.count() == 0) {
			throw new ApiRequestException("There are no users");
		}else {
			userList = userRepository.findAll();
			for(Users user : userList) {
				UserResponseDto userResponseDto = new UserResponseDto(user);
				userResponseList.add(userResponseDto);
			}
		}
		return userResponseList;
	}
	
	public Users updateUser(UserUpdateRequestDto userUpdateRequestDto) {
		Users user;
		if(!userRepository.existsByUsername(userUpdateRequestDto.getUsername())) {
			throw new ApiRequestException("User does not exist");
		}else {
			user = userRepository.findByUsername(userUpdateRequestDto.getUsername());
		}
		
		if(!userUpdateRequestDto.getPrevious_pass().equals(user.getPassword())) {
			throw new ApiRequestException("Previous password does not match");
		}else {
			user.updateUser(userUpdateRequestDto);
		}
		return userRepository.save(user);
	}
	
	public String deleteUser(UserDeleteRequestDto userDeleteRequestDto) {
		Users user;
		String username;
		if(!userRepository.existsByUsername(userDeleteRequestDto.getUsername())) {
			throw new ApiRequestException("User does not exist");
		}else {
			user = userRepository.findByUsername(userDeleteRequestDto.getUsername());
			username = user.getUsername();
		}
		if(!userDeleteRequestDto.getPassword().equals(user.getPassword())) {
			throw new ApiRequestException("Previous password does not match");
		}else {
			userRepository.delete(user);
		}
		
		return username;
	}
	
}