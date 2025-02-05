package TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoEmprestimo.EmprestimoException;
import TRABALHO.Usuarios.IUsuario;

public class ValidacaoBase {
    public static class SistemaBibliotecaException extends Exception {
        public SistemaBibliotecaException(String message) {
            super(message);
        }
    }

    public static void validarUsuarioExiste(int codigoUsuario, IBancoDeDados db) throws SistemaBibliotecaException {
        if (db.getUsuario(codigoUsuario) == null)
            throw new SistemaBibliotecaException("Usuário não existe");
    }

    public static void validarLivroExiste(int codigoLivro, IBancoDeDados db) throws SistemaBibliotecaException {
        if (!db.livroExiste(codigoLivro))
            throw new SistemaBibliotecaException("Livro não existe na biblioteca");
    }

    public static void validarUsuarioNaoTemEmprestimoDoLivro(IUsuario usuario, int codigoLivro)
            throws SistemaBibliotecaException {
        if (usuario.jaTemEmprestimoDoLivroEmAberto(codigoLivro))
            throw new EmprestimoException("Usuário já possui empréstimo do livro");
    }
}
