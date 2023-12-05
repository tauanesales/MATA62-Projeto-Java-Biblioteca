package TRABALHO.Livros;

public class Exemplar extends Livro {
    private int codigoExemplar;
    private boolean disponivel;

    public Exemplar(int codigoLivro, int codigoExemplar, String titulo,
            String editora, String autores, String edicao,
            String anoDaPublicacao) {
        super(codigoLivro, titulo, editora, autores, edicao, anoDaPublicacao);
        this.codigoExemplar = codigoExemplar;
        this.setDisponivel(true);
    }

    public int getCodigoExemplar() {
        return codigoExemplar;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String toString() {
        return "Código Livro: " + getCodigo() + " | " +
                "Código Exemplar: " + getCodigoExemplar() + " | " +
                "Livro: " + getTitulo() + " | " +
                "Editora: " + getEditora() + " | " +
                "Autores: " + getAutores() + " | " +
                "Edição: " + getEdicao() + " | " +
                "Ano de publicação: " + getAnoPublicacao() + " | " +
                "Disponível: " + (isDisponivel() ? "Sim" : "Não");
    }
}
