package TRABALHO.Commands;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;

public class ReservarCommand implements ICommandAcao {
    private SistemaBiblioteca sistemaBiblioteca;
    private IBancoDeDados db;

    public ReservarCommand(SistemaBiblioteca sistemaBiblioteca, IBancoDeDados db) {
        this.sistemaBiblioteca = sistemaBiblioteca;
        this.db = db;
    }

    public void execute(int codigoUsuario, int codigoLivro) {
        sistemaBiblioteca.realizarReserva(codigoUsuario, codigoLivro);
    }    
}
