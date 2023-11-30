package BancoDeDados;

import java.util.List;

import SistemaBiblioteca.Exemplar;
import SistemaBiblioteca.IEntidadeBiblioteca;

public interface IBancoDeDados {
    public boolean add(IEntidadeBiblioteca object);

    public List<? extends IEntidadeBiblioteca> getAll(Class<? extends IEntidadeBiblioteca> tabela);

    public IEntidadeBiblioteca getFirtById(Class<? extends IEntidadeBiblioteca> tabela, int id);

    public IEntidadeBiblioteca[] getAllById(Class<? extends IEntidadeBiblioteca> tabela, int id);

    public Exemplar getExemplar(int codigoExemplar);

}
