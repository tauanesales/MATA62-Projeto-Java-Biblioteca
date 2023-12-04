package TRABALHO.SistemaBiblioteca;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.Console.Mensagens;
import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.Livros.Exemplar;
import TRABALHO.Usuarios.IUsuario;

import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoBase;
import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoDevolucao;
import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoEmprestimo;

public class SistemaBiblioteca {
    private static SistemaBiblioteca instance;
    public IBancoDeDados db;

    private SistemaBiblioteca(IBancoDeDados db) {
        this.db = db;
    }

    public static SistemaBiblioteca getInstance(IBancoDeDados db) {
        if (instance == null) {
            instance = new SistemaBiblioteca(db);
        }
        return instance;
    }

    public void realizarEmprestimo(int codigoUsuario, int codigoLivro) {
        IUsuario usuario = db.getUsuario(codigoUsuario);
        Exemplar exemplar = db.getExemplarDisponivelPorCodigoLivro(codigoLivro);

        try {
            ValidacaoEmprestimo.validarPodeEmprestarExemplarParaUsuario(codigoUsuario, codigoLivro, db);
        } catch (ValidacaoBase.SistemaBibliotecaException e) {
            Mensagens.MensagemDeErro("Não foi possível realizar o empréstimo", e.getMessage(), usuario, db.getLivro(codigoLivro));
            return;
        }

        Emprestimo emprestimo = new Emprestimo(exemplar, usuario);
        db.insert(emprestimo);

        Mensagens.MensagemSucessoEmprestimoDevolucao("Empréstimo realizado com sucesso", usuario, exemplar, emprestimo);
    }

    public void realizarDevolucao(int codigoUsuario, int codigoLivro) {
        try {
            ValidacaoDevolucao.validarPodeDevolverExemplar(codigoUsuario, codigoLivro, db);
        } catch (ValidacaoBase.SistemaBibliotecaException e) {
            Mensagens.MensagemDeErro("Não foi possível realizar a devolução.", e.getMessage(),
                    db.getUsuario(codigoUsuario), db.getLivro(codigoLivro));
            return;
        }

        IUsuario usuario = db.getUsuario(codigoUsuario);
        Emprestimo emprestimo = usuario.obterEmprestimoEmAbertoPorCodigoDoLivro(codigoLivro);
        Exemplar exemplar = emprestimo.getExemplar();

        emprestimo.setDevolvido(true);

        Mensagens.MensagemSucessoEmprestimoDevolucao("Devolução realizada com sucesso", usuario, exemplar, emprestimo);

    }
}
