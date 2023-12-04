package TRABALHO.SistemaBiblioteca;

import java.util.HashMap;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.Commands.DevolucaoCommand;
import TRABALHO.Commands.EmprestimoCommand;
import TRABALHO.Commands.ICommand;
import TRABALHO.Commands.SairCommand;
import TRABALHO.Console.Mensagens;
import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.Livros.Exemplar;
import TRABALHO.Usuarios.IUsuario;

import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoBase;
import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoDevolucao;
import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoEmprestimo;

public class SistemaBiblioteca {
    private static SistemaBiblioteca instance;
    private IBancoDeDados db;
    private HashMap<String, ICommand> commands;

    private SistemaBiblioteca(IBancoDeDados db) {
        this.db = db;
        this.commands = new HashMap<String, ICommand>();
        addCommand("emp", new EmprestimoCommand(this, db));
        addCommand("dev", new DevolucaoCommand(this, db));
        addCommand("sair", new SairCommand(this, db));
    }

    public static SistemaBiblioteca getInstance(IBancoDeDados db) {
        if (instance == null) {
            instance = new SistemaBiblioteca(db);
        }
        return instance;
    }

    private void addCommand(String commandStr, ICommand commandCls) {
        commands.put(commandStr, commandCls);
    }

    public void executeCommand(String command, int codigoUsuario, int codigoLivro) {
        try {
            commands.get(command).execute(codigoUsuario, codigoLivro);
        } catch (NullPointerException e) {
            Mensagens.MensagemDeErro("Não foi possível processar seu pedido.", "Comando \"" + command + "\" inválido.", db.getUsuario(codigoUsuario),
                    db.getLivro(codigoLivro));
        }
    }

    public void realizarEmprestimo(int codigoUsuario, int codigoLivro) {
        IUsuario usuario = db.getUsuario(codigoUsuario);
        Exemplar exemplar = db.getExemplarDisponivelPorCodigoLivro(codigoLivro);

        try {
            ValidacaoEmprestimo.validarPodeEmprestarExemplarParaUsuario(codigoUsuario, codigoLivro, db);
        } catch (ValidacaoBase.SistemaBibliotecaException e) {
            Mensagens.MensagemDeErro("Não foi possível realizar o empréstimo", e.getMessage(), usuario,
                    db.getLivro(codigoLivro));
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
