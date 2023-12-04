package TRABALHO.SistemaBiblioteca;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;

public class SistemaBibliotecaTest extends BaseTest {
    @Test
    public void realizarDevolucaoNaoDevolveExemplaresEmprestadosAOutroUsuarioTest() {
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigo());
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar2.getCodigo());

        Assert.assertEquals(2, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(0, professor.quantidadeDeEmprestimosEmAberto());

        biblioteca.realizarDevolucao(professor.getCodigo(), exemplar1.getCodigo());
        biblioteca.realizarDevolucao(professor.getCodigo(), exemplar2.getCodigo());

        Assert.assertEquals(2, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(0, professor.quantidadeDeEmprestimosEmAberto());
    }

    @Test
    public void realizarDevolucaoDevolveCorretamenteTest() {
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigo());
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar2.getCodigo());

        Assert.assertEquals(2, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(0, professor.quantidadeDeEmprestimosEmAberto());

        biblioteca.realizarDevolucao(alunoPosGrad.getCodigo(), exemplar1.getCodigo());
        biblioteca.realizarDevolucao(alunoPosGrad.getCodigo(), exemplar2.getCodigo());

        Assert.assertEquals(0, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(0, professor.quantidadeDeEmprestimosEmAberto());
    }

    @Test
    public void realizarEmprestimoNaoEmprestaExemplaresJaEmprestadosTest() {
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigo());
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar2.getCodigo());

        Assert.assertEquals(2, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(0, professor.quantidadeDeEmprestimosEmAberto());

        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar1.getCodigo());
        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar2.getCodigo());

        Assert.assertEquals(2, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(0, professor.quantidadeDeEmprestimosEmAberto());
    }

    @Test
    public void realizarEmprestimoFuncionaComExemplaresJaDevolvidosTest() {
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigo());
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar2.getCodigo());

        Assert.assertEquals(2, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(0, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(exemplar1, alunoPosGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar1.getCodigo()).getExemplar());
        Assert.assertEquals(exemplar2, alunoPosGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar2.getCodigo()).getExemplar());

        biblioteca.realizarDevolucao(alunoPosGrad.getCodigo(), exemplar1.getCodigo());
        biblioteca.realizarDevolucao(alunoPosGrad.getCodigo(), exemplar2.getCodigo());

        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar1.getCodigo());
        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar2.getCodigo());

        Assert.assertEquals(0, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(2, professor.quantidadeDeEmprestimosEmAberto());

        Assert.assertEquals(exemplar1, professor.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar1.getCodigo()).getExemplar());
        Assert.assertEquals(exemplar2, professor.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar2.getCodigo()).getExemplar());
    }
}
