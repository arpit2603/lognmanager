package com.lognmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenCtrl {
	
	@PostMapping("/update")
	public void updateToken() {
	}
	
	@GetMapping("/get")
	public void getToken() {
	}
	
	@GetMapping("/delete")
	public void deleteToken() {
	}

}
