package com.camilasoares.cursomc.services.exception;

public class FileException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FileException(String msg){
        super();
    }

    public FileException(String msg, Throwable cause){
        super(msg,cause);
    }
}
