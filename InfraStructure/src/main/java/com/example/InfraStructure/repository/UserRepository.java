package com.example.InfraStructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.InfraStructure.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long>  {
}
