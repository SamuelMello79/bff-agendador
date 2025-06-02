package com.devmello.bff_agendador.infrastructure.exception;

public class ConflictException extends RuntimeException{
    public ConflictException(String message) {
        super(message);
    }
}
