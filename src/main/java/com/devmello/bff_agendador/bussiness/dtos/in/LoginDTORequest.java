package com.devmello.bff_agendador.bussiness.dtos.in;

import lombok.*;

@Builder
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTORequest {
    private String email;
    private String senha;
}
