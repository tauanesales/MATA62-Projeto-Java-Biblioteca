package TRABALHO.Usuarios;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class AlunoPosGraduacaoTest {
    private static AlunoPosGraduacao alunoPosGrad;

    @Before
    public void setUp() {
        alunoPosGrad = new AlunoPosGraduacao(1, "AlunoPosGraduacao");
    }

    @Test
    public void maxEmprestimosTest() {
        Assert.assertEquals(4, alunoPosGrad.maxEmprestimos());
    }

    @Test
    public void tempoDeEmprestimoMaximoTest() {
        Assert.assertEquals(4 * 24 * 60 * 60 * 1000, alunoPosGrad.tempoDeEmprestimoMaximo());
    }
}
