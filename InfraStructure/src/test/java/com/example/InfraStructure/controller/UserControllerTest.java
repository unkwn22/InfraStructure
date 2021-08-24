package com.example.InfraStructure.controller;

import java.util.List;

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
	MockMvc mockMvc;
	
	String authentication;
	
	@BeforeEach
	void setup() {
		String username = "jason";
		String password = "1234";
		String email = "abc";
		String role_pass1 = "jason272k";
		String role_pass2 = "";
		int intSize = 4;
		Users user = null;
		for(int i = 0; i < intSize; i++) {
			if(i % 2 == 0) {
				user = new Users((username + i), password, (email+i+"@naver.com"), role_pass1);
			}else {
				user = new Users((username + i), password, (email+i+"@naver.com"), role_pass2);
			}
			userRepository.save(user);
		}
		authentication = user.getAuthentication();
	}

	@AfterEach
	void tearDown() {
		userRepository.deleteAll();
	}
	
	@DisplayName("Checking roles - Validating authentication password")
	@Test
	void test() throws JsonProcessingException {
		
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.(productList.get(0).getProductId());
		userRequestDto.setUsername("Grace");
		userRequestDto.setPassword("1234");
		userRequestDto.setEmail("grace@naver.com");
		userRequestDto.setRole_password("jason272k");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String userInfo = objectMapper.writeValueAsString(userRequestDto);
		
		mockMvc.perform(post("/api/user")
                .content(userRequestDto)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(authenticated());
		
	}
}