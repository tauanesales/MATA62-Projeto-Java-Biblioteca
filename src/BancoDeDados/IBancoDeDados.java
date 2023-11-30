package BancoDeDados;

import java.util.List;

import SistemaBiblioteca.Exemplar;
import SistemaBiblioteca.IEntidadeBiblioteca;

public interface IBancoDeDados {
    public static boolean add(IEntidadeBiblioteca object) {
        // Add implementation here
        return false; // Placeholder return statement
    }

    public static List<? extends IEntidadeBiblioteca> getAll(Class<? extends IEntidadeBiblioteca> tabela) {
        // Add implementation here
        return null; // Placeholder return statement
    }

    public static IEntidadeBiblioteca getFirtById(Class<? extends IEntidadeBiblioteca> tabela, int id) {
        // Add implementation here
        return null; // Placeholder return statement
    }

    public static IEntidadeBiblioteca[] getAllById(Class<? extends IEntidadeBiblioteca> tabela, int id) {
        // Add implementation here
        return null; // Placeholder return statement
    }

    public static Exemplar getExemplar(int codigoExemplar) {
        // Add implementation here
        return null; // Placeholder return statement
    }
}
