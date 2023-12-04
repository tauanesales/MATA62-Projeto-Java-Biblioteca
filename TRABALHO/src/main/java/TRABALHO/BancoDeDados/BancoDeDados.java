package TRABALHO.BancoDeDados;

import TRABALHO.Livros.Exemplar;
import TRABALHO.Livros.Livro;
import TRABALHO.SistemaBiblioteca.IEntidadeBiblioteca;
import TRABALHO.Usuarios.IUsuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BancoDeDados implements IBancoDeDados {
    private static HashMap<Class<? extends IEntidadeBiblioteca>, List<IEntidadeBiblioteca>> bancoDeDados = new HashMap<Class<? extends IEntidadeBiblioteca>, List<IEntidadeBiblioteca>>();

    public static void init() {
        clearDatabase();
        System.gc();
        initializeDatabase();
    }

    private static void clearDatabase() {
        bancoDeDados = null;
    }

    private static void initializeDatabase() {
        bancoDeDados = new HashMap<Class<? extends IEntidadeBiblioteca>, List<IEntidadeBiblioteca>>();
    }

    public static boolean add(IEntidadeBiblioteca object) {
        return bancoDeDados
                .computeIfAbsent(object.getClass(), key -> new ArrayList<>())
                .add(object);
    }

    public static void printDados() {
        for (Class<? extends IEntidadeBiblioteca> key : bancoDeDados.keySet()) {
            List<IEntidadeBiblioteca> values = bancoDeDados.get(key);
            System.out.println("Key: " + key.getSimpleName());
            System.out.println("Values: " + values);
        }
    }

    public static <T extends IEntidadeBiblioteca> List<T> getAll(Class<T> entityType) {
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

    public static <T extends IEntidadeBiblioteca> T getFirtById(Class<T> tabela,
            int id) {
        return BancoDeDados.getAll(tabela)
                .stream()
                .filter(entidade -> entidade.getCodigo() == id)
                .map(tabela::cast) // Cast the result to the specific type
                .findFirst()
                .orElse(null);
    }

    public static IEntidadeBiblioteca[] getAllById(Class<? extends IEntidadeBiblioteca> tabela, int id) {
        return BancoDeDados.getAll(tabela)
                .stream()
                .filter(entidade -> entidade.getCodigo() == id)
                .toArray(IEntidadeBiblioteca[]::new);
    }

    public static Livro getLivro(int codigoLivro) {
        return BancoDeDados.getFirtById(Livro.class, codigoLivro);
    }

    public static Exemplar getExemplar(int codigoExemplar, int codigoLivro) {
        return getAll(Exemplar.class)
                .stream()
                .filter(exemplar -> exemplar.getCodigo() == codigoLivro)
                .filter(exemplar -> exemplar.getCodigoExemplar() == codigoExemplar)
                .findFirst()
                .orElse(null);
    }

    public static Exemplar getExemplarDisponivelPorCodigoLivro(int codigoLivro) {
        return getAll(Exemplar.class)
                .stream()
                .filter(exemplar -> exemplar.getCodigo() == codigoLivro)
                .filter(exemplar -> exemplar.isDisponivel())
                .findFirst()
                .orElse(null);
    }

    public static boolean temExemplarDisponivel(int codigoLivro) {
        return getAll(Exemplar.class)
                .stream()
                .anyMatch(exemplar -> exemplar.getCodigo() == codigoLivro &&
                        exemplar.isDisponivel());
    }

    public static boolean livroExiste(int codigoLivro) {
        return getAll(Livro.class)
                .stream()
                .filter(livro -> livro.getCodigo() == codigoLivro)
                .findFirst()
                .isPresent();
    }

    public static IUsuario getUsuario(int codigoUsuario) {
        return BancoDeDados.getFirtById(IUsuario.class, codigoUsuario);
    }
}
