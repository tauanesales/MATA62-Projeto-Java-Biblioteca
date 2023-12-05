package TRABALHO.Commands;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;

public class EmprestimoCommand implements ICommandAcao {
    private SistemaBiblioteca sistemaBiblioteca;
    private IBancoDeDados db;


    public EmprestimoCommand(SistemaBiblioteca sistemaBiblioteca, IBancoDeDados db) {
        this.sistemaBiblioteca = sistemaBiblioteca;
        this.db = db;
    }

    public void execute(int codigoUsuario, int codigoLivro) {
        sistemaBiblioteca.realizarEmprestimo(codigoUsuario, codigoLivro);
    }    
}
