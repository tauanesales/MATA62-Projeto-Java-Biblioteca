package TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.Usuarios.IUsuario;
import TRABALHO.Usuarios.Professor;

public class ValidacaoEmprestimo extends ValidacaoBase {
    public static class EmprestimoException extends SistemaBibliotecaException {
        public EmprestimoException(String message) {
            super(message);
        }
    }

    public static void validarPodeEmprestarExemplarParaUsuario(int codigoUsuario, int codigoLivro, IBancoDeDados db)
            throws SistemaBibliotecaException {
        validarUsuario(codigoUsuario, db);
        validarLivro(codigoLivro, db);
        validarExemplarDisponivel(codigoLivro, db);
        validarReservasNaoImpedemEmprestimo(codigoLivro, codigoUsuario, db);

        IUsuario usuario = db.getUsuario(codigoUsuario);
        validarUsuarioSemAtraso(usuario);
        validarUsuarioNaoAtingiuLimiteMaximoDeEmprestimos(usuario);
        validarUsuarioNaoTemEmprestimoDoLivro(usuario, codigoLivro);
    }

    private static boolean usuarioEhProfessor(int codigoUsuario, IBancoDeDados db) {
        return db.getUsuario(codigoUsuario).podeIgnorarListaDeReservas();
    }

    private static boolean usuarioPossuiReservaAtiva(int codigoLivro, int codigoUsuario, IBancoDeDados db) {
        return db.getReservaAtiva(codigoLivro, codigoUsuario) != null;
    }

    private static boolean numeroDeReservasAtivasEhIgualOuMaiorAoNumeroDeExemplaresDisponiveis(int codigoLivro,
            IBancoDeDados db) {
        return db.getReservasPorCodigoLivro(true, codigoLivro).size() >= db.getExemplaresDisponiveis(codigoLivro).size();
    }

    private static void validarReservasNaoImpedemEmprestimo(
            int codigoLivro, int codigoUsuario, IBancoDeDados db) throws EmprestimoException {
        if (!usuarioEhProfessor(codigoUsuario, db) && !usuarioPossuiReservaAtiva(codigoLivro, codigoUsuario, db)
                && numeroDeReservasAtivasEhIgualOuMaiorAoNumeroDeExemplaresDisponiveis(codigoLivro, db))
            throw new EmprestimoException("Exemplares disponíveis já estão reservados");
    }

    public static void validarExemplarDisponivel(int codigoLivro, IBancoDeDados db) throws EmprestimoException {
        if (!db.temExemplarDisponivel(codigoLivro))
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
}
