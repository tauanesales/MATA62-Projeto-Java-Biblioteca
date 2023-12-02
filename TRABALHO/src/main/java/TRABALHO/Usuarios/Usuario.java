package TRABALHO.Usuarios;

import TRABALHO.BancoDeDados.MyORM;
import TRABALHO.SistemaBiblioteca.Emprestimo;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Usuario implements IUsuario {
  private int codigo_identificador;
  private String nome;

  public Usuario(int codigo_identificador, String nome) {
    this.codigo_identificador = codigo_identificador;
    this.nome = nome;
  }

  public int getCodigo() { return codigo_identificador; }

  public String getNome() { return nome; }

  public boolean temAtraso() {
    List<Emprestimo> emprestimosUsuario = this.obterEmprestimos(true);
    return emprestimosUsuario.stream().anyMatch(
        emprestimo
        -> !emprestimo.isDevolvido() &&
               emprestimo.getDataDevolucao().before(new Date()));
  }

  public List<Emprestimo> obterEmprestimos(boolean apenasEmAberto) {
    return MyORM.getAll(Emprestimo.class)
        .stream()
        .filter(emprestimo
                -> emprestimo.getUsuario().getCodigo() == this.getCodigo())
        .filter(emprestimo
                -> !emprestimo.isDevolvido() && apenasEmAberto ||
                       !apenasEmAberto)
        .collect(Collectors.toList());
  }

  public int emprestimosAbertos() { return this.obterEmprestimos(true).size(); }

  public int maxEmprestimos() { return 0; }

  public boolean atingiuLimiteMaximoDeEmprestimos() {
    return this.emprestimosAbertos() >= this.maxEmprestimos();
  }

  public long tempoDeEmprestimoMaximo() { return 0; }

  public boolean jaTemEmprestimoDoLivro(int codigoLivro) {
    return this.obterEmprestimos(true).stream().anyMatch(
        emprestimo -> emprestimo.getExemplar().getCodigo() == codigoLivro);
  }
}
