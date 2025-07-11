package com.deepanshu.simpleserver.services.redisDB;

import java.util.Optional;

//import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserAuthData, String>{
	Optional<UserAuthData>findById(String username);
}
