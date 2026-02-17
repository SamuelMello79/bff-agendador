package com.devmello.bff_agendador.bussiness;

import com.devmello.bff_agendador.bussiness.dtos.in.EnderecoDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.in.LoginDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.in.TelefoneDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.in.UsuarioDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.out.EnderecoDTOResponse;
import com.devmello.bff_agendador.bussiness.dtos.out.TelefoneDTOResponse;
import com.devmello.bff_agendador.bussiness.dtos.out.UsuarioDTOResponse;
import com.devmello.bff_agendador.bussiness.dtos.out.ViaCepDTOResponse;
import com.devmello.bff_agendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public List<UsuarioDTOResponse> findAll(String token) {
        return client.listar(token);
    }

    public UsuarioDTOResponse saveUsuario(UsuarioDTORequest usuarioDTO) {
        return client.salvar(usuarioDTO);
    }

    public String login(LoginDTORequest usuarioDTO) {
        return client.login(usuarioDTO);
    }

    public UsuarioDTOResponse findByEmail(String email, String token) {
        return client.buscarPorEmail(email, token);
    }

    public UsuarioDTOResponse findById(Long id, String token) {
        return client.buscarPorId(id, token);
    }

    public void deleteByEmail(String email, String token) {
        client.deletarPorEmail(email, token);
    }

    public UsuarioDTOResponse updateUsuario(UsuarioDTORequest usuarioDTO, String token) {
        return client.atualizaDadoUsuario(usuarioDTO, token);
    }

    public EnderecoDTOResponse updateEndereco(Long id, EnderecoDTORequest enderecoDTO, String token) {
        return client.atualizaEndereco(enderecoDTO, id, token);
    }

    public TelefoneDTOResponse updateTelefone(Long id, TelefoneDTORequest telefoneDTO, String token) {
        return client.atualizaTelefone(telefoneDTO, id, token);
    }

    public EnderecoDTOResponse cadastrarEndereco(String token, EnderecoDTORequest enderecoDTO) {
        return client.adicionaEndereco(enderecoDTO, token);
    }

    public TelefoneDTOResponse cadastrarTelefone(String token, TelefoneDTORequest telefoneDTO) {
        return client.adicionaTelefone(telefoneDTO, token);
    }

    public ViaCepDTOResponse buscarDadosCep(String cep) {
        return client.buscarDadosCep(cep);
    }
}
