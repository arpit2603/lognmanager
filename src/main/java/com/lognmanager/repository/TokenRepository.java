package com.lognmanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lognmanager.model.Token;

@Repository
public interface TokenRepository extends CrudRepository<Token , String> {
	
	public Token findByToken(String token);
}
