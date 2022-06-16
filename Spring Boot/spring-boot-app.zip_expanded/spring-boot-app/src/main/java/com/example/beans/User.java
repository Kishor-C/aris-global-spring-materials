package com.example.beans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id // to mention the property is mapped to primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // to use auto increment feature
	@Column(name = "user_id") // to mention the column name that maps to the variable
	private int userId;
	
	// no need to mention @Column because variable & column name is same
	private String name;
	
	// no need to mention @Column because variable & column name is same
	private LocalDate dob;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	
}
