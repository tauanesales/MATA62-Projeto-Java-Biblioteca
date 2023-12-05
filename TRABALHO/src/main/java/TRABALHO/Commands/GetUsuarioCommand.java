package TRABALHO.Commands;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;

public class GetUsuarioCommand implements ICommandVisualizacao {
    private SistemaBiblioteca sistemaBiblioteca;
    private IBancoDeDados db;

    public GetUsuarioCommand(SistemaBiblioteca sistemaBiblioteca, IBancoDeDados db) {
        this.sistemaBiblioteca = sistemaBiblioteca;
        this.db = db;
    }

    public void execute(int codigo) {
        sistemaBiblioteca.mostrarDadosDoUsuario(codigo);
    }    
}
