package com.example.InfraStructure.controller;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.InfraStructure.dto.UserRequestDto;
import com.example.InfraStructure.entity.Users;
import com.example.InfraStructure.repository.UserRepository;
import com.example.InfraStructure.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MockMvc mockMvc;
	
	
	@BeforeEach
	void setup() {
		
	}

	@AfterEach
	void tearDown() {
		userRepository.deleteAll();
	}
	
	@DisplayName("Checking roles - input admin password")
	@Test
	void validateAdmin() throws Exception {
		
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setUsername("Grace");
		userRequestDto.setPassword("1234");
		userRequestDto.setEmail("grace@naver.com");
		userRequestDto.setRole_password("jason272k");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String userInfo = objectMapper.writeValueAsString(userRequestDto);
		
		mockMvc.perform(post("/api/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userInfo)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
		
		String expectRole = "ADMIN";
		Users user = userRepository.findByUsername(userRequestDto.getUsername());
		String getRole = user.getRoles();
		assertEquals(expectRole, getRole);
	}
	
	@DisplayName("Checking roles - input nothing")
	@Test
	void validateUser() throws Exception {
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setUsername("Grace");
		userRequestDto.setPassword("1234");
		userRequestDto.setEmail("grace@naver.com");
		userRequestDto.setRole_password("");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String userInfo = objectMapper.writeValueAsString(userRequestDto);
		
		mockMvc.perform(post("/api/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userInfo)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
		
		String expectRole = "USER";
		Users user = userRepository.findByUsername(userRequestDto.getUsername());
		String getRole = user.getRoles();
		assertEquals(expectRole, getRole);
	}
	
	@DisplayName("Check create user - normal input")
	@Test
	void validateCreateUser()  throws Exception {
		
		String username = "Jason";
		String password = "1234";
		String email = "grace@naver.com";
		String role_password = "";
		
		UserRequestDto userRequestDto = new UserRequestDto(username, password, email, role_password);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String userInfo = objectMapper.writeValueAsString(userRequestDto);
		
		mockMvc.perform(post("/api/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userInfo)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
		
		Users user = userRepository.findByUsername(username);
		String getUsername = user.getUsername();
		assertEquals(getUsername, username);
	}
	
}