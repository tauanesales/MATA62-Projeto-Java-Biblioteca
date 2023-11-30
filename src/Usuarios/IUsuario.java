package Usuarios;

import java.util.List;

import SistemaBiblioteca.Emprestimo;
import SistemaBiblioteca.IEntidadeBiblioteca;

public interface IUsuario extends IEntidadeBiblioteca {
    public String getNome();

    public boolean temAtraso();

    public List<Emprestimo> obterEmprestimos(boolean apenasEmAberto);

    public int emprestimosAbertos();

    public int maxEmprestimos();

    public boolean atingiuLimiteMaximoDeEmprestimos();
}
