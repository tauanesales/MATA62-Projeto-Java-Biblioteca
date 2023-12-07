package TRABALHO.Livros.EstadoExemplar;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.Usuarios.IUsuario;

public interface IExemplarEstado {
    public void emprestar(int codigoUsuario, IBancoDeDados db);

    public void devolver();

    default String getDescricao() {
        return this.getClass().getSimpleName();
    }

    public IUsuario getMutuario();

    public Emprestimo getEmprestimo();

    public boolean isDisponivel();
}
