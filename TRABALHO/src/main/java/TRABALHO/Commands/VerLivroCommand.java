package TRABALHO.Commands;

import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;

public class VerLivroCommand implements ICommand {
    private SistemaBiblioteca sistemaBiblioteca;

    public VerLivroCommand(SistemaBiblioteca sistemaBiblioteca) {
        this.sistemaBiblioteca = sistemaBiblioteca;
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
