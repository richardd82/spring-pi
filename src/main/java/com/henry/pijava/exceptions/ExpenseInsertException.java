package com.henry.pijava.exceptions;

public class ExpenseInsertException extends RuntimeException{
    public ExpenseInsertException(String message, Throwable cause) {
        super(message, cause);
    }
}
