package com.devmello.bff_agendador.bussiness.dtos.in;

import lombok.*;

import java.util.List;

@Builder
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTORequest {
    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTORequest> enderecos;
    private List<TelefoneDTORequest> telefones;
}
