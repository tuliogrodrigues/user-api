package com.tgr.user.service;

import com.tgr.user.domain.User;
import com.tgr.user.exceptions.ConflictException;
import com.tgr.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * Created by trodrigues on 2/4/16.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(String id) {
        Assert.notNull(id, "Id cannot be null");
        return Optional.ofNullable(userRepository.findOne(id));
    }

    public Optional<User> getByEmail(String email) {
        Assert.notNull(email, "Email cannot be null");
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public Optional<User> create(User user) {

        if(getByEmail(user.getEmail()).isPresent()) {
            throw new ConflictException();
        }
        return Optional.ofNullable(userRepository.save(user));
    }
}
