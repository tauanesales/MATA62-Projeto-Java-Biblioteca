package TRABALHO.Commands;

import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;

public class ReservarCommand implements ICommand {
    private SistemaBiblioteca sistemaBiblioteca;

    public ReservarCommand(SistemaBiblioteca sistemaBiblioteca) {
        this.sistemaBiblioteca = sistemaBiblioteca;
    }

    public void execute(String... args) {
        validarArgs(args);
        int codigoUsuario = Integer.parseInt(args[0]);
        int codigoLivro = Integer.parseInt(args[1]);

        sistemaBiblioteca.realizarReserva(codigoUsuario, codigoLivro);
    }

    public int getNumberOfArgs() {
        return 2;
    }
}
