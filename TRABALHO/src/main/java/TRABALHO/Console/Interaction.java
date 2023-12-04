package TRABALHO.Console;

import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;

public class Interaction {
    public static void waitRequests(SistemaBiblioteca sistemaBiblioteca) {
        Mensagens.MensagemAjuda();
        while (true) {
            String[] inputArray = System.console().readLine().split(" ");

            if (inputArray.length != 3 && !inputArray[0].equals("sair")) {
                Mensagens.MensagemAjuda();
                continue;
            }

            String command = inputArray[0];
            int codigoUsuario = 0;
            int codigoLivro = 0;

            if (inputArray.length == 3) {
                try {
                    codigoUsuario = Integer.parseInt(inputArray[1]);
                    codigoLivro = Integer.parseInt(inputArray[2]);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor, insira números inteiros válidos para usuário e livro.");
                    continue;
                }
            }
            sistemaBiblioteca.executeCommand(command, codigoUsuario, codigoLivro);
        }
    }
}
