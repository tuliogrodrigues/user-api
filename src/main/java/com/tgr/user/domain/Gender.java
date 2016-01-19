package com.tgr.user.domain;

/**
 * Created by trodrigues on 1/19/16.
 */
public enum Gender {

    M("Male"),
    F("Female"),
    U("Undefined");

    private String description;

    Gender(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
