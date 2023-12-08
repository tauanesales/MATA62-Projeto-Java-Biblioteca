package TRABALHO.Commands;

import TRABALHO.Console.Mensagens;

public class SaiCommand implements ICommand {
    public SaiCommand() {
    }

    public void execute(String... args) {
        Mensagens.LimparTela();
        System.exit(0);
    }
}
