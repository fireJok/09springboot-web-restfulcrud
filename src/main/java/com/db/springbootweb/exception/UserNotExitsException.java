package com.db.springbootweb.exception;

public class UserNotExitsException extends RuntimeException {
    public UserNotExitsException() {
        super("用户不存在");
    }
}
