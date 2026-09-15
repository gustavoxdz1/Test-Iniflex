import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormat FORMATADOR_NUMERICO = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {
        try {
            // 3.1  Inserir Funcionario
            List<Funcionario> funcionarios = inicializarListaFuncionarios();

            // 3.2  Remover o Joao da lista
            removerFuncionarioPorNome(funcionarios, "João");

            // 3.3  Imprimir todos os funcionarios
            System.out.println("- 3.3 LISTA DE FUNCIONÁRIOS ---");
            imprimirFuncionarios(funcionarios);

            // 3.4  Aumento de 10% no slario
            aplicarAumento(funcionarios, new BigDecimal("0.10"));

            // 3.5  Agrupar por função
            Map<String, List<Funcionario>> agrupados = agruparPorFuncao(funcionarios);

            // 3.6  Imprimir os agrupados
            System.out.println("\n- 3.6 FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO ---");
            agrupados.forEach((funcao, lista) -> {
                System.out.println("Função: " + funcao);
                lista.forEach(f -> System.out.println(" - " + f.getNome()));
            });

            // 3.8  Aniversariantes mês 10 e 12, so possuia o do mes 10
            System.out.println("\n- 3.8 ANIVERSARIANTES (MÊS 10 e 12) ---");
            List<Funcionario> aniversariantes = filtrarAniversariantes(funcionarios, 10, 12);
            imprimirFuncionarios(aniversariantes);

            // 3.9  Funcionário mais velho
            System.out.println("\n- 3.9 FUNCIONÁRIO MAIS VELHO ---");
            Funcionario maisVelho = obterFuncionarioMaisVelho(funcionarios);
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println("Nome: " + maisVelho.getNome() + " | Idade: " + idade);

            // 3.10 - Ordem alfabética
            System.out.println("\n- 3.10 ORDEM ALFABÉTICA ---");
            List<Funcionario> ordemAlfabetica = obterListaOrdenadaPorNome(funcionarios);
            ordemAlfabetica.forEach(f -> System.out.println(f.getNome()));

            // 3.11 - Total dos salários
            System.out.println("\n- 3.11 TOTAL DOS SALARIOS ");
            BigDecimal total = calcularTotalSalarios(funcionarios);
            System.out.println("Total: R$ " + FORMATADOR_NUMERICO.format(total));

            // 3.12  Quantidade de salários mínimos
            System.out.println("\n- 3.12 SALÁRIOS MÍNIMOS POR FUNCIONÁRIO ");
            funcionarios.forEach(f -> {
                BigDecimal qtd = calcularQuantidadeSalariosMinimos(f.getSalario(), SALARIO_MINIMO);
                System.out.println(f.getNome() + " ganha " + qtd + " salários mínimos.");
            });

        } catch (Exception e) {
            System.err.println("Erro na execução: " + e.getMessage());
        }
    }

    public static List<Funcionario> inicializarListaFuncionarios() {
        return new ArrayList<>(Arrays.asList(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));
    }

    public static void removerFuncionarioPorNome(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase(nome));
    }

    public static void aplicarAumento(List<Funcionario> funcionarios, BigDecimal percentual) {
        funcionarios.forEach(f -> {
            BigDecimal aumento = f.getSalario().multiply(percentual);
            f.setSalario(f.getSalario().add(aumento));
        });
    }

    public static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    public static List<Funcionario> filtrarAniversariantes(List<Funcionario> funcionarios, int... meses) {
        List<Integer> listaMeses = Arrays.stream(meses).boxed().collect(Collectors.toList());
        return funcionarios.stream()
                .filter(f -> listaMeses.contains(f.getDataNascimento().getMonthValue()))
                .collect(Collectors.toList());
    }

    public static Funcionario obterFuncionarioMaisVelho(List<Funcionario> funcionarios) {
        return Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
    }

    public static List<Funcionario> obterListaOrdenadaPorNome(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());
    }

    public static BigDecimal calcularTotalSalarios(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal calcularQuantidadeSalariosMinimos(BigDecimal salario, BigDecimal salarioMinimo) {
        return salario.divide(salarioMinimo, 2, RoundingMode.HALF_UP);
    }

    public static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        for (Funcionario f : funcionarios) {
            System.out.println(String.format("Nome: %s | Data Nasc.: %s | Salário: R$ %s | Função: %s",
                    f.getNome(),
                    f.getDataNascimento().format(FORMATADOR_DATA),
                    FORMATADOR_NUMERICO.format(f.getSalario()),
                    f.getFuncao()));
        }
    }
}