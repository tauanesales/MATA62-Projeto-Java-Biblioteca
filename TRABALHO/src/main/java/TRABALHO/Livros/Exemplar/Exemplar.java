package TRABALHO.Livros.Exemplar;

import TRABALHO.Livros.EstadoExemplar.ExemplarDisponivel;
import TRABALHO.Livros.EstadoExemplar.IExemplarEstado;
import TRABALHO.Livros.Livro.Livro;
import TRABALHO.Usuarios.IUsuario;

public class Exemplar implements IExemplar {
    private IExemplarEstado estado;

    private int codigoExemplar;
    private Livro livro;

    public Exemplar(int codigoExemplar, Livro livro) {
        this.codigoExemplar = codigoExemplar;
        this.livro = livro;
        setEstado(new ExemplarDisponivel(this));
    }

    public void setEstado(IExemplarEstado estado) {
        this.estado = estado;
    }

    public IUsuario getMutuario() {
        return estado.getMutuario();
    }

    public int getCodigo() {
        return codigoExemplar;
    }

    public int getCodigoLivro() {
        return livro.getCodigo();
    }

    public Livro getLivro() {
        return livro;
    }

    public boolean isDisponivel() {
        return estado.isDisponivel();
    }

    public String toString() {
        return "Código Livro: " + livro.getCodigo() + " | " +
                "Código Exemplar: " + getCodigo() + " | " +
                "Livro: " + getTitulo() + " | " +
                "Editora: " + getEditora() + " | " +
                "Autores: " + getAutores() + " | " +
                "Edição: " + getEdicao() + " | " +
                "Ano de publicação: " + getAnoPublicacao() + " | " +
                "Disponível: " + (isDisponivel() ? "Sim" : "Não");
    }

    public String getTitulo() {
        return livro.getTitulo();
    }

    public String getEditora() {
        return livro.getEditora();
    }

    public String getAutores() {
        return livro.getAutores();
    }

    public String getEdicao() {
        return livro.getEdicao();
    }

    public String getAnoPublicacao() {
        return livro.getAnoPublicacao();
    }

    public IExemplarEstado getEstado() {
        return estado;
    }
}
