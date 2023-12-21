package br.com.fiap.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mensagem {

    @Id
    private UUID id;

    @Column(nullable = false) // não aceita usuário nulo
    @NotEmpty(message = "Usuário não pode estar vazio!") //Msg que aparece se tiver uma requisição com esse campo vazio
    private String usuario;

    @Column(nullable = false)
    @NotEmpty(message = "O conteúdo não pode estar vazio!")
    private String conteudo;

    @Builder.Default
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Builder.Default
    private int gostei = 0;

//    public Mensagem(UUID id, String usuario, String conteudo, LocalDateTime dataCriacaoMensagem, int gostei) {
//        this.id = id;
//        this.usuario = usuario;
//        this.conteudo = conteudo;
//        this.dataCriacaoMensagem = dataCriacaoMensagem;
//        this.gostei = gostei;
//    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataCriacaoMensagem() {
        return dataCriacao;
    }

    public void setDataCriacaoMensagem(LocalDateTime dataCriacaoMensagem) {
        this.dataCriacao = dataCriacaoMensagem;
    }

    public int getGostei() {
        return gostei;
    }

    public void setGostei(int gostei) {
        this.gostei = gostei;
    }
}
