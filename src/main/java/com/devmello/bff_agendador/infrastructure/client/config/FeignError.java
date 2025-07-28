package com.devmello.bff_agendador.infrastructure.client.config;

import com.devmello.bff_agendador.infrastructure.exception.*;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        String mensagem = "Erro ";

        switch (response.status()) {

            case 409:
                return new ConflictException(mensagem + mensagemErro(response));
            case 404:
                return new NotFoundException(mensagem + mensagemErro(response));
            case 401:
                return new UnauthorizedException(mensagem + mensagemErro(response));
            case 400:
                return new BadRequestException(mensagem + mensagemErro(response));
            default:
                return new BussinessException("Erro de servidor");
        }
    }

    private String mensagemErro(Response response) {
        try {
            if (Objects.isNull(response.body())){
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
