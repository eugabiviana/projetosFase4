package example;

import org.example.Calculadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    private Calculadora calculadora;
    @BeforeEach //essa é uma classe padrão que irá executar antes de cada teste criado. Isso evita repetição de código.
    void setup(){
        calculadora = new Calculadora();
    }

    @Test
    void deveSomar(){
        int resultado = calculadora.somar(4,2);
        assertThat(resultado).isEqualTo(6); //Escrever dessa forma, dá mais opções de comparações.
    }

    @Test
    void deveSubtrair(){
        int resultado = calculadora.subtrair(5,2);
        assertEquals(3,resultado);
    }

    @Test
    void deveMultiplicar(){
        int resultado = calculadora.multiplicar(5,2);
        assertEquals(10, resultado);
    }

    @Test
    void deveDividir(){
        int resultado = calculadora.dividir(10,2);
        assertEquals(5, resultado);
    }

    @Test
    void deveDividir_gerarExcecaoQuandoPorZero(){
        Throwable exception = catchThrowable(() -> calculadora.dividir(4,0));
        assertThat(exception).isInstanceOf(ArithmeticException.class)
                .hasMessage("/ by zero");
    }
}
