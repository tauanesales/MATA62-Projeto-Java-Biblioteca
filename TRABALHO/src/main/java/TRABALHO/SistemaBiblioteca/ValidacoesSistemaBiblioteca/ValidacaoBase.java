package TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca;

import TRABALHO.BancoDeDados.IBancoDeDados;

public class ValidacaoBase {
    public static class SistemaBibliotecaException extends Exception {
        public SistemaBibliotecaException(String message) {
            super(message);
        }
    }

    public static void validarUsuario(int codigoUsuario, IBancoDeDados db) throws SistemaBibliotecaException {
        if (db.getUsuario(codigoUsuario) == null)
            throw new SistemaBibliotecaException("Usuário não existe");
    }

    public static void validarLivro(int codigoLivro, IBancoDeDados db) throws SistemaBibliotecaException {
        if (!db.livroExiste(codigoLivro))
            throw new SistemaBibliotecaException("Livro não existe na biblioteca");
    }

}
