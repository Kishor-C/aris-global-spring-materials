package com.example.service;

import java.time.LocalDate;
import java.util.List;

import com.example.beans.User;
import com.example.exceptions.UserNotFoundException;

/*
 * This provides the methods that involves in business layer
 */
public interface UserService {
	public User store(User user); // store & return the stored user
	public List<User> getAllUsers(); // return all the users in a List
	public User getUser(int id) throws UserNotFoundException; // return user based on id or throw exception
	public User updateDob(int id, LocalDate dob) throws UserNotFoundException; // returns user updated or throw exception
	public void deleteUser(int id) throws UserNotFoundException; // delete or throw exception if user is not found
}
