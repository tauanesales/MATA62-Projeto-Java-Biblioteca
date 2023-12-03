package TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca;

import TRABALHO.BancoDeDados.MyORM;
import TRABALHO.Livros.ILivro;
import TRABALHO.Usuarios.IUsuario;

public class ValidacaoEmprestimo {
    public static class EmprestimoException extends Exception {
        public EmprestimoException(String message) {
            super(message);
        }
    }

    public static void validacaoPodeEmprestarExemplarParaUsuario(int codigoUsuario, int codigoLivro, IUsuario usuario, ILivro livro)
            throws EmprestimoException {
        validarUsuario(usuario);
        validarLivro(codigoLivro);
        validarExemplarDisponivel(codigoLivro);
        validarUsuarioSemAtraso(usuario);
        validarUsuarioNaoAtingiuLimiteMaximoDeEmprestimos(usuario);
        validarUsuarioNaoTemEmprestimoDoLivro(usuario, codigoLivro);
    }

    public static void validarUsuario(IUsuario usuario) throws EmprestimoException {
        if (usuario == null)
            throw new EmprestimoException("Usuário não existe");
    }

    public static void validarLivro(int codigoLivro) throws EmprestimoException {
        if (!MyORM.livroExiste(codigoLivro))
            throw new EmprestimoException("Livro não existe na biblioteca");
    }

    public static void validarExemplarDisponivel(int codigoLivro) throws EmprestimoException {
        if (!MyORM.temExemplarDisponivel(codigoLivro))
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

    public static void validarUsuarioNaoTemEmprestimoDoLivro(IUsuario usuario, int codigoLivro) throws EmprestimoException {
        if (usuario.jaTemEmprestimoDoLivro(codigoLivro))
            throw new EmprestimoException("Usuário já possui empréstimo do livro");
    }
}
