package com.aris.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aris.global.service.SecondService;

@RestController
@RequestMapping("/wallet")
public class SecondController {

	@Autowired
	private SecondService service;

	@GetMapping
	public ResponseEntity<Object> testingRemoteCalls() {
		String result = service.invokeRemoteService();
		return ResponseEntity.status(200).body(result+" Got from Second Service");
	}
}
