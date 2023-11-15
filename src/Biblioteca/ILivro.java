package Biblioteca;

public interface ILivro {
    public int getCodigo();

    public String getTitulo();

    public boolean isDisponivel();

    public void setDisponivel(boolean disponivel);
}
