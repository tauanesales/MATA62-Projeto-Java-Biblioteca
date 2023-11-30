package TRABALHO.Usuarios;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class AlunoGraduacaoTest {
    private static AlunoGraduacao alunoGrad;

    @Before
    public void setUp() {
        alunoGrad = new AlunoGraduacao(1, "AlunoGraduacao");
    }

    @Test
    public void maxEmprestimosTest() {
        Assert.assertEquals(3, alunoGrad.maxEmprestimos());
    }

    @Test
    public void tempoDeEmprestimoMaximoTest() {
        Assert.assertEquals(3 * 24 * 60 * 60 * 1000, alunoGrad.tempoDeEmprestimoMaximo());
    }
}
