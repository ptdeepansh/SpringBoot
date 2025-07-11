package com.deepanshu.simpleserver.services.mongoDB;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface mongoRepo extends MongoRepository<MuserData, String> {
//	user findByEmail(String email);
}
