package TRABALHO.Usuarios;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.Reserva.Reserva;

import java.util.Date;
import java.util.List;

public abstract class Usuario implements IUsuario {
    private int codigoUsuario;
    private String nome;
    private IBancoDeDados db;
    private int quantidadeDeVezesQueFoiNotificado;

    public Usuario(int codigoUsuario, String nome, IBancoDeDados db) {
        this.codigoUsuario = codigoUsuario;
        this.nome = nome;
        this.db = db;
        this.quantidadeDeVezesQueFoiNotificado = 0;
    }

    public int getCodigo() {
        return codigoUsuario;
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
                emprestimo -> emprestimo.getExemplar().getCodigoLivro() == codigoLivro).findFirst().orElse(null);
    }

    public int quantidadeDeEmprestimosEmAberto() {
        return this.obterEmprestimos(true).size();
    }

    public boolean atingiuLimiteMaximoDeEmprestimos() {
        return this.quantidadeDeEmprestimosEmAberto() >= this.maxEmprestimos();
    }

    public boolean jaTemEmprestimoDoLivroEmAberto(int codigoLivro) {
        return this.obterEmprestimos(true).stream().anyMatch(
                emprestimo -> emprestimo.getExemplar().getCodigoLivro() == codigoLivro);
    }

    public String toString() {
        return String.format(
                "Código Usuário: %d | Nome: %s | Quantidade de Notificações: %s | Tipo: %s",
                this.getCodigo(),
                this.getNome(), this.getQuantidadeDeVezesQueFoiNotificado(),
                this.getClass().getSimpleName());
    }

    public boolean atingiuLimiteMaximoDeReservas() {
        return this.obterReservasAtivas().size() >= this.maxReservas();
    }

    public List<Reserva> obterReservasAtivas() {
        return db.getReservasPorCodigoUsuario(true, this.getCodigo());
    }

    public boolean temReservaDoLivro(int codigoLivro) {
        return this.obterReservasAtivas().stream().anyMatch(
                reserva -> reserva.getLivro().getCodigo() == codigoLivro);
    }

    public int getQuantidadeDeVezesQueFoiNotificado() {
        return quantidadeDeVezesQueFoiNotificado;
    }

    public void notificar() {
        this.quantidadeDeVezesQueFoiNotificado++;
    }

    public List<Reserva> obterReservas(boolean apenasEmAberto) {
        return db.getReservasPorCodigoUsuario(apenasEmAberto, this.getCodigo());
    }
}
