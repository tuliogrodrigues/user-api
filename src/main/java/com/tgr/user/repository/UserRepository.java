package com.tgr.user.repository;

import com.tgr.user.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by trodrigues on 2/4/16.
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
}
