package TRABALHO.SistemaBiblioteca;

import java.util.HashMap;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.Commands.DevolucaoCommand;
import TRABALHO.Commands.EmprestimoCommand;
import TRABALHO.Commands.VerLivroCommand;
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
import TRABALHO.Reserva.Reserva;
import TRABALHO.Usuarios.IUsuario;

import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoBase;
import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoBase.SistemaBibliotecaException;
import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoDevolucao;
import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoEmprestimo;
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
            commands.get(command).execute(args);
        } catch (NullPointerException e) {
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
            reserva.setAtiva(false);

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
        Exemplar exemplar = db.getExemplarDisponivelPorCodigoLivro(codigoLivro);

        Reserva reserva = new Reserva(usuario, exemplar);
        db.insert(reserva);

        Mensagens.MensagemSucessoBase("Reserva realizada com sucesso!", usuario, exemplar);
    }

    public void mostrarDadosDoUsuario(int codigo) {
        System.out.println("Não implementado");
    }

    public void mostrarDadosDoLivro(int codigo) {
        System.out.println("Não implementado");
    }

    public void observarReservasDeLivro(int codigoLivro, int codigoObservador) {
        System.out.println("Não implementado");
    }

    public void mostrarTodosOsDadosDoBanco() {
        Mensagens.mostrarTodosOsDadosDoBanco(db);
    }
}
