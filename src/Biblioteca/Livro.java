package Biblioteca;

public class Livro implements ILivro {
    private int codigo_identificador;
    private String titulo;
    private boolean disponivel;

    public Livro(int codigo_identificador, String titulo) {
        this.codigo_identificador = codigo_identificador;
        this.titulo = titulo;
        this.disponivel = true;
    }

    public int getCodigo() {
        return codigo_identificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
