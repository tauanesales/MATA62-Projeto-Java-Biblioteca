package TRABALHO.SistemaBiblioteca;

public class Exemplar extends Livro {
    private int codigoExemplar;
    private boolean disponivel;

    public Exemplar(int codigoLivro, int codigoExemplar, String titulo, String editora,
            String autores, String edicao, String anoDaPublicacao) {
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

}
