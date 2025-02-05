package TRABALHO.Usuarios;

import TRABALHO.BancoDeDados.IBancoDeDados;

public class AlunoGraduacao extends Usuario {
    public AlunoGraduacao(int codigo_identificador, String nome, IBancoDeDados db) {
        super(codigo_identificador, nome, db);
    }

    public int maxEmprestimos() {
        return 2;
    }

    public long tempoDeEmprestimoMaximo() {
        return 4 * 24 * 60 * 60 * 1000; // 4 dias em milissegundos;
    }
}
