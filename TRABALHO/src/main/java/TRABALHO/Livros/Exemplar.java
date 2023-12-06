package TRABALHO.Livros;

public class Exemplar implements ILivro {
    private int codigoExemplar;
    private boolean disponivel;
    private ILivro livro;

    public Exemplar(int codigoExemplar, ILivro livro) {
        this.codigoExemplar = codigoExemplar;
        this.livro = livro;
        this.setDisponivel(true);
    }

    public int getCodigo() {
        return codigoExemplar;
    }

    public int getCodigoLivro() {
        return livro.getCodigo();
    }

    public ILivro getLivro() {
        return livro;
    }
    
    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
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
}
