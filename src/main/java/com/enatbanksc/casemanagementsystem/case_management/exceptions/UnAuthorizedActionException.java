package com.enatbanksc.casemanagementsystem.case_management.exceptions;

import lombok.Getter;

@Getter
public class UnAuthorizedActionException extends RuntimeException {
    private final String message;

    public UnAuthorizedActionException(String msg) {
        this.message = msg;
    }

}
