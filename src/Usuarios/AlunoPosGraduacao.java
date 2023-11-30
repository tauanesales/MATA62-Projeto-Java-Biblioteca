package Usuarios;

public class AlunoPosGraduacao extends Aluno {
    public AlunoPosGraduacao(int codigo_identificador, String nome) {
        super(codigo_identificador, nome);
    }

    public int maxEmprestimos() {
        return 4;
    }
}
