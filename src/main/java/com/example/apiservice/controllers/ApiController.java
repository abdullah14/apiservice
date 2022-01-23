package com.example.apiservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class ApiController {
	
	@GetMapping("hello")
	public String hello() {
		
		return  "Hello Azure World";
	}

}
