package TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.Usuarios.IUsuario;

public class ValidacaoReservas extends ValidacaoBase {
    public static class ReservaException extends SistemaBibliotecaException {
        public ReservaException(String message) {
            super(message);
        }
    }

    public static void validarPodeReservarExemplar(int codigoUsuario, int codigoLivro, IBancoDeDados db)
            throws SistemaBibliotecaException {
        validarUsuario(codigoUsuario, db);
        validarLivro(codigoLivro, db);

        IUsuario usuario = db.getUsuario(codigoUsuario);
        validarUsuarioNaoTemEmprestimoDoLivro(usuario, codigoLivro);
        validarUsuarioNaoTemReservaDoLivro(usuario, codigoLivro);
        validarUsuarioNaoAtingiuOLimiteDeReservasDeLivro(usuario);
    }

    private static void validarUsuarioNaoTemReservaDoLivro(IUsuario usuario, int codigoLivro) throws ReservaException {
        if (usuario.temReservaDoLivro(codigoLivro))
            throw new ReservaException("O usuário já tem uma reserva do livro.");
    }

    private static void validarUsuarioNaoAtingiuOLimiteDeReservasDeLivro(IUsuario usuario) throws ReservaException {
        if (usuario.atingiuLimiteMaximoDeReservas())
            throw new ReservaException("O usuário atingiu o limite máximo de reservas de livros.");
    }
}
