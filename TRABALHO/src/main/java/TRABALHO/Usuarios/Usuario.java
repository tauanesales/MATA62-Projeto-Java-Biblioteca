package TRABALHO.Usuarios;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.Reserva.Reserva;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Usuario implements IUsuario {
    private int codigo_identificador;
    private String nome;
    private IBancoDeDados db;

    public Usuario(int codigo_identificador, String nome, IBancoDeDados db) {
        this.codigo_identificador = codigo_identificador;
        this.nome = nome;
        this.db = db;
    }

    public int getCodigo() {
        return codigo_identificador;
    }

    public String getNome() {
        return nome;
    }

    public boolean temAtraso() {
        List<Emprestimo> emprestimosUsuario = this.obterEmprestimos(true);
        return emprestimosUsuario.stream().anyMatch(
                emprestimo -> !emprestimo.isDevolvido() &&
                        emprestimo.getDataDevolucao().before(new Date()));
    }

    public List<Emprestimo> obterEmprestimos(boolean apenasEmAberto) {
        return db.getEmprestimos(apenasEmAberto, this.getCodigo());
    }

    public Emprestimo obterEmprestimoEmAbertoPorCodigoDoLivro(int codigoLivro) {
        return this.obterEmprestimos(true).stream().filter(
                emprestimo -> emprestimo.getExemplar().getCodigo() == codigoLivro).findFirst().orElse(null);
    }

    public int quantidadeDeEmprestimosEmAberto() {
        return this.obterEmprestimos(true).size();
    }

    public boolean atingiuLimiteMaximoDeEmprestimos() {
        return this.quantidadeDeEmprestimosEmAberto() >= this.maxEmprestimos();
    }

    public boolean jaTemEmprestimoDoLivroEmAberto(int codigoLivro) {
        return this.obterEmprestimos(true).stream().anyMatch(
                emprestimo -> emprestimo.getExemplar().getCodigo() == codigoLivro);
    }

    public String toString() {
        return String.format("Código: %d | Nome: %s | Tipo: %s", this.getCodigo(), this.getNome(), this.getClass().getSimpleName());
    }

    public boolean atingiuLimiteMaximoDeReservas() {
        return this.obterReservasAtivas().size() >= this.maxReservas();
    }

    public List<Reserva> obterReservasAtivas() {
        return db.getReservasPorCodigoUsuario(true, this.getCodigo());
    }
}
