package com.devmello.bff_agendador.infrastructure.client;

import com.devmello.bff_agendador.bussiness.dtos.out.TarefaDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "tarefas", url = "${api.notificacao.url}")
public interface NotificacaoClient {
    @PostMapping
    void sendEmail(@RequestBody TarefaDTOResponse tarefaDTO);
}
