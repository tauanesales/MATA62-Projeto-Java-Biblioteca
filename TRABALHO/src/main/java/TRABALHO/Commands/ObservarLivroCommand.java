package TRABALHO.Commands;

import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;

public class ObservarLivroCommand implements ICommand {
    private SistemaBiblioteca sistemaBiblioteca;

    public ObservarLivroCommand(SistemaBiblioteca sistemaBiblioteca) {
        this.sistemaBiblioteca = sistemaBiblioteca;
    }

    public void execute(String... args) {
        validarArgs(args);

        int codigoObservador = Integer.parseInt(args[0]);
        int codigoLivro = Integer.parseInt(args[1]);

        sistemaBiblioteca.observarReservasDeLivro(codigoLivro, codigoObservador);
    }

    public int getNumberOfArgs() {
        return 2;
    }
}
