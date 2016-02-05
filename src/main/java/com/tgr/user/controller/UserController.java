package com.tgr.user.controller;

import com.tgr.user.domain.User;
import com.tgr.user.domain.assembler.UserResource;
import com.tgr.user.domain.assembler.UserResourceAssembler;
import com.tgr.user.exceptions.BadRequestException;
import com.tgr.user.exceptions.NotFoundException;
import com.tgr.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by trodrigues on 10/31/15.
 */
@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserResourceAssembler userResourceAssembler;

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<UserResource> list() {

        logger.info("Retrieve all users");

        return userService
                .listAll()
                .stream()
                .map( u -> userResourceAssembler.toResource(u))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public UserResource get(@PathVariable String userId) {

        logger.info("Retrieve user data to id: " + userId);

        return userService
                .getById(userId)
                .map( u -> userResourceAssembler.toResource(u))
                .orElseThrow(new NotFoundException());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public UserResource create(@RequestBody User user) {

        logger.info("Creating user " + user);

        return userService
                .create(user)
                .map(u -> userResourceAssembler.toResource(u))
                .orElseThrow(new BadRequestException());
    }
}
