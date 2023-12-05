package TRABALHO.Livros;

public class Livro implements ILivro {
    private int codigoLivro;
    private String titulo;
    private String editora;
    private String autores;
    private String edicao;
    private String anoDaPublicacao;

    public Livro(int codigoLivro, String titulo, String editora, String autores, String edicao,
            String anoDaPublicacao) {
        this.codigoLivro = codigoLivro;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoDaPublicacao = anoDaPublicacao;
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
}
