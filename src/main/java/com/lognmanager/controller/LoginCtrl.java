package com.lognmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lognmanager.request.dto.LoginReqDto;
import com.lognmanager.response.dto.AppResponse;
import com.lognmanager.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginCtrl {
	
	@Autowired
	LoginService lgnService;
	
	@PostMapping("/request")
	public ResponseEntity<?> loginRequest(@RequestBody LoginReqDto login) {
		return new ResponseEntity<AppResponse>(lgnService.getLoginDetails(login),HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public void addDetails() {
		
	}
	
	@PostMapping("/update")
	public void updateDetails() {
		
	}

}
