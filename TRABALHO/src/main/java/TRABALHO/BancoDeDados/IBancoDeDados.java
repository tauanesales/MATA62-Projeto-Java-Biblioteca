package TRABALHO.BancoDeDados;

import java.util.List;

import TRABALHO.Livros.Exemplar;
import TRABALHO.Livros.ILivro;
import TRABALHO.SistemaBiblioteca.IEntidadeBiblioteca;
import TRABALHO.Usuarios.IUsuario;

public interface IBancoDeDados {
    public static IBancoDeDados getInstance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void reset();

    public boolean insert(IEntidadeBiblioteca object);

    public <T extends IEntidadeBiblioteca> List<T> getAll(Class<T> entityType);

    public <T extends IEntidadeBiblioteca> T getFirtById(Class<T> tabela, int id);

    public IEntidadeBiblioteca[] getAllById(Class<? extends IEntidadeBiblioteca> tabela, int id);

    public ILivro getLivro(int codigoLivro);

    public Exemplar getExemplar(int codigoExemplar, int codigoLivro);

    public Exemplar getExemplarDisponivelPorCodigoLivro(int codigoLivro);

    public boolean temExemplarDisponivel(int codigoLivro);

    public boolean livroExiste(int codigoLivro);

    public IUsuario getUsuario(int codigoUsuario);

}
