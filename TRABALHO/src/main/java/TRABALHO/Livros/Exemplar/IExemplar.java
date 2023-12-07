package TRABALHO.Livros.Exemplar;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.Livros.EstadoExemplar.IExemplarEstado;
import TRABALHO.Livros.Livro.ILivro;
import TRABALHO.Livros.Livro.Livro;
import TRABALHO.Usuarios.IUsuario;

public interface IExemplar extends ILivro {

    default void emprestar(int codigoUsuario, IBancoDeDados db) {
        getEstado().emprestar(codigoUsuario, db);
    }

    default void devolver() {
        getEstado().devolver();
    }

    default String getEstadoDescricao() {
        return getEstado().getClass().getSimpleName();
    }

    public IUsuario getMutuario();

    public void setEstado(IExemplarEstado estado);

    public IExemplarEstado getEstado();

    default int getCodigoLivro() {
        return getLivro().getCodigo();
    }

    public Livro getLivro();

    default String getTitulo() {
        return getLivro().getTitulo();
    }

    default String getEditora() {
        return getLivro().getEditora();
    }

    default String getAutores() {
        return getLivro().getAutores();
    }

    default String getEdicao() {
        return getLivro().getEdicao();
    }

    default String getAnoPublicacao() {
        return getLivro().getAnoPublicacao();
    }

    default Emprestimo getEmprestimo() {
        return getEstado().getEmprestimo();
    }
}
