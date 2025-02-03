package TRABALHO.Usuarios;

import java.util.List;

import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.Reserva.Reserva;
import TRABALHO.SistemaBiblioteca.IEntidadeBiblioteca;

public interface IUsuarioAluno extends IEntidadeBiblioteca {
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

    //No momento, essa classe é usada somente pelo professor, mas foi mantida pela possbilidade
    //de outros tipos de usuario usarem, como os alunos
    default Boolean podeSerObservador() {
        return false;
    }

    //Essa classe é mantida, pois precisamos verificar se há possibilidade de priorização de interfaces
    default Boolean podeIgnorarListaDeReservas() {
        return false;
    }

    public List<Reserva> obterReservas(boolean apenasEmAberto);
}
