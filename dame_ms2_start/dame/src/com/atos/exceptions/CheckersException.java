package com.atos.exceptions;

public abstract class CheckersException extends Exception {
    protected int code;

    public CheckersException(int code) {

        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
