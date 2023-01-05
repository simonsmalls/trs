package com.example.trs.exceptions;

public class EndTimeBeforeStartTimeException extends Exception {
    public EndTimeBeforeStartTimeException(String message) {
        super(message);
    }
}
