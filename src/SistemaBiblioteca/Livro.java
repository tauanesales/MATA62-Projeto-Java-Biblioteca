package SistemaBiblioteca;

public class Livro implements ILivro {
    private int codigo_identificador;
    private String titulo;
    private String editora;
    private String autores;
    private String edicao;
    private String ano_da_publicacao;

    public Livro(int codigo_identificador, String titulo, String editora, String autores, String edicao,
            String ano_da_publicacao) {
        this.codigo_identificador = codigo_identificador;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.ano_da_publicacao = ano_da_publicacao;
    }

    public int getCodigo() {
        return codigo_identificador;
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
        return ano_da_publicacao;
    }
}
