package com.deepanshu.simpleserver.database.mongoDB;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface mongoRepo extends MongoRepository<MuserData, String> {
	Optional<MuserData>findByName(String username);

	void deleteByName(String name);
}
