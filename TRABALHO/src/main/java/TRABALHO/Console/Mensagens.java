package TRABALHO.Console;

import java.text.SimpleDateFormat;
import java.util.Date;

import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.Livros.Livro.ILivro;
import TRABALHO.Usuarios.IUsuario;

public class Mensagens {
    private static String delimiter = "\n";
    private static String dash = "------------------------------------------------------------";

    public static void MensagemAjuda() {
        String alert = String.join("\n",
                dash,
                "Comandos disponíveis:",
                "emp <codigoUsuario> <codigoLivro> - Realiza um empréstimo",
                "dev <codigoUsuario> <codigoLivro> - Realiza uma devolução",
                "res <codigoUsuario> <codigoLivro> - Realiza uma reserva",
                "obs <codigoUsuario> <codigoLivro> - Observa as reservas de um livro",
                "usu <codigoUsuario> - Mostra os dados de um usuário",
                "liv <codigoLivro> - Mostra os dados de um livro",
                "ntf <codigoUsuario> - Mostra as notificações de um usuário",
                "all - Mostra todos os dados do banco de dados",
                "sai - Encerra o programa",
                dash);
        System.out.println(alert);
    }

    public static void MensagemDeErro(String mensagem, String motivo, IUsuario usuario, ILivro livro) {
        String alert = String.join(delimiter,
                delimiter == "\n" ? dash : "",
                mensagem,
                "Motivo: " + motivo,
                "Usuário: " + (usuario != null ? usuario.getNome() : "N/A"),
                "Livro: " + (livro != null ? livro.getTitulo() : "N/A"),
                delimiter == "\n" ? dash : "");
        System.out.println(alert);
    }

    public static void MensagemSucessoBase(String mensagem, IUsuario usuario, ILivro livro) {
        String alert = String.join(delimiter,
                delimiter == "\n" ? dash : "",
                mensagem,
                "Usuário: " + (usuario != null ? usuario.getNome() : "N/A"),
                "Livro: " + (livro != null ? livro.getTitulo() : "N/A"),
                delimiter == "\n" ? dash : "");
        System.out.println(alert);
    }

    public static void MensagemSucessoBase(String mensagem, IUsuario usuario, ILivro livro,
            Emprestimo emprestimo) {
        String alert = String.join(delimiter,
                delimiter == "\n" ? dash : "",
                mensagem,
                "Usuário: " + (usuario != null ? usuario.getNome() : "N/A"),
                "Livro: " + (livro != null ? livro.getTitulo() : "N/A"),
                "Devolução até: " + (new SimpleDateFormat("dd/MM/yyyy")).format(emprestimo.getDataDevolucao()));

        if (emprestimo.isDevolvido()) {
            alert += delimiter + "Devolvido em: " + (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
        }
        alert += delimiter + (delimiter == "\n" ? dash : "");
        System.out.println(alert);
    }

    public static void LimparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void mostrarTodosOsDadosDoBanco(IBancoDeDados db) {
        db.getTables().forEach((key, values) -> {
            System.out.println(dash);
            System.out.println("Classe: " + key.getSimpleName());

            if (values != null) {
                values.forEach(value -> System.out.println(value.toString()));
            }
            System.out.println(dash);
        });
    }

    public static void mostrarNotificacoesDoUsuario(IUsuario usuario) {
        System.out.println(dash);
        System.out.println(usuario);
        System.out.println(dash);
    }

    public static void mostrarDadosDoUsuario(IUsuario usuario) {
        System.out.println(dash);
        System.out.println(usuario.getNome() + "\n");

        System.out.println("Emprestimos:");
        usuario.obterEmprestimos(false).forEach(emprestimo -> {
            System.out.println("  - " + emprestimo);
        });

        System.out.println("Reservas:");
        usuario.obterReservas(false).forEach(reserva -> {
            System.out.println("  - " + reserva);
        });
        System.out.println(dash);
    }
}
