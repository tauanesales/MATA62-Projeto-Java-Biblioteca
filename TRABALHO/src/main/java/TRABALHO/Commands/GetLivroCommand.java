package TRABALHO.Commands;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;

public class GetLivroCommand implements ICommandVisualizacao {
    private SistemaBiblioteca sistemaBiblioteca;
    private IBancoDeDados db;

    public GetLivroCommand(SistemaBiblioteca sistemaBiblioteca, IBancoDeDados db) {
        this.sistemaBiblioteca = sistemaBiblioteca;
        this.db = db;
    }

    public void execute(int codigo) {
        sistemaBiblioteca.mostrarDadosDoLivro(codigo);
    }    
}
