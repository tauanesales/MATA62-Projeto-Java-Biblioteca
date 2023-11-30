package TRABALHO.Usuarios;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class ProfessorTest {
    private static Professor professor;

    @Before
    public void setUp() {
        professor = new Professor(1, "Professor");
    }

    @Test
    public void maxEmprestimosTest() {
        Assert.assertEquals(Integer.MAX_VALUE, professor.maxEmprestimos());
    }

    @Test
    public void tempoDeEmprestimoMaximoTest() {
        Assert.assertEquals(7 * 24 * 60 * 60 * 1000, professor.tempoDeEmprestimoMaximo());
    }
}
