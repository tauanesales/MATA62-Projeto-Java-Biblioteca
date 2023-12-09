package TRABALHO.Livros.Exemplar;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.Livros.EstadoExemplar.IExemplarEstado;
import TRABALHO.Livros.Livro.ILivro;
import TRABALHO.Livros.Livro.ILivroObservavel;
import TRABALHO.Usuarios.IUsuario;

public interface IExemplarEmprestavel extends ILivro {

    default void emprestar(int codigoUsuario, IBancoDeDados db) {
        getEstado().emprestar(codigoUsuario, db);
    }

    default void devolver() {
        getEstado().devolver();
    }

    default String getEstadoDescricao() {
        return getEstado().getClass().getSimpleName();
    }

    default IUsuario getMutuario() {
        return getEstado().getMutuario();
    }

    public void setEstado(IExemplarEstado estado);

    public IExemplarEstado getEstado();

    default int getCodigoLivro() {
        return getLivro().getCodigo();
    }

    public ILivroObservavel getLivro();

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

    default boolean isDisponivel() {
        return getEstado().isDisponivel();
    }
}
