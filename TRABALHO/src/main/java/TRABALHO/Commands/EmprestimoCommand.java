package TRABALHO.Commands;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;

public class EmprestimoCommand implements ICommand {
    private SistemaBiblioteca sistemaBiblioteca;
    private IBancoDeDados db;

    public EmprestimoCommand(SistemaBiblioteca sistemaBiblioteca, IBancoDeDados db) {
        this.sistemaBiblioteca = sistemaBiblioteca;
        this.db = db;
    }

    public void execute(String... args) {
        validarArgs(args);

        int codigoUsuario = Integer.parseInt(args[0]);
        int codigoLivro = Integer.parseInt(args[1]);

        sistemaBiblioteca.realizarEmprestimo(codigoUsuario, codigoLivro);
    }

    public int getNumberOfArgs() {
        return 2;
    }
}
