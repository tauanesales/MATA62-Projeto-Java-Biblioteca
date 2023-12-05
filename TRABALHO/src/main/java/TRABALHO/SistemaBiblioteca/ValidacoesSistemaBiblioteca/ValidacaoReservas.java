package TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.Usuarios.IUsuario;

public class ValidacaoReservas extends ValidacaoBase {
    public static class ReservaException extends SistemaBibliotecaException {
        public ReservaException(String message) {
            super(message);
        }
    }

    public static void validarPodeReservarExemplar(int codigoUsuario, int codigoLivro, IBancoDeDados db) throws SistemaBibliotecaException {
        validarUsuario(codigoUsuario, db);
        validarLivro(codigoLivro, db);

        IUsuario usuario = db.getUsuario(codigoUsuario);
        validarUsuarioNaoTemEmprestimoDoLivro(usuario, codigoLivro);
        validarUsuarioNaoAtingiuOLimiteDeReservasDeLivro(usuario);
    }

    private static void validarUsuarioNaoAtingiuOLimiteDeReservasDeLivro(IUsuario usuario) throws SistemaBibliotecaException {
        if (usuario.atingiuLimiteMaximoDeReservas())
            throw new ReservaException("O usuário atingiu o limite máximo de reservas de livros.");
    }
}
