package BancoDeDados;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Biblioteca.Exemplar;
import Biblioteca.IEntidadeBiblioteca;

public class BancoDeDados implements IBancoDeDados {
  // private HashMap<Class<? extends IEntidadeBiblioteca>, List<? extends
  // IEntidadeBiblioteca>> dados;

  // public BancoDeDados() {
  // this.dados = new HashMap<Class<? extends IEntidadeBiblioteca>, List<? extends
  // IEntidadeBiblioteca>>();
  // }

  private HashMap<Class<? extends IEntidadeBiblioteca>, List<IEntidadeBiblioteca>> dados;

  public BancoDeDados() {
    this.dados = new HashMap<Class<? extends IEntidadeBiblioteca>, List<IEntidadeBiblioteca>>();
  }

  public boolean add(IEntidadeBiblioteca object) {
    if (!dados.containsKey(object.getClass())) {
      dados.put(object.getClass(), new ArrayList<IEntidadeBiblioteca>());
    }
    return dados.get(object.getClass()).add(object);
  }

  public void printDados() {
    for (Class<? extends IEntidadeBiblioteca> key : dados.keySet()) {
      List<IEntidadeBiblioteca> values = dados.get(key);
      System.out.println("Key: " + key.getSimpleName());
      System.out.println("Values: " + values);
    }
  }

  public List<? extends IEntidadeBiblioteca> getAll(Class<? extends IEntidadeBiblioteca> T) {
    List<IEntidadeBiblioteca> result = new ArrayList<>();
    for (Class<? extends IEntidadeBiblioteca> key : dados.keySet()) {
      if (T.isAssignableFrom(key)) {
        result.addAll(dados.get(key));
      }
    }
    return result;
  }

  public IEntidadeBiblioteca getFirtById(Class<? extends IEntidadeBiblioteca> tabela, int id) {
    return this.getAll(tabela).stream()
        .filter(entidade -> entidade.getCodigo() == id)
        .findFirst()
        .orElse(null);
  }

  public IEntidadeBiblioteca[] getAllById(Class<? extends IEntidadeBiblioteca> tabela, int id) {
    return this.getAll(tabela).stream()
        .filter(entidade -> entidade.getCodigo() == id)
        .toArray(IEntidadeBiblioteca[]::new);
  }

  public Exemplar getExemplar(int codigoExemplar) {
    return (Exemplar) getAll(Exemplar.class).stream()
        .filter(exemplar -> exemplar.getCodigo() == codigoExemplar)
        .findFirst()
        .orElse(null);
  }
}
