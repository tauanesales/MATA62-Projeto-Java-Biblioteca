package TRABALHO.Usuarios;

import TRABALHO.BancoDeDados.IBancoDeDados;

public class AlunoPosGraduacao extends Usuario {
    public AlunoPosGraduacao(int codigo_identificador, String nome, IBancoDeDados db) {
        super(codigo_identificador, nome, db);
    }

    public int maxEmprestimos() {
        return 3;
    }

    public long tempoDeEmprestimoMaximo() {
        return 5 * 24 * 60 * 60 * 1000; // 5 dias em milissegundos;
    }
}
