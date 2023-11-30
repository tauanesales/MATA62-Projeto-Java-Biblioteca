package TRABALHO.Usuarios;

public class AlunoGraduacao extends Aluno {
    public AlunoGraduacao(int codigo_identificador, String nome) {
        super(codigo_identificador, nome);
    }

    public int maxEmprestimos() {
        return 3;
    }

    public long tempoDeEmprestimoMaximo() {
        return 3 * 24 * 60 * 60 * 1000; // 3 dias em milissegundos;
    }
}
