package com.lognmanager.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


@Service
public class CommonService {
	
	public String getEncryptedPassword(String inputValue) {
		if(inputValue==null || inputValue.trim().length()==0){
			return "";
		}
		String encryptedValue = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hashValue = md.digest(inputValue.getBytes(StandardCharsets.UTF_8));
			StringBuilder builder = new StringBuilder();
			for (byte b : hashValue) {
				builder.append(String.format("%02x", b));
			}
			encryptedValue = builder.toString();
		} catch (NoSuchAlgorithmException e) {
			
		}
		return encryptedValue;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray getErrors(BindingResult bindResult) {
		List<FieldError> errorList = bindResult.getFieldErrors();
		JSONArray errors = new JSONArray();
		for(FieldError fieldError : errorList) {
			JSONObject error = new JSONObject();
			error.put("name", fieldError.getField());
			error.put("message", fieldError.getDefaultMessage());
			errors.add(error);
		}
		return errors;
	}

}
