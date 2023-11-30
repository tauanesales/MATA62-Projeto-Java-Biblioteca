package TRABALHO.Usuarios;

public class Professor extends Usuario {
    public Professor(int codigo_identificador, String nome) {
        super(codigo_identificador, nome);
    }

    public int maxEmprestimos() {
        return Integer.MAX_VALUE;
    }

    public long tempoDeEmprestimoMaximo() {
        return 7 * 24 * 60 * 60 * 1000; // 7 dias em milissegundos;
    }
}
