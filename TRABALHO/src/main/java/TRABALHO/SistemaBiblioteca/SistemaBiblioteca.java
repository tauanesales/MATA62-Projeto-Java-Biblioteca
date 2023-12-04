package TRABALHO.SistemaBiblioteca;

import TRABALHO.BancoDeDados.BancoDeDados;
import TRABALHO.Console.Mensagens;
import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.Livros.Exemplar;
import TRABALHO.Usuarios.IUsuario;

import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoBase;
import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoDevolucao;
import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoEmprestimo;

public class SistemaBiblioteca {
    private static SistemaBiblioteca instance;
    public BancoDeDados bancoDeDados;

    private SistemaBiblioteca() {
        bancoDeDados = BancoDeDados.getInstance();
    }

    public static SistemaBiblioteca getInstance() {
        if (instance == null) {
            instance = new SistemaBiblioteca();
        }
        return instance;
    }

    public void realizarEmprestimo(int codigoUsuario, int codigoLivro) {
        IUsuario usuario = BancoDeDados.getInstance().getUsuario(codigoUsuario);
        Exemplar exemplar = BancoDeDados.getInstance().getExemplarDisponivelPorCodigoLivro(codigoLivro);

        try {
            ValidacaoEmprestimo.validarPodeEmprestarExemplarParaUsuario(codigoUsuario, codigoLivro);
        } catch (ValidacaoBase.SistemaBibliotecaException e) {
            Mensagens.MensagemDeErro("Não foi possível realizar o empréstimo", e.getMessage(), usuario, exemplar);
            return;
        }

        Emprestimo emprestimo = new Emprestimo(exemplar, usuario);
        BancoDeDados.getInstance().add(emprestimo);

        Mensagens.MensagemSucessoEmprestimoDevolucao("Empréstimo realizado com sucesso", usuario, exemplar, emprestimo);
    }

    public void realizarDevolucao(int codigoUsuario, int codigoLivro) {
        try {
            ValidacaoDevolucao.validarPodeDevolverExemplar(codigoUsuario, codigoLivro);
        } catch (ValidacaoBase.SistemaBibliotecaException e) {
            Mensagens.MensagemDeErro("Não foi possível realizar a devolução.", e.getMessage(),
                    BancoDeDados.getInstance().getUsuario(codigoUsuario), BancoDeDados.getInstance().getLivro(codigoLivro));
            return;
        }

        IUsuario usuario = BancoDeDados.getInstance().getUsuario(codigoUsuario);
        Emprestimo emprestimo = usuario.obterEmprestimoEmAbertoPorCodigoDoLivro(codigoLivro);
        Exemplar exemplar = emprestimo.getExemplar();

        emprestimo.setDevolvido(true);

        Mensagens.MensagemSucessoEmprestimoDevolucao("Devolução realizada com sucesso", usuario, exemplar, emprestimo);

    }
}
