package com.devmello.bff_agendador.controller;

import com.devmello.bff_agendador.bussiness.dtos.in.EnderecoDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.in.LoginDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.in.TelefoneDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.in.UsuarioDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.out.EnderecoDTOResponse;
import com.devmello.bff_agendador.bussiness.dtos.out.TelefoneDTOResponse;
import com.devmello.bff_agendador.bussiness.dtos.out.UsuarioDTOResponse;
import com.devmello.bff_agendador.infrastructure.config.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuário", description = "Cadastro e login de usuário")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public interface UsuarioController {
    @Operation(summary = "Salvar usuários", description = "Cria um novo usuário", responses = {
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuário já cadastrado"),
            @ApiResponse(responseCode = "500", description = "Erro de servidor"),
    })
    ResponseEntity<UsuarioDTOResponse> salvar(@RequestBody UsuarioDTORequest usuarioDTO);

    @Operation(summary = "Salvar usuários", description = "Cria um novo usuário", responses = {
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuário já cadastrado"),
            @ApiResponse(responseCode = "500", description = "Erro de servidor"),
    })
    ResponseEntity<List<UsuarioDTOResponse>> listar(@RequestHeader(name = "Authorization", required = false) String token);

    @Operation(summary = "Buscar dados de usuários por email", description = "Buscar dados do usuário", responses = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro de servidor"),
    })
    ResponseEntity<UsuarioDTOResponse> buscarPorEmail(@RequestParam("email") String email,
                                                      @RequestHeader(name = "Authorization", required = false) String token);

    @Operation(summary = "Login de usuários", description = "Login do usuário", responses = {
            @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credênciais são inválidas"),
            @ApiResponse(responseCode = "500", description = "Erro de servidor"),
    })
    ResponseEntity<String> login(@RequestBody LoginDTORequest usuarioDTO);

    @Operation(summary = "Salva endereços", description = "Cria um novo endereço de usuário", responses = {
            @ApiResponse(responseCode = "200", description = "Endereço de usuário criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro de servidor"),
    })
    ResponseEntity<EnderecoDTOResponse> adicionaEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                         @RequestHeader(name = "Authorization", required = false) String token);

    @Operation(summary = "Salva telefones", description = "Cria um novo telefone de usuário", responses = {
            @ApiResponse(responseCode = "200", description = "Telefone de usuário criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro de servidor"),
    })
    ResponseEntity<TelefoneDTOResponse> adicionaTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                                         @RequestHeader(name = "Authorization", required = false) String token);

    @Operation(summary = "Atualizar dados de usuários", description = "Atualizar dados de usuário", responses = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro de servidor"),
    })
    ResponseEntity<UsuarioDTOResponse> atualizaDadoUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                                           @RequestHeader(name = "Authorization", required = false) String token);

    @Operation(summary = "Atualizar dados endereço de usuários",
            description = "Atualizar dados de endereço de usuário", responses = {
            @ApiResponse(responseCode = "200", description = "Endereço de usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro de servidor"),
    })
    ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                         @RequestParam("id") Long id,
                                                         @RequestHeader(name = "Authorization", required = false) String token);

    @Operation(summary = "Atualizar dados telefone de usuários",
            description = "Atualizar dados de telefone de usuário", responses = {
            @ApiResponse(responseCode = "200", description = "Telefone de usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro de servidor"),
    })
    ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                                         @RequestParam("id") Long id,
                                                         @RequestHeader(name = "Authorization", required = false) String token);

    @Operation(summary = "Deleta usuário por email", description = "Deleta usuário", responses = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro de servidor"),
    })
    ResponseEntity<Void> deletarPorEmail(@PathVariable String email,
                                         @RequestHeader(name = "Authorization", required = false) String token);
}
