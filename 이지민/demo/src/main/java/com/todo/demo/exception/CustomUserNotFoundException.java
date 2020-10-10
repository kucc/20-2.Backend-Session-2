package com.todo.demo.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserNotFoundException extends RuntimeException {

    public CustomUserNotFoundException(String msg, Throwable t){
        super(msg,t);
    }
    public CustomUserNotFoundException(String msg){
        super(msg);
    }
    public CustomUserNotFoundException(){
        super();
    }
}
