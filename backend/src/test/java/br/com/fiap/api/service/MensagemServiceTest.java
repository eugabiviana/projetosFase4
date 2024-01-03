package br.com.fiap.api.service;

import br.com.fiap.api.model.Mensagem;
import br.com.fiap.api.repository.MensagemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class MensagemServiceTest {

    private MensagemService mensagemService;

    @Mock
    private MensagemRepository mensagemRepository;

    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        mensagemService = new MensagemServiceImpl(mensagemRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }
    @Test
    void devePermitirRegistrarMensagem(){
        //Arrange
        var mensagem = gerarMensagem();
        when(mensagemRepository.save(any(Mensagem.class)))
                .thenAnswer(i -> i.getArgument(0));
        //Act
        var mensagemRegistrada = mensagemService
                .registrarMensagem(mensagem);
        //Assert
        assertThat(mensagemRegistrada)
                .isInstanceOf(Mensagem.class)
                .isNotNull();
        assertThat(mensagemRegistrada.getConteudo()).isEqualTo(mensagem.getConteudo());
        assertThat(mensagemRegistrada.getUsuario()).isEqualTo(mensagem.getUsuario());
        assertThat(mensagemRegistrada.getId()).isNotNull();
    }

    @Test
    void devePermitirBuscarMensagem(){
        fail("teste não implementado");
    }

    @Test
    void devePermitirAlterarMensagem(){
        fail("teste não implementado");
    }

    @Test
    void devePermitirExcluirMensagem(){
        fail("teste não implementado");
    }

    @Test
    void devePermitirListarMensagem(){
        fail("teste não implementado");
    }

    private Mensagem gerarMensagem(){
        return Mensagem.builder()
                .usuario("Dani")
                .conteudo("É uma linda! <3")
                .build();
    }
}
