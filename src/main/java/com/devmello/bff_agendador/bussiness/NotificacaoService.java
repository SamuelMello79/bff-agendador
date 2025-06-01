package com.devmello.bff_agendador.bussiness;

import com.devmello.bff_agendador.bussiness.dtos.out.TarefaDTOResponse;
import com.devmello.bff_agendador.infrastructure.client.NotificacaoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacaoService {
    private final NotificacaoClient client;

    public void sendEmail(TarefaDTOResponse tarefaDTO) {
        client.sendEmail(tarefaDTO);
    }

}
