package com.ecutb.web.repositories;

import com.ecutb.web.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
