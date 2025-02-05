package TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca;

import TRABALHO.BancoDeDados.IBancoDeDados;

public class ValidacaoDevolucao extends ValidacaoBase {
    public static class DevolucaoException extends SistemaBibliotecaException {
        public DevolucaoException(String message) {
            super(message);
        }
    }

    public static void validarPodeDevolverExemplar(int codigoUsuario, int codigoLivro, IBancoDeDados db)
            throws SistemaBibliotecaException {
        validarUsuarioExiste(codigoUsuario, db);
        validarLivroExiste(codigoLivro, db);
        validarUsuarioTemEmprestimoDoLivro(codigoUsuario, codigoLivro, db);
    }

    public static void validarUsuarioTemEmprestimoDoLivro(int codigoUsuario, int codigoLivro, IBancoDeDados db)
            throws SistemaBibliotecaException {
        if (!db.getUsuario(codigoUsuario).jaTemEmprestimoDoLivroEmAberto(codigoLivro))
            throw new DevolucaoException("Usuário não possui empréstimo do livro em aberto.");
    }
}
