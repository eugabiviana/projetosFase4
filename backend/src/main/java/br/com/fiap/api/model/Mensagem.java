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
    private LocalDateTime dataCriacaoMensagem = LocalDateTime.now();

    @Builder.Default
    private int gostei = 0;


}
