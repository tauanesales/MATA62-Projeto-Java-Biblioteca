package TRABALHO.Console;

import java.text.SimpleDateFormat;
import java.util.Date;

import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.Livros.ILivro;
import TRABALHO.Usuarios.IUsuario;

public class Mensagens {
    private static String delimiter = " | ";

    public static void MensagemAjuda() {
        String alert = String.join("\n",
                "------------------------------------------------------------",
                "Comandos disponíveis:",
                "emp <codigoUsuario> <codigoLivro> - Realiza um empréstimo",
                "dev <codigoUsuario> <codigoLivro> - Realiza uma devolução",
                "sair - Encerra o programa",
                "------------------------------------------------------------");
        System.out.println(alert);
    }

    public static void MensagemDeErro(String mensagem, String motivo, IUsuario usuario, ILivro livro) {
        String alert = String.join(delimiter,
                mensagem,
                "Motivo: " + motivo,
                "Usuário: " + (usuario != null ? usuario.getNome() : "N/A"),
                "Livro: " + (livro != null ? livro.getTitulo() : "N/A"));
        System.out.println(alert);
    }

    public static void MensagemSucessoEmprestimoDevolucao(String mensagem, IUsuario usuario, ILivro livro,
            Emprestimo emprestimo) {
        String alert = String.join(delimiter,
                mensagem,
                "Usuário: " + (usuario != null ? usuario.getNome() : "N/A"),
                "Livro: " + (livro != null ? livro.getTitulo() : "N/A"),
                "Devolução até: " + (new SimpleDateFormat("dd/MM/yyyy")).format(emprestimo.getDataDevolucao()));

        if (emprestimo.isDevolvido()) {
            alert += delimiter + "Devolvido em: " + (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
        }
        System.out.println(alert);
    }

}
