package TRABALHO.BancoDeDados;

import TRABALHO.Livros.Exemplar;
import TRABALHO.Livros.Livro;
import TRABALHO.SistemaBiblioteca.IEntidadeBiblioteca;
import TRABALHO.Usuarios.IUsuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BancoDeDados implements IBancoDeDados {
    private static BancoDeDados instance;
    private HashMap<Class<? extends IEntidadeBiblioteca>, List<IEntidadeBiblioteca>> tables;

    private BancoDeDados() {
        initializeDatabase();
    }

    public static BancoDeDados getInstance() {
        if (instance == null) {
            instance = new BancoDeDados();
        }
        return instance;
    }

    public void reset() {
        clearDatabase();
        System.gc();
        initializeDatabase();
    }

    private void clearDatabase() {
        tables = null;
    }

    private void initializeDatabase() {
        tables = new HashMap<Class<? extends IEntidadeBiblioteca>, List<IEntidadeBiblioteca>>();
    }

    public boolean insert(IEntidadeBiblioteca object) {
        return tables
                .computeIfAbsent(object.getClass(), key -> new ArrayList<>())
                .add(object);
    }

    public <T extends IEntidadeBiblioteca> List<T> getAll(Class<T> entityType) {
        return tables.keySet()
                .stream()
                .filter(key -> entityType.isAssignableFrom(key))
                .map(tables::get)
                .flatMap(List::stream)
                .map(entityType::cast)
                .collect(Collectors.toList());
    }

    public <T extends IEntidadeBiblioteca> T getFirtById(Class<T> tabela,
            int id) {
        return this.getAll(tabela)
                .stream()
                .filter(entidade -> entidade.getCodigo() == id)
                .map(tabela::cast) // Cast the result to the specific type
                .findFirst()
                .orElse(null);
    }

    public IEntidadeBiblioteca[] getAllById(Class<? extends IEntidadeBiblioteca> tabela, int id) {
        return this.getAll(tabela)
                .stream()
                .filter(entidade -> entidade.getCodigo() == id)
                .toArray(IEntidadeBiblioteca[]::new);
    }

    public Livro getLivro(int codigoLivro) {
        return this.getFirtById(Livro.class, codigoLivro);
    }

    public Exemplar getExemplar(int codigoExemplar, int codigoLivro) {
        return getAll(Exemplar.class)
                .stream()
                .filter(exemplar -> exemplar.getCodigo() == codigoLivro)
                .filter(exemplar -> exemplar.getCodigoExemplar() == codigoExemplar)
                .findFirst()
                .orElse(null);
    }

    public Exemplar getExemplarDisponivelPorCodigoLivro(int codigoLivro) {
        return getAll(Exemplar.class)
                .stream()
                .filter(exemplar -> exemplar.getCodigo() == codigoLivro)
                .filter(exemplar -> exemplar.isDisponivel())
                .findFirst()
                .orElse(null);
    }

    public boolean temExemplarDisponivel(int codigoLivro) {
        return getAll(Exemplar.class)
                .stream()
                .anyMatch(exemplar -> exemplar.getCodigo() == codigoLivro &&
                        exemplar.isDisponivel());
    }

    public boolean livroExiste(int codigoLivro) {
        return getAll(Livro.class)
                .stream()
                .filter(livro -> livro.getCodigo() == codigoLivro)
                .findFirst()
                .isPresent();
    }

    public IUsuario getUsuario(int codigoUsuario) {
        return this.getFirtById(IUsuario.class, codigoUsuario);
    }

    public HashMap<Class<? extends IEntidadeBiblioteca>, List<IEntidadeBiblioteca>> getTables() {
        return tables;
    }
}
