package com.example;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("secured-api")
public class MyRestApis {

	@PostMapping
	public String store() {
		return "Store API";
	}
	@GetMapping
	public String fetch() {
		return "Fetch API";
	}
	@PutMapping
	public String modify() {
		return "Modify API";
	}
	@DeleteMapping
	public String delete() {
		return "Delete API";
	}
}
