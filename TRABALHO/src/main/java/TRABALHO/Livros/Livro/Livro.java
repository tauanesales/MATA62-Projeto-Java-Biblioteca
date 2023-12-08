package TRABALHO.Livros.Livro;

import java.util.ArrayList;
import java.util.List;

import TRABALHO.Usuarios.IUsuario;

public class Livro implements ILivroObservavel {
    private int codigoLivro;
    private String titulo;
    private String editora;
    private String autores;
    private String edicao;
    private String anoDaPublicacao;

    private List<IUsuario> observadores;
    private int quantidadeDeReservas;

    public Livro(int codigoLivro, String titulo, String editora, String autores, String edicao,
            String anoDaPublicacao) {
        this.codigoLivro = codigoLivro;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoDaPublicacao = anoDaPublicacao;

        this.observadores = new ArrayList<IUsuario>();
        this.quantidadeDeReservas = 0;
    }

    public int getCodigo() {
        return codigoLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEditora() {
        return editora;
    }

    public String getAutores() {
        return autores;
    }

    public String getEdicao() {
        return edicao;
    }

    public String getAnoPublicacao() {
        return anoDaPublicacao;
    }

    public String toString() {
        return "Código: " + codigoLivro + " | " +
                "Livro: " + titulo + " | " +
                "Editora: " + editora + " | " +
                "Autores: " + autores + " | " +
                "Edição: " + edicao + " | " +
                "Ano de publicação: " + anoDaPublicacao;
    }

    public void adicionarObservador(IUsuario usuario) {
        this.observadores.add(usuario);
    }

    public List<IUsuario> getObservadores() {
        return this.observadores;
    }

    public void notificarObservadores() {
        this.getObservadores().forEach(observador -> observador.notificar());
    }

    public int getQuantidadeDeReservas() {
        return this.quantidadeDeReservas;
    }

    public void incrementarQuantidadeDeReservas() {
        if (++quantidadeDeReservas > 2)
            this.notificarObservadores();
    }

    public void decrementarQuantidadeDeReservas() {
        this.quantidadeDeReservas--;
    }
}
