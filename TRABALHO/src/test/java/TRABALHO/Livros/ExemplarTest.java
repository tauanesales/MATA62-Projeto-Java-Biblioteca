package TRABALHO.Livros;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;
import TRABALHO.Livros.EstadoExemplar.ExemplarDisponivel;
import TRABALHO.Livros.EstadoExemplar.ExemplarEmprestado;

public class ExemplarTest extends BaseTest {
    @Test
    public void basicExemplarFunctionsAreWorkingTest() {
        Assert.assertEquals(0, exemplar1.getLivro().getObservadores().size());
        Assert.assertEquals(livro1, exemplar1.getLivro());
        Assert.assertEquals(livro2, exemplar2.getLivro());

        Assert.assertNull(exemplar1.getEmprestimo());
        Assert.assertNull(exemplar1.getMutuario());

        Assert.assertEquals((new ExemplarDisponivel(null)).getClass().getSimpleName(),
                exemplar1.getEstado().getClass().getSimpleName());
        Assert.assertTrue(exemplar1.isDisponivel());
        exemplar1.emprestar(alunoGrad.getCodigo(), db);
        Assert.assertFalse(exemplar1.isDisponivel());
        Assert.assertEquals((new ExemplarEmprestado(exemplar2, alunoGrad, db)).getClass().getSimpleName(),
                exemplar1.getEstado().getClass().getSimpleName());

        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());

        exemplar1.devolver();

        Assert.assertTrue(exemplar1.isDisponivel());
        Assert.assertEquals((new ExemplarDisponivel(null)).getClass().getSimpleName(),
                exemplar1.getEstado().getClass().getSimpleName());

    }
}
