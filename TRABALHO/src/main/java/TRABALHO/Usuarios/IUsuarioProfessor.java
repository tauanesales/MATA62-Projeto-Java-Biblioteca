package TRABALHO.Usuarios;

import java.util.List;

import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.Reserva.Reserva;
import TRABALHO.SistemaBiblioteca.IEntidadeBiblioteca;

public interface IUsuarioProfessor extends IEntidadeBiblioteca {
    public String getNome();

    public boolean temAtraso();

    public List<Emprestimo> obterEmprestimos(boolean apenasEmAberto);
    
    public Emprestimo obterEmprestimoEmAbertoPorCodigoDoLivro(int codigoLivro);

    public int quantidadeDeEmprestimosEmAberto();

    //Esse método não é necessário, pois os professores podem fazer quantos 
    //empréstimos quiserem: public int maxEmprestimos();
    //esse método é retirado seguindo a mesma lógica: public boolean atingiuLimiteMaximoDeEmprestimos();

    public long tempoDeEmprestimoMaximo();

    public boolean jaTemEmprestimoDoLivroEmAberto(int codigoLivro);

    public boolean atingiuLimiteMaximoDeReservas();

    default int maxReservas() {
        return 3;
    }

    //Esse método não é necessário na interface do professor,
    //pois ele pode alugar mesmo sem reserva: public boolean temReservaDoLivro(int codigoLivro);

    public int getQuantidadeDeVezesQueFoiNotificado();

    public void notificar();

    default Boolean podeSerObservador() {
        return false;
    }
    
    default Boolean podeIgnorarListaDeReservas() {
        return false;
    }

    public List<Reserva> obterReservas(boolean apenasEmAberto);
}
