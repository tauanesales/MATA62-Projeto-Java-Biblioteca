package Usuarios;

public class Professor extends Usuario {
    public Professor(int codigo_identificador, String nome) {
        super(codigo_identificador, nome);
    }

    public int maxEmprestimos() {
        return Integer.MAX_VALUE;
    }
}
