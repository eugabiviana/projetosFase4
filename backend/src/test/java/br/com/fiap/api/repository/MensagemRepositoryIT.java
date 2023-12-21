package br.com.fiap.api.repository;

import br.com.fiap.api.model.Mensagem;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class MensagemRepositoryIT {

    @Autowired
    private MensagemRepository mensagemRepository;

    @Test
    void devePermitirCriarTabela(){
        var totalDeRegistros = mensagemRepository.count();
        assertThat(totalDeRegistros).isNotNegative();
    }

    @Test
    void devePermitirRegistrarMensagem(){
        //Arrange
        var id = UUID.randomUUID();
        var mensagem = gerarMensagem();
        mensagem.setId(id);

        //Act
        var mensagemRecebida = mensagemRepository.save(mensagem);

        //Assert
        assertThat(mensagemRecebida)
                .isInstanceOf(Mensagem.class)
                .isNotNull();
        assertThat(mensagemRecebida.getId()).isEqualTo(id);
        assertThat(mensagemRecebida.getConteudo()).isEqualTo(mensagem.getConteudo());
        assertThat(mensagemRecebida.getUsuario()).isEqualTo(mensagem.getUsuario());

    }

    @Test
    void devePermitirBuscarMensagem(){
        //Arrange
        var id = UUID.randomUUID();
        var mensagem = gerarMensagem();
        mensagem.setId(id);
        registrarMensagem(mensagem);

        //Act
        var mensagemRecebidaOptional = mensagemRepository.findById(id);

        //Assert
        assertThat(mensagemRecebidaOptional).isPresent();

        mensagemRecebidaOptional.ifPresent(mensagemRecebida ->{
            assertThat(mensagemRecebida.getId()).isEqualTo(id);
            assertThat(mensagemRecebida.getConteudo()).isEqualTo(mensagem.getConteudo());
        });

    }

    @Test
    void devePermitirExcluirMensagem(){
        fail("Método não implementado");
    }

    @Test
    void devePermitirListarMensagens(){
        fail("Método não implementado");
    }

    private Mensagem gerarMensagem(){
        return Mensagem.builder()
                .usuario("Dani")
                .conteudo("conteúdo da msg")
                .build();
    }
    private Mensagem registrarMensagem(Mensagem mensagem){
        return mensagemRepository.save(mensagem);
    }
}
