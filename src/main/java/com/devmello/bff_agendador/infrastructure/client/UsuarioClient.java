package com.devmello.bff_agendador.infrastructure.client;

import com.devmello.bff_agendador.bussiness.dtos.in.EnderecoDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.in.LoginDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.in.TelefoneDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.in.UsuarioDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.out.EnderecoDTOResponse;
import com.devmello.bff_agendador.bussiness.dtos.out.TelefoneDTOResponse;
import com.devmello.bff_agendador.bussiness.dtos.out.UsuarioDTOResponse;
import com.devmello.bff_agendador.bussiness.dtos.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "usuario", url = "${api.usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioDTOResponse findByEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponse salvar(@RequestBody UsuarioDTORequest usuarioDTO);

    @GetMapping("/listar")
    List<UsuarioDTOResponse> listar(@RequestHeader(name = "Authorization") String token);

    @GetMapping("/{id}")
    UsuarioDTOResponse listarPorId(@PathVariable Long id, @RequestHeader(name = "Authorization") String token);

    @GetMapping
    UsuarioDTOResponse buscarPorEmail(@RequestParam("email") String email, @RequestHeader(name = "Authorization") String token);

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest usuarioDTO);

    @PostMapping("/endereco")
    EnderecoDTOResponse adicionaEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                         @RequestHeader(name = "Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse adicionaTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                         @RequestHeader(name = "Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadoUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                           @RequestHeader(name = "Authorization", required = false) String token);

    // Atualiza um endereço do usuário pelo EndereçoID
    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                         @RequestParam("id") Long id,
                                         @RequestHeader(name = "Authorization", required = false) String token);

    // Atualiza um telefone do usuário pelo TelefoneID
    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                         @RequestParam("id") Long id,
                                         @RequestHeader(name = "Authorization", required = false) String token);

    @DeleteMapping("/{email}")
    void deletarPorEmail(@PathVariable String email, @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping("/endereco/{cep}")
    ViaCepDTOResponse buscarDadosCep(@PathVariable("cep") String cep);
}
