package br.com.fiap.api.service;

import br.com.fiap.api.exception.MensagemNotFoundException;
import br.com.fiap.api.model.Mensagem;
import br.com.fiap.api.repository.MensagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MensagemServiceImpl implements MensagemService{

    private final MensagemRepository mensagemRepository;

    @Override
    public Mensagem registrarMensagem(Mensagem mensagem) {
        mensagem.setId(UUID.randomUUID());//regra de negócio
        return mensagemRepository.save(mensagem);
    }

    @Override
    public Mensagem buscarMensagem(UUID id) {
        return mensagemRepository.findById(id)
                .orElseThrow(() -> new MensagemNotFoundException("Mensagem não encontrada!"));
    }

    @Override
    public Mensagem alterarMensagem(Mensagem mensagemAtual, Mensagem mensagemNova) {
        return null;
    }

    @Override
    public boolean excluirMensagem(UUID id) {
        return false;
    }
}
