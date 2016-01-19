package com.tgr.user.controller;

import com.tgr.user.domain.User;
import com.tgr.user.domain.assembler.UserResource;
import com.tgr.user.domain.assembler.UserResourceAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by trodrigues on 10/31/15.
 */
@RestController
public class UserController implements UserClient {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserResourceAssembler userResourceAssembler;

    public UserResource getUser() {
        User user = new User();
        user.setFirstName("Tulio");
        user.setLastName("Rodrigues");
        return userResourceAssembler.toResource(user);
    }

}
