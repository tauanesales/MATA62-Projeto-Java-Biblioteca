package TRABALHO.Commands;

import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;

public class DevolucaoCommand implements ICommand {
    private SistemaBiblioteca sistemaBiblioteca;

    public DevolucaoCommand(SistemaBiblioteca sistemaBiblioteca) {
        this.sistemaBiblioteca = sistemaBiblioteca;
    }

    public void execute(String... args) {
        validarArgs(args);

        int codigoUsuario = Integer.parseInt(args[0]);
        int codigoLivro = Integer.parseInt(args[1]);

        sistemaBiblioteca.realizarDevolucao(codigoUsuario, codigoLivro);
    }

    public int getNumberOfArgs() {
        return 2;
    }
}
