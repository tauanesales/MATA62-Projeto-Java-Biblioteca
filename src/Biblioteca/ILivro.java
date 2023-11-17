package Biblioteca;

public interface ILivro {
    public int getCodigo();

    public String getTitulo();

    public String getEditora();

    public String getAutores();

    public String getEdicao();

    public String getAnoPublicacao();

    public boolean isDisponivel();

    public void setDisponivel(boolean disponivel);
}
