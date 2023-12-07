package TRABALHO.Livros.EstadoExemplar;

import java.util.Date;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.Livros.Exemplar.Exemplar;
import TRABALHO.Usuarios.IUsuario;

public class ExemplarEmprestado implements IExemplarEstado {
    private Exemplar exemplar;
    private IUsuario mutuario;
    private Emprestimo emprestimo;

    public ExemplarEmprestado(Exemplar exemplar, IUsuario mutuario, IBancoDeDados db) {
        this.exemplar = exemplar;
        this.mutuario = mutuario;
        this.emprestimo = new Emprestimo(exemplar, mutuario);
        db.insert(this.emprestimo);
    }
    
    public ExemplarEmprestado(Exemplar exemplar, IUsuario mutuario, IBancoDeDados db, Date dataDevolucao) {
        this.exemplar = exemplar;
        this.mutuario = mutuario;
        this.emprestimo = new Emprestimo(exemplar, mutuario, dataDevolucao);
        db.insert(this.emprestimo);
    }


    public void emprestar(int codigoUsuario, IBancoDeDados db) {
        return;
    }

    public void devolver() {
        getEmprestimo().devolver();
        exemplar.setEstado(new ExemplarDisponivel(exemplar));
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public IUsuario getMutuario() {
        return mutuario;
    }

    public boolean isDisponivel() {
        return false;
    }
}
