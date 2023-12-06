package TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca;

import TRABALHO.BancoDeDados.IBancoDeDados;

public class ValidacaoObserver extends ValidacaoBase {
    public static class ObserverException extends SistemaBibliotecaException {
        public ObserverException(String message) {
            super(message);
        }
    }

    public static void validarPodeSeTornarObservador(int codigoObservador, int codigoLivro, IBancoDeDados db) throws SistemaBibliotecaException {
        validarUsuario(codigoObservador, db);
        validarLivro(codigoLivro, db);
        validarUsuarioPodeObservar(codigoObservador, db);
        validarUsuarioJaEhObservador(codigoObservador, codigoLivro, db);
    }

    private static void validarUsuarioJaEhObservador(int codigoObservador, int codigoLivro, IBancoDeDados db) throws ObserverException {
        if (db.getLivro(codigoLivro).getObservadores().stream().anyMatch(usuario -> usuario.getCodigo() == codigoObservador))
            throw new ObserverException("Usuário já é observador");
    }

    private static void validarUsuarioPodeObservar(int codigoObservador, IBancoDeDados db) throws ObserverException {
        if (!db.getUsuario(codigoObservador).podeSerObservador())
            throw new ObserverException("Usuário não pode ser observador");
    }
}
