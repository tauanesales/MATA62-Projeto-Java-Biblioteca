package TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca;

import TRABALHO.BancoDeDados.BancoDeDados;

public class ValidacaoBase {
    public static class SistemaBibliotecaException extends Exception {
        public SistemaBibliotecaException(String message) {
            super(message);
        }
    }
    
    public static void validarUsuario(int codigoUsuario) throws SistemaBibliotecaException {
        if (BancoDeDados.getInstance().getUsuario(codigoUsuario) == null)
            throw new SistemaBibliotecaException("Usuário não existe");
    }

    public static void validarLivro(int codigoLivro) throws SistemaBibliotecaException {
        if (!BancoDeDados.getInstance().livroExiste(codigoLivro))
            throw new SistemaBibliotecaException("Livro não existe na biblioteca");
    }

}
