package com.deepanshu.simpleserver.database.mongoDB;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.deepanshu.simpleserver.database.redisDB.UserAuthData;

public interface mongoRepo extends MongoRepository<MuserData, String> {
	Optional<MuserData>findByName(String username);
}
