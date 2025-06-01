package com.devmello.bff_agendador.bussiness;

import com.devmello.bff_agendador.bussiness.dtos.in.TarefaDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.out.TarefaDTOResponse;
import com.devmello.bff_agendador.infrastructure.client.TarefaClient;
import com.devmello.bff_agendador.infrastructure.enums.StatusNotificacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaClient client;

    public TarefaDTOResponse gravarTarefa(TarefaDTORequest tarefaDTO, String token) {
        return client.gravarTarefa(tarefaDTO, token);
    }

    public List<TarefaDTOResponse> buscarListaTarefasPorPeriodo(
            LocalDateTime dataInicial,
            LocalDateTime dataFinal,
            String token) {
        return client.buscarListaTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefaDTOResponse> buscarTarefasPorEmail(String token) {
        return client.buscarTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        client.deletaTarefaPorId(id, token);
    }

    public TarefaDTOResponse atualizarStatusTarefa(StatusNotificacao status, String id, String token) {
        return client.atualizarStatusTarefa(status, id, token);
    }

    public TarefaDTOResponse atualizarDadosTarefa(TarefaDTORequest tarefaDTO, String id, String token) {
        return client.atualizarDadosTarefa(tarefaDTO, id, token);
    }
}
