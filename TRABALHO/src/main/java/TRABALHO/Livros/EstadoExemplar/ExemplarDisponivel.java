package TRABALHO.Livros.EstadoExemplar;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.Livros.Exemplar.Exemplar;
import TRABALHO.Usuarios.IUsuario;

public class ExemplarDisponivel implements IExemplarEstado {
    private Exemplar exemplar;

    public ExemplarDisponivel(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public void emprestar(int codigoUsuario, IBancoDeDados db) {
        exemplar.setEstado(new ExemplarEmprestado(exemplar, db.getUsuario(codigoUsuario), db));
    }

    public void devolver() {
        return;
    }

    public IUsuario getMutuario() {
        return null;
    }

    public Emprestimo getEmprestimo() {
        return null;
    }

    public boolean isDisponivel() {
        return true;
    }
}
