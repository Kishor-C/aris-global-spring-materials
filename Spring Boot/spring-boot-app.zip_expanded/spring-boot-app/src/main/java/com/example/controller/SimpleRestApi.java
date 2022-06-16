package com.example.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.User;
import com.example.exceptions.UserNotFoundException;
import com.example.service.UserService;

@RestController
@RequestMapping("/user")
public class SimpleRestApi {

	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<Object> storeUser(@RequestBody User user) {
		User user2 = service.store(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user2);
	}
	
	@GetMapping
	public ResponseEntity<Object> getUsers() {
		List<User> usersList = service.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(usersList);
	}
	
	@GetMapping("{userId}") // user/1, user/2, user/3
	public ResponseEntity<Object> getUser(@PathVariable("userId") int id) {
		try {
			User user = service.getUser(id);
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} catch (UserNotFoundException e) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
		}
	}
	@PutMapping("{userId}/{dob}")
	public ResponseEntity<Object> updateDob(@PathVariable("userId") int id, 
				@PathVariable("dob") String dob) {
		try {
			User user = service.updateDob(id, LocalDate.parse(dob));
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} catch (UserNotFoundException e) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
		}
	}
}
