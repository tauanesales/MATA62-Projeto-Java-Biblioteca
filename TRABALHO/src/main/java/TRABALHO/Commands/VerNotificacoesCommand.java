package TRABALHO.Commands;

import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;

public class VerNotificacoesCommand implements ICommand {
    private SistemaBiblioteca sistemaBiblioteca;

    public VerNotificacoesCommand(SistemaBiblioteca sistemaBiblioteca) {
        this.sistemaBiblioteca = sistemaBiblioteca;
    }

    public void execute(String... args) {
        validarArgs(args);
        int codigoUsuario = Integer.parseInt(args[0]);

        sistemaBiblioteca.mostrarNotificacoesDoUsuario(codigoUsuario);
    }

    public int getNumberOfArgs() {
        return 1;
    }
}
