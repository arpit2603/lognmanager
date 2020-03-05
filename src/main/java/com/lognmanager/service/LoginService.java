package com.lognmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lognmanager.dto.LoginDto;
import com.lognmanager.model.Login;
import com.lognmanager.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	LoginRepository lgnRepo;
	
	public void getLoginDetails(LoginDto login) {
		Login loginInfo = lgnRepo.findByUserName(login.getUserName());
		System.out.println(loginInfo.getUserName());
	}

}
