package com.devmello.bff_agendador.controller;

import com.devmello.bff_agendador.bussiness.dtos.in.TarefaDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.out.TarefaDTOResponse;
import com.devmello.bff_agendador.infrastructure.config.SecurityConfig;
import com.devmello.bff_agendador.infrastructure.enums.StatusNotificacao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Tarefas", description = "Cadastra tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public interface TarefaController {

    @Operation(summary = "Salvar tarefas de usuários", description = "Cria um nova tarefa", responses = {
            @ApiResponse(responseCode = "201", description = "Tarefa salva com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro de servidor"),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    })
    ResponseEntity<TarefaDTOResponse> gravarTarefa(@RequestBody TarefaDTORequest tarefaDTO,
                                                   @RequestHeader(name = "Authorization", required = false) String token);

    @Operation(summary = "Busca tarefas por periodo", description = "Busca tarefas cadastradas por periodo", responses = {
            @ApiResponse(responseCode = "200", description = "Tarefas encontradas"),
            @ApiResponse(responseCode = "500", description = "Erro de servidor"),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    })
    ResponseEntity<List<TarefaDTOResponse>> buscarListaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token
    );

    @Operation(summary = "Busca tarefas por email de usuario", description = "Busca lista de tarefas cadastradas por usuairo", responses = {
            @ApiResponse(responseCode = "200", description = "Tarefas encontradas"),
            @ApiResponse(responseCode = "404", description = "Email não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro de servidor"),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    })
    ResponseEntity<List<TarefaDTOResponse>> buscarTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token);

    @Operation(summary = "Deleta tarefas cadastradas por Id", description = "Deleta tarefas cadastradas por Id", responses = {
            @ApiResponse(responseCode = "200", description = "Tarefas deletadas"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro de servidor"),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    })
    ResponseEntity<Void> deletaTarefaPorId(@PathVariable String id,
                                           @RequestHeader(name = "Authorization", required = false) String token);

    @Operation(summary = "Altera status das tarefas", description = "Altera status das tarefas cadastradas", responses = {
            @ApiResponse(responseCode = "200", description = "Status da tarefa alterado"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro de servidor"),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    })
    ResponseEntity<TarefaDTOResponse> atualizarStatusTarefa(@RequestParam("status") StatusNotificacao status,
                                                            @RequestParam("id") String id,
                                                            @RequestHeader(name = "Authorization", required = false) String token);

    @Operation(summary = "Altera dados de tarefas", description = "Altera dados de tarefas cadastradas", responses = {
            @ApiResponse(responseCode = "200", description = "Tarefas alteradas"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro de servidor"),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    })
    ResponseEntity<TarefaDTOResponse> atualizarDadosTarefa(@RequestBody TarefaDTORequest tarefaDTO,
                                                           @RequestParam("id") String id,
                                                           @RequestHeader(name = "Authorization", required = false) String token);
}
