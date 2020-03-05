package com.lognmanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lognmanager.model.Login;

@Repository
public interface LoginRepository extends CrudRepository<Login , String>{
	
	public Login findByUserName(String userName); 
}
