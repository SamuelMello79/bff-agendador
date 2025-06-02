package com.devmello.bff_agendador.bussiness;

import com.devmello.bff_agendador.bussiness.dtos.in.LoginDTORequest;
import com.devmello.bff_agendador.bussiness.dtos.out.TarefaDTOResponse;
import com.devmello.bff_agendador.infrastructure.enums.StatusNotificacao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CronService {
    private final NotificacaoService notificacaoService;
    private final TarefaService tarefaService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscarTarefasProximaHora() {
        LocalDateTime proximaHora = LocalDateTime.now().plusHours(1);
        LocalDateTime proximaHoraMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);
        String token = login(converter());

        List<TarefaDTOResponse> tarefas =
                tarefaService.buscarListaTarefasPorPeriodo(proximaHora, proximaHoraMaisCinco, token);
        log.info("Lista de tarefas encontradas: " + tarefas);

        tarefas.forEach(t -> {
            notificacaoService.sendEmail(t);
            tarefaService.atualizarStatusTarefa(StatusNotificacao.NOTIFICADO, t.getId(), token);
            log.info("Email enviado: " + t.getEmailUsuario());
        });
        log.info("Finalizada a busca e notificacação de tarefas");
    }

    public String login(LoginDTORequest dto) {
        return usuarioService.login(dto);
    }

    public LoginDTORequest converter() {
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
