package TRABALHO.SistemaBiblioteca;

import java.util.HashMap;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.Commands.DevolucaoCommand;
import TRABALHO.Commands.EmprestimoCommand;
import TRABALHO.Commands.VerLivroCommand;
import TRABALHO.Commands.VerNotificacoesCommand;
import TRABALHO.Commands.VerUsuarioCommand;
import TRABALHO.Commands.ICommand;
import TRABALHO.Commands.ObservarLivroCommand;
import TRABALHO.Commands.ICommand.InvalidNumberOfArgsException;
import TRABALHO.Commands.ReservarCommand;
import TRABALHO.Commands.SaiCommand;
import TRABALHO.Commands.VerBancoDeDadosCommand;
import TRABALHO.Console.Mensagens;
import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.Livros.Exemplar;
import TRABALHO.Livros.ILivro;
import TRABALHO.Livros.Livro;
import TRABALHO.Reserva.Reserva;
import TRABALHO.Usuarios.IUsuario;

import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoBase;
import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoBase.SistemaBibliotecaException;
import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoCommand.CommandException;
import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoDevolucao;
import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoCommand;
import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoEmprestimo;
import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoObserver;
import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoReservas;

public class SistemaBiblioteca {
    private static SistemaBiblioteca instance;
    private IBancoDeDados db;
    private HashMap<String, ICommand> commands;

    private SistemaBiblioteca(IBancoDeDados db) {
        this.db = db;
        this.commands = new HashMap<String, ICommand>();
        addCommand("emp", new EmprestimoCommand(this, db));
        addCommand("dev", new DevolucaoCommand(this, db));
        addCommand("res", new ReservarCommand(this, db));
        addCommand("obs", new ObservarLivroCommand(this, db));
        addCommand("usu", new VerUsuarioCommand(this, db));
        addCommand("liv", new VerLivroCommand(this, db));
        addCommand("ntf", new VerNotificacoesCommand(this, db));
        addCommand("all", new VerBancoDeDadosCommand(this, db));
        addCommand("sai", new SaiCommand());
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

    public void executeCommand(String command, String... args) {
        try {
            ICommand concreteCommand = commands.get(command);
            ValidacaoCommand.validarCommand(concreteCommand);
            concreteCommand.execute(args);
        } catch (CommandException e) {
            Mensagens.MensagemDeErro("Não foi possível processar seu pedido.", "Comando \"" + command + "\" inválido.",
                    null, null);
        } catch (NumberFormatException e) {
            Mensagens.MensagemDeErro("Não foi possível processar seu pedido.",
                    "Esperava números como argumentos.",
                    null, null);
        } catch (InvalidNumberOfArgsException e) {
            Mensagens.MensagemDeErro("Não foi possível processar seu pedido.", e.getMessage(), null, null);
        }
    }

    public void realizarEmprestimo(int codigoUsuario, int codigoLivro) {
        try {
            ValidacaoEmprestimo.validarPodeEmprestarExemplarParaUsuario(codigoUsuario, codigoLivro, db);
        } catch (SistemaBibliotecaException e) {
            Mensagens.MensagemDeErro("Não foi possível realizar o empréstimo", e.getMessage(),
                    db.getUsuario(codigoUsuario),
                    db.getLivro(codigoLivro));
            return;
        }

        IUsuario usuario = db.getUsuario(codigoUsuario);
        Exemplar exemplar = db.getExemplarDisponivelPorCodigoLivro(codigoLivro);

        Emprestimo emprestimo = new Emprestimo(exemplar, usuario);
        Reserva reserva = db.getReservaAtiva(codigoLivro, codigoUsuario);

        if (reserva != null)
            reserva.removerReserva();

        db.insert(emprestimo);

        Mensagens.MensagemSucessoBase("Empréstimo realizado com sucesso!", usuario, exemplar,
                emprestimo);
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

        emprestimo.setDevolvido(true);

        Mensagens.MensagemSucessoBase("Devolução realizada com sucesso!", usuario,
                emprestimo.getExemplar(), emprestimo);
    }

    public void realizarReserva(int codigoUsuario, int codigoLivro) {
        try {
            ValidacaoReservas.validarPodeReservarExemplar(codigoUsuario, codigoLivro, db);
        } catch (SistemaBibliotecaException e) {
            Mensagens.MensagemDeErro("Não foi possível realizar a reserva.", e.getMessage(),
                    db.getUsuario(codigoUsuario), db.getLivro(codigoLivro));
            return;
        }

        IUsuario usuario = db.getUsuario(codigoUsuario);
        Livro livro = db.getLivro(codigoLivro);

        Reserva reserva = new Reserva(usuario, livro);
        db.insert(reserva);

        Mensagens.MensagemSucessoBase("Reserva realizada com sucesso!", usuario, livro);
    }

    public void mostrarDadosDoUsuario(int codigoUsuario) {
        System.out.println("Não implementado");
    }

    public void mostrarDadosDoLivro(int codigoLivro) {
        System.out.println("Não implementado");
    }

    public void observarReservasDeLivro(int codigoLivro, int codigoObservador) {
        try {
            ValidacaoObserver.validarPodeSeTornarObservador(codigoObservador, codigoLivro, db);
        } catch (SistemaBibliotecaException e) {
            Mensagens.MensagemDeErro("Não é possível se tornar observador.", e.getMessage(),
                    db.getUsuario(codigoObservador), db.getLivro(codigoLivro));
            return;
        }

        IUsuario usuario = db.getUsuario(codigoObservador);
        Livro livro = db.getLivro(codigoLivro);

        livro.adicionarObservador(usuario);

        Mensagens.MensagemSucessoBase("Sucesso: usuário se tornou observador.", usuario, livro);
    }

    public void mostrarTodosOsDadosDoBanco() {
        Mensagens.mostrarTodosOsDadosDoBanco(db);
    }

    public void mostrarNotificacoesDoUsuario(int codigoUsuario) {
        System.out.println("Não implementado");
    }
}
