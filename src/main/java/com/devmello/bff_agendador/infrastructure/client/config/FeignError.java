package com.devmello.bff_agendador.infrastructure.client.config;

import com.devmello.bff_agendador.infrastructure.exception.ConflictException;
import com.devmello.bff_agendador.infrastructure.exception.BussinessException;
import com.devmello.bff_agendador.infrastructure.exception.NotFoundException;
import com.devmello.bff_agendador.infrastructure.exception.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 409:
                return new ConflictException("Erro: atributo já existente");
            case 404:
                return new NotFoundException("Erro: atributo não encontrado");
            case 401:
                return new UnauthorizedException("Erro: usuário não autorizado");
            default:
                return new BussinessException("Erro de servidor");
        }
    }
}
