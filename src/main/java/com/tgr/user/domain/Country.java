package com.tgr.user.domain;

/**
 * Created by trodrigues on 1/19/16.
 */
public enum Country {

    BR("Brazil"),
    US("United States");

    private String name;

    Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
