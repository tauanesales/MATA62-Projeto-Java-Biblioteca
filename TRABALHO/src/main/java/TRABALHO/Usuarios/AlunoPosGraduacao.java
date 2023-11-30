package TRABALHO.Usuarios;

public class AlunoPosGraduacao extends Aluno {
    public AlunoPosGraduacao(int codigo_identificador, String nome) {
        super(codigo_identificador, nome);
    }

    public int maxEmprestimos() {
        return 4;
    }

    public long tempoDeEmprestimoMaximo() {
        return 4 * 24 * 60 * 60 * 1000; // 4 dias em milissegundos;
    }
}
