package TRABALHO.Livros.Exemplar;

import TRABALHO.Livros.EstadoExemplar.ExemplarDisponivel;
import TRABALHO.Livros.EstadoExemplar.IExemplarEstado;
import TRABALHO.Livros.Livro.ILivroObservavel;

public class Exemplar implements IExemplarEmprestavel {
    private IExemplarEstado estado;

    private int codigoExemplar;
    private ILivroObservavel livro;

    public Exemplar(int codigoExemplar, ILivroObservavel livro) {
        this.codigoExemplar = codigoExemplar;
        this.livro = livro;
        setEstado(new ExemplarDisponivel(this));
    }

    public void setEstado(IExemplarEstado estado) {
        this.estado = estado;
    }

    public int getCodigo() {
        return codigoExemplar;
    }

    public ILivroObservavel getLivro() {
        return livro;
    }

    public IExemplarEstado getEstado() {
        return estado;
    }

    public String toString() {
        return "Código Livro: " + getLivro().getCodigo() + " | " +
                "Código Exemplar: " + getCodigo() + " | " +
                "Livro: " + getTitulo() + " | " +
                "Editora: " + getEditora() + " | " +
                "Autores: " + getAutores() + " | " +
                "Edição: " + getEdicao() + " | " +
                "Ano de publicação: " + getAnoPublicacao() + " | " +
                "Disponível: " + (isDisponivel() ? "Sim" : "Não");
    }
}
