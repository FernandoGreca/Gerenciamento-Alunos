package com.teste.consultaescola.exceptions;

public class CriptoExistException extends Exception{
    
    private static final long serialVersionUID = 1L;

    public CriptoExistException(String msg) {
        super(msg);
    }
}
