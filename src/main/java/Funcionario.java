import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        if (salario == null || salario.compareTo(BigDecimal.ZERO) <0) {
            throw new IllegalArgumentException("O salarario nao pode ser nulo ou negativo.");
        }
        if (funcao == null || funcao.trim().isEmpty()) {
            throw new IllegalArgumentException("A funcao nao pode ser nula ou vazia.");
        }
        this.salario = salario;
        this.funcao = funcao;
    }
    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        if(salario == null || salario.compareTo(BigDecimal.ZERO) <0) {
          throw new IllegalArgumentException("O novo salario nao pode ser nulo ou negativo");
        }
        this.salario = salario;
    }
    public String getFuncao() {
        return funcao;
    }
}
