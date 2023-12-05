package TRABALHO.Commands;

public class SaiCommand implements ICommand {
    public SaiCommand() {
    }

    public void execute(String... args) {
        System.exit(0);
    }
}
