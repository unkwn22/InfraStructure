package com.example.InfraStructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.InfraStructure.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long>  {
	Users findByUsername(String username);
	boolean existsByUsername(String username);
}
