package com.hshen.album.repository;

import com.hshen.album.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
    User save(User user);

    User findByEmail(String email);

    User findById(String id);

    User findByName(String name);
}
