package com.tgr.user.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by trodrigues on 12/24/15.
 */
@FeignClient("users")
public interface UserClient {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    String getUser();
}
