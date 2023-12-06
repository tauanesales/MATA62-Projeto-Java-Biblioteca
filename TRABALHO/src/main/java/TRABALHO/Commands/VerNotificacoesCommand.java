package TRABALHO.Commands;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;

public class VerNotificacoesCommand implements ICommand {
    private SistemaBiblioteca sistemaBiblioteca;
    private IBancoDeDados db;

    public VerNotificacoesCommand(SistemaBiblioteca sistemaBiblioteca, IBancoDeDados db) {
        this.sistemaBiblioteca = sistemaBiblioteca;
        this.db = db;
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
