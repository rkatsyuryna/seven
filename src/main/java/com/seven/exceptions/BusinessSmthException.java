package com.seven.exceptions;

/**
 * Created by ruslankatsyuryna on 6/27/17.
 */
public class BusinessSmthException extends RuntimeException {
    public BusinessSmthException () {};

    public BusinessSmthException(String message) {
        super(message);
    }
}
