package com.tgr.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

/**
 * Created by trodrigues on 2/4/16.
 */
@ResponseStatus(value= HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException implements Supplier<RuntimeException> {

    @Override
    public RuntimeException get() {
        return this;
    }
}
