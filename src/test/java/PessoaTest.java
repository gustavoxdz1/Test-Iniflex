import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class PessoaTest {
    @Test
    void deveCriarPessoaComSucesso() {
        Pessoa pessoa = new Pessoa("Maria", LocalDate.of(2000, 10, 18));
        assertEquals("Maria", pessoa.getNome());
        assertEquals(LocalDate.of(2000, 10, 18), pessoa.getDataNascimento());
    }

    @Test
    void deveLancarUmaExceptionQuandoNomeForNuloOuVazio() {
       assertThrows(IllegalArgumentException.class, () -> new Pessoa(null, LocalDate.of(2000, 10, 18)));
       assertThrows(IllegalArgumentException.class, () -> new Pessoa("", LocalDate.of(2000, 10, 18)));
    }

    @Test
    void deveLancarUmaExceptionQuandoDataDeNascimentoForNula() {
        assertThrows(IllegalArgumentException.class, () -> new Pessoa("Maria", null));
    }
}
