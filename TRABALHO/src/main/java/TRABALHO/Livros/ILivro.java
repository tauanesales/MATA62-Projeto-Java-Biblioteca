package TRABALHO.Livros;

import TRABALHO.SistemaBiblioteca.IEntidadeBiblioteca;

public interface ILivro extends IEntidadeBiblioteca {
    public String getTitulo();

    public String getEditora();

    public String getAutores();

    public String getEdicao();

    public String getAnoPublicacao();
}
