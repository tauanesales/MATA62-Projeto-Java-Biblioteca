package TRABALHO.Commands;

import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;

public class VerBancoDeDadosCommand implements ICommand {
    private SistemaBiblioteca sistemaBiblioteca;

    public VerBancoDeDadosCommand(SistemaBiblioteca sistemaBiblioteca) {
        this.sistemaBiblioteca = sistemaBiblioteca;
    }

    public void execute(String... args) {
        validarArgs(args);

        sistemaBiblioteca.mostrarTodosOsDadosDoBanco();
    }
}
