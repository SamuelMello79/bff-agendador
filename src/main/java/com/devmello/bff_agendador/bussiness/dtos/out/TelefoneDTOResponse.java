package com.devmello.bff_agendador.bussiness.dtos.out;

import lombok.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDTOResponse {
    private Long id;
    private String numero;
    private String ddd;
}
