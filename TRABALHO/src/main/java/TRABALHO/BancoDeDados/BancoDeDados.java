package TRABALHO.BancoDeDados;

import TRABALHO.Livros.Exemplar;
import TRABALHO.Livros.Livro;
import TRABALHO.SistemaBiblioteca.IEntidadeBiblioteca;
import TRABALHO.Usuarios.IUsuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BancoDeDados implements IBancoDeDados {
    private static BancoDeDados instance;
    private HashMap<Class<? extends IEntidadeBiblioteca>, List<IEntidadeBiblioteca>> bancoDeDados;

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
        bancoDeDados = null;
    }

    private void initializeDatabase() {
        bancoDeDados = new HashMap<Class<? extends IEntidadeBiblioteca>, List<IEntidadeBiblioteca>>();
    }

    public boolean add(IEntidadeBiblioteca object) {
        return bancoDeDados
                .computeIfAbsent(object.getClass(), key -> new ArrayList<>())
                .add(object);
    }

    public void printDados() {
        for (Class<? extends IEntidadeBiblioteca> key : bancoDeDados.keySet()) {
            List<IEntidadeBiblioteca> values = bancoDeDados.get(key);
            System.out.println("Key: " + key.getSimpleName());
            System.out.println("Values: " + values);
        }
    }

    public <T extends IEntidadeBiblioteca> List<T> getAll(Class<T> entityType) {
        List<T> result = new ArrayList<>();

        bancoDeDados.keySet()
                .stream()
                .filter(key -> entityType.isAssignableFrom(key))
                .map(bancoDeDados::get)
                .flatMap(List::stream)
                .map(entityType::cast)
                .forEach(result::add);

        return result;
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
}
