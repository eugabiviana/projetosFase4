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
        assertThat(totalDeRegistros).isGreaterThan(0);
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
        var id = UUID.fromString("a080bb28-cb45-4494-8229-7cc5a3674fbf");

        //Act
        var mensagemRecebidaOptional = mensagemRepository.findById(id);

        //Assert
        assertThat(mensagemRecebidaOptional).isPresent();

        mensagemRecebidaOptional.ifPresent(mensagemRecebida ->{
            assertThat(mensagemRecebida.getId()).isEqualTo(id);
        });

    }

    @Test
    void devePermitirExcluirMensagem(){
        //Arrange
        var id = UUID.fromString("acddb9b3-84ea-4377-9916-4a453e7407dc");

        //Act
        mensagemRepository.deleteById(id);
        var mensagemRecebidaOpcional = mensagemRepository.findById(id);

        //Assert
        assertThat(mensagemRecebidaOpcional).isEmpty();
    }

    @Test
    void devePermitirListarMensagens(){
        //Act
        var resultadosObtidos = mensagemRepository.findAll();

        //Assert
        assertThat(resultadosObtidos).hasSizeGreaterThan(0);
    }

    private Mensagem gerarMensagem(){
        return Mensagem.builder()
                .usuario("Dani")
                .conteudo("conteúdo da msg")
                .build();
    }
}
