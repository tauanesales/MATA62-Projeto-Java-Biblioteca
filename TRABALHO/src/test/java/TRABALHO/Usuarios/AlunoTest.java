package TRABALHO.Usuarios;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class AlunoTest {
    private static Aluno aluno;

    @Before
    public void setUp() {
        aluno = new Aluno(1, "Aluno");
    }

    @Test
    public void maxEmprestimosTest() {
        Assert.assertEquals(0, aluno.maxEmprestimos());
    }

    @Test
    public void tempoDeEmprestimoMaximoTest() {
        Assert.assertEquals(0, aluno.tempoDeEmprestimoMaximo());
    }
}
