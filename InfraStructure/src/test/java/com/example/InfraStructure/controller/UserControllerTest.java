package com.example.InfraStructure.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.InfraStructure.dto.UserDeleteRequestDto;
import com.example.InfraStructure.dto.UserRequestDto;
import com.example.InfraStructure.dto.UserResponseDto;
import com.example.InfraStructure.dto.UserUpdateRequestDto;
import com.example.InfraStructure.dto.UsernameRequestDto;
import com.example.InfraStructure.entity.Users;
import com.example.InfraStructure.repository.UserRepository;
import com.example.InfraStructure.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		for(int i = 0; i < 3; i++) {
			String username = "lsjc12911" + i;
			String password = "1234";
			String email = "1234@naver.com";
			String role_pass = "";
			Users user = new Users(username, password, email, role_pass);
			userRepository.save(user);
		}
	}

	@AfterEach
	void tearDown() {
		userRepository.deleteAll();
	}
	
	@DisplayName("Delete user - Normal input")
	@Test
	void deleteUser() throws Exception {
		String username = "lsjc129111";
		String password = "1234";
		
		UserDeleteRequestDto deleteRequest = new UserDeleteRequestDto(username, password);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String userInfo = objectMapper.writeValueAsString(deleteRequest);
		
		mockMvc.perform(delete("/api/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userInfo)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
		
		assertEquals(false, (userRepository.existsByUsername(username)));
	}
	
	
	@DisplayName("Update user - Normal input")
	@Test
	void updateUser() throws Exception {
		String username = "lsjc129111";
		String password = "1234";
		String change_password ="12345";
		String change_email = "12345@naver.com";
		
		UserUpdateRequestDto userUpdate = new UserUpdateRequestDto(username, password, change_password, change_email);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String userInfo = objectMapper.writeValueAsString(userUpdate);
		
		mockMvc.perform(put("/api/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userInfo)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
		
		Users user = userRepository.findByUsername(username);
		assertEquals(change_password, user.getPassword());
	}
	
	@DisplayName("Get user - Specific")
	@Test
	void getOneUser() throws Exception {
		String username = "lsjc129111";
		UsernameRequestDto usernameRequestDto = new UsernameRequestDto();
		usernameRequestDto.setUsername(username);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String userInfo = objectMapper.writeValueAsString(usernameRequestDto);
		
		 MvcResult mvcResult = mockMvc.perform(post("/api/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userInfo)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
		
		UserResponseDto userResponseDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserResponseDto.class);
		
		Users user = userRepository.findByUsername(username);
		
		assertEquals(userResponseDto.getUsername(), user.getUsername());
	}
	
	@DisplayName("Get users - All")
	@Test
	void getAllUsers() throws Exception {

        List<Users> userList = userRepository.findAll();

        MvcResult mvcResult = mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andReturn();
        
        ObjectMapper objectMapper = new ObjectMapper();
        List<Users> userReturnedList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Users>>() {});
        
        assertEquals(userList.size(),userReturnedList.size());

        List<Users> found = userRepository.findAll();

        for(int i = 0; i < found.size(); i++){
            assertEquals(userReturnedList.get(i).getUsername(), found.get(i).getUsername());
        }
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
		
		mockMvc.perform(post("/api/signup")
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
		
		mockMvc.perform(post("/api/signup")
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
		
		mockMvc.perform(post("/api/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userInfo)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
		
		Users user = userRepository.findByUsername(username);
		String getUsername = user.getUsername();
		assertEquals(getUsername, username);
	}
	
}