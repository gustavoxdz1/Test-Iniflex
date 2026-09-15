import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioTest {

    @Test
    void deveCriarFuncionarioComSucesso() {
        Funcionario funcionario = new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador");

        assertEquals("Caio", funcionario.getNome());
        assertEquals(new BigDecimal("9836.14"), funcionario.getSalario());
        assertEquals("Coordenador", funcionario.getFuncao());
    }

    @Test
    void deveLancarExcecaoQuandoSalarioForNuloOuNegativo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), null, "Coordenador"));

        assertThrows(IllegalArgumentException.class, () ->
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("-100"), "Coordenador"));
    }

    @Test
    void deveLancarExcecaoQuandoFuncaoForNulaOuVazia() {
        assertThrows(IllegalArgumentException.class, () ->
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), null));

        assertThrows(IllegalArgumentException.class, () ->
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "   "));
    }

    @Test
    void deveAtualizarSalarioComSucesso() {
        Funcionario funcionario = new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("1000.00"), "Coordenador");
        funcionario.setSalario(new BigDecimal("1500.00"));

        assertEquals(new BigDecimal("1500.00"), funcionario.getSalario());
    }
}