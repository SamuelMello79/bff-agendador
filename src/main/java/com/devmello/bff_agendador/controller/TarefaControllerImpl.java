package com.devmello.bff_agendador.controller;

import com.devmello.bff_agendador.bussiness.TarefaService;
import com.devmello.bff_agendador.bussiness.dtos.in.TarefaDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.out.TarefaDTOResponse;
import com.devmello.bff_agendador.infrastructure.enums.StatusNotificacao;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaControllerImpl implements TarefaController {
    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTOResponse> gravarTarefa(@RequestBody TarefaDTORequest tarefaDTO,
                                                          @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(tarefaDTO, token));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTOResponse>> buscarListaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(tarefaService.buscarListaTarefasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTOResponse>> buscarTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.buscarTarefasPorEmail(token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaTarefaPorId(@PathVariable String id,
                                                  @RequestHeader(name = "Authorization", required = false) String token) {
        tarefaService.deletaTarefaPorId(id, token);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<TarefaDTOResponse> atualizarStatusTarefa(@RequestParam("status") StatusNotificacao status,
                                                                   @RequestParam("id") String id,
                                                                   @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.atualizarStatusTarefa(status, id, token));
    }

    @PutMapping
    public ResponseEntity<TarefaDTOResponse> atualizarDadosTarefa(@RequestBody TarefaDTORequest tarefaDTO,
                                                                  @RequestParam("id") String id,
                                                                  @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.atualizarDadosTarefa(tarefaDTO, id, token));
    }
}
