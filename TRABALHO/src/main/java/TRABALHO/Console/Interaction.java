package TRABALHO.Console;

import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;
import java.util.Arrays;

public class Interaction {

    public static void waitRequests(SistemaBiblioteca sistemaBiblioteca) {
        Mensagens.LimparTela();
        Mensagens.MensagemAjuda();
        while (true) {
            String[] inputArray = System.console().readLine().split(" ");

            String command = inputArray[0];
            String[] args = Arrays.copyOfRange(inputArray, 1, inputArray.length);

            Mensagens.LimparTela();
            Mensagens.MensagemAjuda();
            sistemaBiblioteca.executeCommand(command, args);
        }
    }
}
