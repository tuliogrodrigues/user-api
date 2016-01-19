package com.tgr.user.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * Created by trodrigues on 10/31/15.
 */
@RestController
public class UserController implements UserClient {

    public String getUser() {
        return "{name:'foo', lastName: 'boo'}";
    }
}
