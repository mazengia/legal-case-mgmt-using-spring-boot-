package com.enatbanksc.casemanagementsystem.case_management.exceptions;

public class PasswordMisMatchException extends RuntimeException {
   private String message;

    public PasswordMisMatchException(String message) {
        super(message);

    }
}
