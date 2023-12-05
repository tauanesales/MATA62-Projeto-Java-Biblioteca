package TRABALHO.Commands;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;

public class ReservarCommand implements ICommand {
    private SistemaBiblioteca sistemaBiblioteca;
    private IBancoDeDados db;

    public ReservarCommand(SistemaBiblioteca sistemaBiblioteca, IBancoDeDados db) {
        this.sistemaBiblioteca = sistemaBiblioteca;
        this.db = db;
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
