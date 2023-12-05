package TRABALHO.Commands;

public interface ICommandAcao extends ICommand {
    public void execute(int codigoUsuario, int codigoLivro);
}
