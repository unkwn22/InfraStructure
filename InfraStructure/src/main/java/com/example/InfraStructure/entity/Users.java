package com.example.InfraStructure.entity;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;
import javax.validation.constraints.Email;

import com.example.InfraStructure.entity.Time.TimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
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
	
	public void updateUser() {
		
	}
	
}
