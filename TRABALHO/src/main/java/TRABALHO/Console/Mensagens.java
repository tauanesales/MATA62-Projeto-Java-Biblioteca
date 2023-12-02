package TRABALHO.Console;

import TRABALHO.SistemaBiblioteca.ILivro;
import TRABALHO.Usuarios.IUsuario;

public class Mensagens {
    private static String delimiter = " | ";

    public static void MensagemDeErroEmprestimo(String mensagem, IUsuario usuario, ILivro livro) {
        String alert = String.join(delimiter,
                "Não foi possível realizar o empréstimo.",
                "Motivo: " + mensagem,
                "Usuário: " + usuario.getNome(),
                "Livro: " + (livro != null ? livro.getTitulo() : "N/A"));
        System.out.println(alert);
    }
}