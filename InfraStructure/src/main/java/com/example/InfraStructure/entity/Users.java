package com.example.InfraStructure.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

import com.example.InfraStructure.entity.Time.TimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users extends TimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "users_id")
	private Long userId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Email
	@Column(name = "email_address")
	private String email;
	
	@Column(name = "role")
	private String roles;
	
	@OneToMany
	@Builder.Default
	List<Post> posts = new ArrayList<>();
	
	@Transient
	private final String authentication = "jason272k";
	
	public Users(String username, String password, String email, String role_password) {
		this.username = username;
		this.password = password;
		this.email = email;
		
		if(role_password.equals(authentication)) {
			this.roles = "ADMIN";
		}else {
			this.roles = "USER";
		}
	} 
	
	public Users() {
		
	}
	
	public void updateUser() {
		
	}
	
	public String authenticateUser(String password) {
		if(password.equals(authentication)) {
			this.roles = "ADMIN";
		}else {
			this.roles = "USER";
		}
		return this.roles;
	}
	
}
