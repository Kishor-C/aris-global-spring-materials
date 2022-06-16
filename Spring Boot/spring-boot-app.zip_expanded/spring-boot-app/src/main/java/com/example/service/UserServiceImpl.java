package com.example.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.beans.User;
import com.example.dao.UserRepository;
import com.example.exceptions.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userDao;

	@Transactional
	@Override
	public User store(User user) {
		return userDao.save(user); // saves the entity & returns the saved entity
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public User getUser(int id) throws UserNotFoundException {
		Optional<User> optional = userDao.findById(id);
		User user = optional.orElse(null);
		if(user == null) {
			throw new UserNotFoundException("User with an id "+id+" not found!");
		}
		return user;
	}

	@Transactional
	@Override
	public User updateDob(int id, LocalDate dob) throws UserNotFoundException {
		User user = getUser(id);
		user.setDob(dob);
		return userDao.save(user);
	}

	@Override
	@Transactional
	public void deleteUser(int id) throws UserNotFoundException {
		User user = getUser(id);
		userDao.delete(user);
	}

}
