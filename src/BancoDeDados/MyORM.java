package BancoDeDados;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import SistemaBiblioteca.Exemplar;
import SistemaBiblioteca.IEntidadeBiblioteca;
import SistemaBiblioteca.Livro;
import Usuarios.Usuario;

public class MyORM implements IBancoDeDados {
  // private HashMap<Class<? extends IEntidadeBiblioteca>, List<? extends
  // IEntidadeBiblioteca>> bancoDeDados;

  // public static BancoDeDados() {
  // this.bancoDeDados = new HashMap<Class<? extends IEntidadeBiblioteca>, List<?
  // extends
  // IEntidadeBiblioteca>>();
  // }
  private static HashMap<Class<? extends IEntidadeBiblioteca>, List<IEntidadeBiblioteca>> bancoDeDados = new HashMap<Class<? extends IEntidadeBiblioteca>, List<IEntidadeBiblioteca>>();

  public static boolean add(IEntidadeBiblioteca object) {
    if (!bancoDeDados.containsKey(object.getClass())) {
      bancoDeDados.put(object.getClass(), new ArrayList<IEntidadeBiblioteca>());
    }
    return bancoDeDados.get(object.getClass()).add(object);
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
    for (Class<? extends IEntidadeBiblioteca> key : bancoDeDados.keySet()) {
      if (entityType.isAssignableFrom(key)) {
        result.addAll((List<T>) bancoDeDados.get(key));
      }
    }
    return result;
  }

  public static <T extends IEntidadeBiblioteca> T getFirtById(Class<T> tabela, int id) {
    return MyORM.getAll(tabela).stream()
        .filter(entidade -> entidade.getCodigo() == id)
        .map(tabela::cast) // Cast the result to the specific type
        .findFirst()
        .orElse(null);
  }

  public static IEntidadeBiblioteca[] getAllById(Class<? extends IEntidadeBiblioteca> tabela, int id) {
    return MyORM.getAll(tabela).stream()
        .filter(entidade -> entidade.getCodigo() == id)
        .toArray(IEntidadeBiblioteca[]::new);
  }

  public static Livro getLivro(int codigoLivro) {
    return MyORM.getFirtById(Livro.class, codigoLivro);
  }

  public static Exemplar getExemplar(int codigoExemplar) {
    return getAll(Exemplar.class).stream()
        .filter(exemplar -> exemplar.getCodigo() == codigoExemplar)
        .findFirst()
        .orElse(null);
  }

  public static Exemplar getExemplarDisponivelPorCodigoLivro(int codigoLivro) {
    return getAll(Exemplar.class).stream()
        .filter(exemplar -> exemplar.getCodigo() == codigoLivro && exemplar.isDisponivel())
        .findFirst()
        .orElse(null);
  }

  public static boolean livroExiste(int codigoLivro) {
    return getAll(Livro.class).stream()
        .filter(livro -> livro.getCodigo() == codigoLivro)
        .findFirst()
        .isPresent();
  }

  public static Usuario getUsuario(int codigoUsuario) {
    return MyORM.getFirtById(Usuario.class, codigoUsuario);
  }
}
