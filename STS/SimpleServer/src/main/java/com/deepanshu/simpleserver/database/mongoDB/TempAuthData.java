package com.deepanshu.simpleserver.database.mongoDB;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TempAuthData extends CrudRepository<TempUserAuthData, String>{
	Optional<TempUserAuthData>findByUsername(String username);
}
