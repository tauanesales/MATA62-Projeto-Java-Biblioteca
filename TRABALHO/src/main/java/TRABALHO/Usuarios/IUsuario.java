package TRABALHO.Usuarios;

import java.util.List;

import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.SistemaBiblioteca.IEntidadeBiblioteca;

public interface IUsuario extends IEntidadeBiblioteca {
    public String getNome();

    public boolean temAtraso();

    public List<Emprestimo> obterEmprestimos(boolean apenasEmAberto);
    
    public Emprestimo obterEmprestimoEmAbertoPorCodigoDoLivro(int codigoLivro);

    public int quantidadeDeEmprestimosEmAberto();

    public int maxEmprestimos();

    public boolean atingiuLimiteMaximoDeEmprestimos();

    public long tempoDeEmprestimoMaximo();

    public boolean jaTemEmprestimoDoLivroEmAberto(int codigoLivro);

    public boolean atingiuLimiteMaximoDeReservas();

    default int maxReservas() {
        return 3;
    }

    public boolean temReservaDoLivro(int codigoLivro);

    public int getQuantidadeDeVezesQueFoiNotificado();

    public void notificar();

    default Boolean podeSerObservador() {
        return false;
    }
}
