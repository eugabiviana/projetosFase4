package br.com.fiap.api.repository;

import br.com.fiap.api.model.Mensagem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
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
        var mensagem = gerarMensagem();

        when(mensagemRepository.save(any(Mensagem.class))).thenReturn(mensagem);
        //Act
        var mensagemRegistrada = mensagemRepository.save(mensagem);

        //Assert
        assertThat(mensagemRegistrada).isNotNull().isEqualTo(mensagem);
        verify(mensagemRepository, times(1)).save(any(Mensagem.class));
    }

    @Test
    void devePermitirBuscarMensagem(){
        //Arrange
        var id = UUID.randomUUID();
        var mensagem = gerarMensagem();
        mensagem.setId(id);

        when(mensagemRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(mensagem));
        //Act
        var mensagemRecebidaOpcional = mensagemRepository.findById(id);

        //Assert
        assertThat(mensagemRecebidaOpcional).isPresent().containsSame(mensagem);
        mensagemRecebidaOpcional.ifPresent(mensagemRecebida -> {
            assertThat(mensagemRecebida.getId()).isEqualTo(mensagem.getId());
            assertThat(mensagemRecebida.getConteudo()).isEqualTo(mensagem.getConteudo());
        });
        verify(mensagemRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void devePermitirExcluirMensagem(){
        //Arrange
       var id = UUID.randomUUID();
       doNothing().when(mensagemRepository).deleteById(any(UUID.class));// doNothing -> porque o delete não tem retorno
        //Act
        mensagemRepository.deleteById(id);
        //Assert
        verify(mensagemRepository, times(1)).deleteById(any(UUID.class));
    }

    @Test
    void devePermitirListarMensagens(){
        //Arrange
        var mensagem1 = gerarMensagem();
        var mensagem2 = gerarMensagem();
        var listaMensagens = Arrays.asList(
                mensagem1,
                mensagem2);
        when(mensagemRepository.findAll()).thenReturn(listaMensagens);
        // Act
        var mensagensRecebidas = mensagemRepository.findAll();
        // Assert
        assertThat(mensagensRecebidas)
                .hasSize(2)
                .containsExactlyInAnyOrder(mensagem1, mensagem2);
        verify(mensagemRepository, times(1)).findAll();

    }
    private Mensagem gerarMensagem(){
        return Mensagem.builder()
                .usuario("Dani")
                .conteudo("conteúdo da msg")
                .build();
    }


}
