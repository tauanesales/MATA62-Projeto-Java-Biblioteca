package TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca;

import TRABALHO.Commands.ICommand;

public class ValidacaoCommand extends ValidacaoBase {
    public static class CommandException extends SistemaBibliotecaException {
        public CommandException(String message) {
            super(message);
        }
    }

    public static void validarCommand(ICommand command) throws CommandException {
        if (command == null)
            throw new CommandException("Comando inválido.");
    }
}
