package TrabalhoEngSoft;

class Livro {
    private int codigo;
    private String titulo;
    private boolean disponivel;

    public Livro(int codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.disponivel = true;
    }

    public int getCodigo() {
        return codigo;
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
