import java.time.LocalDate;
import java.util.Date;

public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;

    public  Pessoa(String nome, LocalDate dataNascimento) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        if (dataNascimento == null) {
            throw new IllegalArgumentException("A data de nascimento nao pode ser nula ou inválida");
        }
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
