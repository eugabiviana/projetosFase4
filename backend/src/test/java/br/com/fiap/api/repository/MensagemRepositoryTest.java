package br.com.fiap.api.repository;

import br.com.fiap.api.model.Mensagem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class MensagemRepositoryTest {

    @Mock
    private MensagemRepository mensagemRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setup(){ //inicia o mock
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception{ //limpa o mock da memória
        openMocks.close();
    }
    @Test
    void devePermitirRegistrarMensagem(){
        //Arrange
        var mensagem = Mensagem.builder()
                .id(UUID.randomUUID())
                .usuario("Dani")
                .conteudo("counteúdo da msg")
                .build();

        when(mensagemRepository.save(any(Mensagem.class))).thenReturn(mensagem);
        //Act
        var mensagemRegistrada = mensagemRepository.save(mensagem);

        //Assert
        assertThat(mensagemRegistrada).isNotNull().isEqualTo(mensagem);
        verify(mensagemRepository, times(1)).save(any(Mensagem.class));


    }

    @Test
    void devePermitirBuscarMensagem(){
        fail("Teste não implementado!");
    }

    @Test
    void devePermitirAlterarMensagem(){
        fail("Teste não implementado!");
    }

    @Test
    void devePermitirExcluirMensagem(){
        fail("Teste não implementado!");
    }


}
