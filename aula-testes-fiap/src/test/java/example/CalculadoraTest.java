package example;

import org.example.Calculadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class CalculadoraTest {

    @Test
    void deveSomar(){
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.somar(4,2);
        Assertions.assertEquals(6,resultado);
    }

    @Test
    void deveSubtrair(){
        fail("teste não implementado");
    }

    @Test
    void deveMultiplicar(){
        fail("teste não implementado");
    }

    @Test
    void deveDividir(){
        fail("teste não implementado");
    }
}
