package com.devmello.bff_agendador.infrastructure.exception;

public class BussinessException extends RuntimeException {
    public BussinessException(String message) {
        super(message);
    }
}
