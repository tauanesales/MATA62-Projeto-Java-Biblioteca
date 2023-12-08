package TRABALHO.Livros.Livro;

import TRABALHO.Usuarios.IUsuario;
import java.util.List;

public interface ILivroObservavel extends ILivro {
  public void adicionarObservador(IUsuario usuario);

  public List<IUsuario> getObservadores();

  public int getQuantidadeDeReservas();

  public void incrementarQuantidadeDeReservas();

  public void decrementarQuantidadeDeReservas();
}
