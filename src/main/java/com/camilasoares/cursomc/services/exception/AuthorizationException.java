package com.camilasoares.cursomc.services.exception;

public class AuthorizationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AuthorizationException(String msg){
        super();
    }

    public AuthorizationException(String msg, Throwable cause){
        super(msg,cause);
    }
}
