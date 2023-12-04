package TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca;

import TRABALHO.BancoDeDados.BancoDeDados;

public class ValidacaoDevolucao extends ValidacaoBase {
    public static class DevolucaoException extends SistemaBibliotecaException {
        public DevolucaoException(String message) {
            super(message);
        }
    }

    public static void validarPodeDevolverExemplar(int codigoUsuario, int codigoLivro) throws SistemaBibliotecaException {
        validarUsuario(codigoUsuario);
        validarLivro(codigoLivro);
        validarUsuarioTemEmprestimoDoLivro(codigoUsuario, codigoLivro);
    }

    public static void validarUsuarioTemEmprestimoDoLivro(int codigoUsuario, int codigoLivro) throws SistemaBibliotecaException {
        if (!BancoDeDados.getInstance().getUsuario(codigoUsuario).jaTemEmprestimoDoLivroEmAberto(codigoLivro))
            throw new DevolucaoException("Usuário não possui empréstimo do livro em aberto.");
    }
}
