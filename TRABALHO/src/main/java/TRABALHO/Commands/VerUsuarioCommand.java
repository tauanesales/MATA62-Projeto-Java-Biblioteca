package TRABALHO.Commands;

import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;

public class VerUsuarioCommand implements ICommand {
    private SistemaBiblioteca sistemaBiblioteca;

    public VerUsuarioCommand(SistemaBiblioteca sistemaBiblioteca) {
        this.sistemaBiblioteca = sistemaBiblioteca;
    }

    public void execute(String... args) {
        validarArgs(args);
        int codigo = Integer.parseInt(args[0]);
        sistemaBiblioteca.mostrarDadosDoUsuario(codigo);
    }

    public int getNumberOfArgs() {
        return 1;
    }
}
