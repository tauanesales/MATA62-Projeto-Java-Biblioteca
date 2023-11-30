package BancoDeDados;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import SistemaBiblioteca.Exemplar;
import SistemaBiblioteca.IEntidadeBiblioteca;

public class BancoDeDados implements IBancoDeDados {
  // private HashMap<Class<? extends IEntidadeBiblioteca>, List<? extends
  // IEntidadeBiblioteca>> bancoDeDados;

  // public static BancoDeDados() {
  // this.bancoDeDados = new HashMap<Class<? extends IEntidadeBiblioteca>, List<? extends
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

  public static List<? extends IEntidadeBiblioteca> getAll(Class<? extends IEntidadeBiblioteca> T) {
    List<IEntidadeBiblioteca> result = new ArrayList<>();
    for (Class<? extends IEntidadeBiblioteca> key : bancoDeDados.keySet()) {
      if (T.isAssignableFrom(key)) {
        result.addAll(bancoDeDados.get(key));
      }
    }
    return result;
  }

  public static IEntidadeBiblioteca getFirtById(Class<? extends IEntidadeBiblioteca> tabela, int id) {
    return BancoDeDados.getAll(tabela).stream()
        .filter(entidade -> entidade.getCodigo() == id)
        .findFirst()
        .orElse(null);
  }

  public static IEntidadeBiblioteca[] getAllById(Class<? extends IEntidadeBiblioteca> tabela, int id) {
    return BancoDeDados.getAll(tabela).stream()
        .filter(entidade -> entidade.getCodigo() == id)
        .toArray(IEntidadeBiblioteca[]::new);
  }

  public static Exemplar getExemplar(int codigoExemplar) {
    return (Exemplar) getAll(Exemplar.class).stream()
        .filter(exemplar -> exemplar.getCodigo() == codigoExemplar)
        .findFirst()
        .orElse(null);
  }
}
