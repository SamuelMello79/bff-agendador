package com.devmello.bff_agendador.controller;

import com.devmello.bff_agendador.bussiness.UsuarioService;
import com.devmello.bff_agendador.bussiness.dtos.in.EnderecoDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.in.LoginDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.in.TelefoneDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.in.UsuarioDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.out.EnderecoDTOResponse;
import com.devmello.bff_agendador.bussiness.dtos.out.TelefoneDTOResponse;
import com.devmello.bff_agendador.bussiness.dtos.out.UsuarioDTOResponse;
import com.devmello.bff_agendador.bussiness.dtos.out.ViaCepDTOResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioControllerImpl implements UsuarioController {
    private final UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioDTOResponse> salvar(@RequestBody UsuarioDTORequest usuarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveUsuario(usuarioDTO));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTOResponse>> listar(@RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(service.findAll(token));
    }

    @GetMapping
    public ResponseEntity<UsuarioDTOResponse> buscarPorEmail(@RequestParam("email") String email,
                                                             @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(service.findByEmail(email, token));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTORequest usuarioDTO) {
        return ResponseEntity.ok(service.login(usuarioDTO));
    }

    @PostMapping("/endereco")
    public ResponseEntity<EnderecoDTOResponse> adicionaEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(service.cadastrarEndereco(token, enderecoDTO));
    }

    @PostMapping("/telefone")
    public ResponseEntity<TelefoneDTOResponse> adicionaTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(service.cadastrarTelefone(token, telefoneDTO));
    }

    @PutMapping
    public ResponseEntity<UsuarioDTOResponse> atualizaDadoUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                                                  @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(service.updateUsuario(usuarioDTO, token));
    }

    // Atualiza um endereço do usuário pelo EndereçoID
    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(service.updateEndereco(id, enderecoDTO, token));
    }

    // Atualiza um telefone do usuário pelo TelefoneID
    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(service.updateTelefone(id, telefoneDTO, token));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletarPorEmail(@PathVariable String email,
                                                @RequestHeader(name = "Authorization", required = false) String token) {
        service.deleteByEmail(email, token);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/endereco/{cep}")
    public ResponseEntity<ViaCepDTOResponse> buscarDadosCep(@PathVariable String cep) {
        return ResponseEntity.ok(service.buscarDadosCep(cep));
    }
}
