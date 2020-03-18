package com.lognmanager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lognmanager.request.dto.LoginRegisterReqDto;
import com.lognmanager.request.dto.LoginReqDto;
import com.lognmanager.response.dto.AppResponse;
import com.lognmanager.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginCtrl {
	
	@Autowired
	LoginService lgnService;
	
	@PostMapping("/request")
	@ResponseBody
	public ResponseEntity<?> loginRequest(@RequestBody@Valid LoginReqDto login , HttpServletRequest request , BindingResult bindResult ) {
		if(bindResult.hasErrors()) {
			return new ResponseEntity<AppResponse>(lgnService.getErrors(bindResult),HttpStatus.OK);
		}
		return new ResponseEntity<AppResponse>(lgnService.getLoginDetails(login , request),HttpStatus.OK);
	}
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<?> addDetails(@RequestBody LoginRegisterReqDto loginDetails) {
		return new ResponseEntity<AppResponse>(lgnService.addLoginDetails(loginDetails),HttpStatus.OK);
	}
	
	@PostMapping("/update")
	@ResponseBody
	public ResponseEntity<?> updateDetails(@RequestBody LoginRegisterReqDto loginDetails) {
		return new ResponseEntity<AppResponse>(lgnService.updateLoginDetails(loginDetails),HttpStatus.OK);
	}
	
	@GetMapping("/test")
	@ResponseBody
	public ResponseEntity<?> testMessage() {
		return new ResponseEntity<AppResponse>(lgnService.testMessage(),HttpStatus.OK);
	}

}
