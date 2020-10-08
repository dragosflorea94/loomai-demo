package com.loomai.demo.exception;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    PROFILE_NOT_FOUND("User profile not found for given id");

    private String msg;

    ErrorMessage(String msg) {
        this.msg = msg;
    }

}
