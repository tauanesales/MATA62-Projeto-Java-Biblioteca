package TRABALHO.Usuarios;

import java.util.List;

import TRABALHO.SistemaBiblioteca.Emprestimo;
import TRABALHO.SistemaBiblioteca.IEntidadeBiblioteca;

public interface IUsuario extends IEntidadeBiblioteca {
    public String getNome();

    public boolean temAtraso();

    public List<Emprestimo> obterEmprestimos(boolean apenasEmAberto);

    public int quantidadeDeEmprestimosEmAberto();

    public int maxEmprestimos();

    public boolean atingiuLimiteMaximoDeEmprestimos();

    public long tempoDeEmprestimoMaximo();

    public boolean jaTemEmprestimoDoLivro(int codigoLivro);
}
