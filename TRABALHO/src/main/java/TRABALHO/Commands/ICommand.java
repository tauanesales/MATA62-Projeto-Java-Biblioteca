package TRABALHO.Commands;

public interface ICommand {
    public class InvalidNumberOfArgsException extends IllegalArgumentException {
        public InvalidNumberOfArgsException(String message) {
            super(message);
        }
    }

    public void execute(String... args);

    default void validarArgs(String... args) {
        if (args.length != getNumberOfArgs()) {
            throw new InvalidNumberOfArgsException(
                    "Número de argumentos inválido. Esperava " + getNumberOfArgs() + " argumentos.");
        }
    }

    default int getNumberOfArgs() {
        return 0;
    }
}
