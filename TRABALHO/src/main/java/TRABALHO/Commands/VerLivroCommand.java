package TRABALHO.Commands;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;

public class VerLivroCommand implements ICommand {
    private SistemaBiblioteca sistemaBiblioteca;
    private IBancoDeDados db;

    public VerLivroCommand(SistemaBiblioteca sistemaBiblioteca, IBancoDeDados db) {
        this.sistemaBiblioteca = sistemaBiblioteca;
        this.db = db;
    }

    public void execute(String... args) {
        validarArgs(args);

        int codigo = Integer.parseInt(args[0]);

        sistemaBiblioteca.mostrarDadosDoLivro(codigo);
    }

    public int getNumberOfArgs() {
        return 1;
    }
}
