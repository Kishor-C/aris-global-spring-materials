package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.beans.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	
}
