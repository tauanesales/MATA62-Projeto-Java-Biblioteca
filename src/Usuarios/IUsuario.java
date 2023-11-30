package Usuarios;

import SistemaBiblioteca.IEntidadeBiblioteca;

public interface IUsuario extends IEntidadeBiblioteca {
    public String getNome();
    public boolean temAtraso();
    public int emprestimosAbertos();
}
