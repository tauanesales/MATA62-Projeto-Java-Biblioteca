package TRABALHO.BancoDeDados;

import java.util.HashMap;
import java.util.List;

import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.Livros.Exemplar.Exemplar;
import TRABALHO.Livros.Livro.Livro;
import TRABALHO.Reserva.Reserva;
import TRABALHO.SistemaBiblioteca.IEntidadeBiblioteca;
import TRABALHO.Usuarios.IUsuario;

public interface IBancoDeDados {
    public static IBancoDeDados getInstance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void reset();

    public boolean insert(IEntidadeBiblioteca object);

    public <T extends IEntidadeBiblioteca> List<T> getAll(Class<T> entityType);

    public <T extends IEntidadeBiblioteca> T getFirstById(Class<T> tabela, int id);

    public IEntidadeBiblioteca[] getAllById(Class<? extends IEntidadeBiblioteca> tabela, int id);

    public Livro getLivro(int codigoLivro);

    public Exemplar getExemplar(int codigoExemplar, int codigoLivro);

    public Exemplar getExemplarDisponivelPorCodigoLivro(int codigoLivro);

    public boolean temExemplarDisponivel(int codigoLivro);

    public boolean livroExiste(int codigoLivro);

    public IUsuario getUsuario(int codigoUsuario);

    public HashMap<Class<? extends IEntidadeBiblioteca>, List<IEntidadeBiblioteca>> getTables();

    public List<Emprestimo> getEmprestimos(boolean apenasEmAberto, int codigoUsuario);

    public List<Reserva> getReservasPorCodigoUsuario(boolean reservaAtiva, int codigo);

    public List<Exemplar> getExemplaresDisponiveis(int codigoLivro);

    public Reserva getReservaAtiva(int codigoLivro, int codigoUsuario);

    public List<Reserva> getReservasPorCodigoLivro(boolean reservaAtiva, int codigoLivro);

    public Exemplar getExemplarEmprestado(int codigoLivro, int codigoUsuario);
}
