package Biblioteca;

public class Exemplar extends Livro {
    private int codigo_identificador_exemplar;
    private boolean disponivel;

    public Exemplar(int codigo_identificador_livro, int codigo_identificador_exemplar, String titulo, String editora,
            String autores, String edicao, String ano_da_publicacao) {
        super(codigo_identificador_livro, titulo, editora, autores, edicao, ano_da_publicacao);
        this.codigo_identificador_exemplar = codigo_identificador_exemplar;
        this.setDisponivel(true);
    }

    public int getCodigoExemplar() {
        return codigo_identificador_exemplar;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

}
