package com.devmello.bff_agendador.bussiness.dtos.in;

import lombok.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDTORequest {
    private String numero;
    private String ddd;
}
