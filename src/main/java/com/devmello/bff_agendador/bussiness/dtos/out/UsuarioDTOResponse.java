package com.devmello.bff_agendador.bussiness.dtos.out;

import lombok.*;

import java.util.List;

@Builder
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTOResponse {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTOResponse> enderecos;
    private List<TelefoneDTOResponse> telefones;
}
