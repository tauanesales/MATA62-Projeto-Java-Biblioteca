package TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca;

import TRABALHO.BancoDeDados.BancoDeDados;
import TRABALHO.Usuarios.IUsuario;

public class ValidacaoEmprestimo extends ValidacaoBase {
    public static class EmprestimoException extends SistemaBibliotecaException {
        public EmprestimoException(String message) {
            super(message);
        }
    }

    public static void validarPodeEmprestarExemplarParaUsuario(int codigoUsuario, int codigoLivro)
            throws SistemaBibliotecaException {
        validarUsuario(codigoUsuario);
        validarLivro(codigoLivro);
        validarExemplarDisponivel(codigoLivro);

        IUsuario usuario = BancoDeDados.getInstance().getUsuario(codigoUsuario);
        validarUsuarioSemAtraso(usuario);
        validarUsuarioNaoAtingiuLimiteMaximoDeEmprestimos(usuario);
        validarUsuarioNaoTemEmprestimoDoLivro(usuario, codigoLivro);
    }

    public static void validarExemplarDisponivel(int codigoLivro) throws EmprestimoException {
        if (!BancoDeDados.getInstance().temExemplarDisponivel(codigoLivro))
            throw new EmprestimoException("Exemplar não está disponível");
    }

    public static void validarUsuarioSemAtraso(IUsuario usuario) throws EmprestimoException {
        if (usuario.temAtraso())
            throw new EmprestimoException("Usuário está com atraso");
    }

    public static void validarUsuarioNaoAtingiuLimiteMaximoDeEmprestimos(IUsuario usuario) throws EmprestimoException {
        if (usuario.atingiuLimiteMaximoDeEmprestimos())
            throw new EmprestimoException("Usuário atingiu o limite máximo de empréstimos");
    }

    public static void validarUsuarioNaoTemEmprestimoDoLivro(IUsuario usuario, int codigoLivro)
            throws EmprestimoException {
        if (usuario.jaTemEmprestimoDoLivroEmAberto(codigoLivro))
            throw new EmprestimoException("Usuário já possui empréstimo do livro");
    }
}
