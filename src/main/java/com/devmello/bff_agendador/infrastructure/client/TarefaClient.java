package com.devmello.bff_agendador.infrastructure.client;

import com.devmello.bff_agendador.bussiness.dtos.in.TarefaDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.out.TarefaDTOResponse;
import com.devmello.bff_agendador.infrastructure.enums.StatusNotificacao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tarefas", url = "${api.tarefa.url}")
public interface TarefaClient {

    @PostMapping
    TarefaDTOResponse gravarTarefa(@RequestBody TarefaDTORequest tarefaDTO,
                                   @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefaDTOResponse> buscarListaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token
    );

    @GetMapping
    List<TarefaDTOResponse> buscarTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping("/{id}")
    void deletaTarefaPorId(@PathVariable String id,
                           @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefaDTOResponse atualizarStatusTarefa(@RequestParam("status") StatusNotificacao status,
                                            @RequestParam("id") String id,
                                            @RequestHeader("Authorization") String token);

    @PutMapping
    TarefaDTOResponse atualizarDadosTarefa(@RequestBody TarefaDTORequest tarefaDTO,
                                           @RequestParam("id") String id,
                                           @RequestHeader("Authorization") String token);
}
