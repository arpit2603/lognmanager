package com.lognmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lognmanager.request.dto.TokenExpireReq;
import com.lognmanager.request.dto.TokenReqDto;
import com.lognmanager.response.dto.AppResponse;
import com.lognmanager.service.TokenService;

@RestController
@RequestMapping("/token")
public class TokenCtrl {
	
	@Autowired
	TokenService tknService;
	
	@PostMapping("/update")
	@ResponseBody
	public ResponseEntity<?> updateToken(@RequestBody TokenReqDto tokenReq) {
		return new ResponseEntity<AppResponse>(tknService.updateToken(tokenReq) , HttpStatus.OK);
	}
	
	@GetMapping("/get")
	@ResponseBody
	public void getToken() {
	}
	
	@GetMapping("/delete/{token}")
	@ResponseBody
	public ResponseEntity<?> deleteToken(@PathVariable("token") String token) {
		return new ResponseEntity<AppResponse>(tknService.deleteToken(token),HttpStatus.OK);
	}
	
	@PostMapping("/expired")
	@ResponseBody
	public ResponseEntity<?> isExpired(@RequestBody TokenExpireReq tokenExpireReq) {
		return new ResponseEntity<AppResponse>(tknService.isExpired(tokenExpireReq),HttpStatus.OK);
	}

}
