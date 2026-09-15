import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PrincipalTest {

    private List<Funcionario> funcionarios;

    @BeforeEach
    void setUp() {
        funcionarios = Principal.inicializarListaFuncionarios();
    }

    @Test
    void deveRemoverFuncionarioPorNome() {
        Principal.removerFuncionarioPorNome(funcionarios, "João");
        boolean contemJoao = funcionarios.stream().anyMatch(f -> f.getNome().equals("João"));
        assertFalse(contemJoao);
    }

    @Test
    void deveAplicarAumentoDe10Porcento() {
        Funcionario maria = funcionarios.get(0); // Salário inicial: 2009.44
        Principal.aplicarAumento(funcionarios, new BigDecimal("0.10"));

        // 2009.44 + 10% = 2210.384
        assertEquals(new BigDecimal("2210.3840"), maria.getSalario());
    }

    @Test
    void deveAgruparFuncionariosPorFuncao() {
        Map<String, List<Funcionario>> agrupados = Principal.agruparPorFuncao(funcionarios);

        assertTrue(agrupados.containsKey("Operador"));
        assertEquals(3, agrupados.get("Operador").size()); // Maria, João, Heitor
    }

    @Test
    void deveFiltrarAniversariantes() {
        List<Funcionario> filtrados = Principal.filtrarAniversariantes(funcionarios, 10, 12);

        assertEquals(2, filtrados.size()); // Maria (10), Miguel (10)
        assertTrue(filtrados.stream().anyMatch(f -> f.getNome().equals("Maria")));
    }

    @Test
    void deveObterFuncionarioMaisVelho() {
        Funcionario maisVelho = Principal.obterFuncionarioMaisVelho(funcionarios);
        assertEquals("Caio", maisVelho.getNome()); // Nascido em 1961
    }

    @Test
    void deveCalcularTotalDosSalarios() {
        BigDecimal total = Principal.calcularTotalSalarios(funcionarios);
        assertEquals(new BigDecimal("48563.31"), total);
    }

    @Test
    void deveCalcularQuantidadeSalariosMinimos() {
        BigDecimal qtd = Principal.calcularQuantidadeSalariosMinimos(new BigDecimal("2424.00"), new BigDecimal("1212.00"));
        assertEquals(new BigDecimal("2.00"), qtd);
    }
}