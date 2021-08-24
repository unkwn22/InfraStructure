package com.example.InfraStructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.InfraStructure.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>  {
	
}
