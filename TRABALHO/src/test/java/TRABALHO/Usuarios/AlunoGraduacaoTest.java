package TRABALHO.Usuarios;

import TRABALHO.BaseTest;
import TRABALHO.Emprestimo.Emprestimo;

import org.junit.Assert;
import org.junit.Test;
import java.util.Calendar;

public class AlunoGraduacaoTest extends BaseTest {
    @Test
    public void getCodigoTest() {
        Assert.assertEquals(2, alunoGrad.getCodigo());
    }

    @Test
    public void getNomeTest() {
        Assert.assertEquals("Maria", alunoGrad.getNome());
    }

    @Test
    public void temAtrasoTest() {
        // Não possui atraso;
        Assert.assertFalse(alunoGrad.temAtraso());

        // Continua sem atraso;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertFalse(alunoGrad.temAtraso());

        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1);

        // Entra em situação de atraso por ter um empréstimo antigo;
        db.insert(new Emprestimo(exemplar1, alunoGrad, calendar.getTime()));
        Assert.assertTrue(alunoGrad.temAtraso());
    }

    @Test
    public void obterEmprestimosSemEmprestimosPassadosTest() {
        Assert.assertEquals(0, alunoGrad.obterEmprestimos(true).size());
        Assert.assertEquals(0, alunoGrad.obterEmprestimos(false).size());
    }

    @Test
    public void obterEmprestimosAumentaAQuantidadeDaListaAoEmprestarTest() {
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertEquals(1, alunoGrad.obterEmprestimos(true).size());
        Assert.assertEquals(1, alunoGrad.obterEmprestimos(false).size());

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        Assert.assertEquals(2, alunoGrad.obterEmprestimos(true).size());
        Assert.assertEquals(2, alunoGrad.obterEmprestimos(false).size());
    }

    @Test
    public void obterEmprestimosRetornaListaComOsExemplaresEmprestadosQuandoTodosEmprestimosEstaoEmAbertoTest() {
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());

        Assert.assertEquals(exemplar1, alunoGrad.obterEmprestimos(true).get(0).getExemplar());
        Assert.assertEquals(exemplar1, alunoGrad.obterEmprestimos(false).get(0).getExemplar());

        Assert.assertEquals(exemplar2, alunoGrad.obterEmprestimos(true).get(1).getExemplar());
        Assert.assertEquals(exemplar2, alunoGrad.obterEmprestimos(false).get(1).getExemplar());
    }

    @Test
    public void obterEmprestimosRetornaListaComOsExemplaresEmprestadosQuandoApenasAlgunsEmprestimosEstaoEmAbertoTest() {
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 500);

        alunoGrad.obterEmprestimos(false).get(0).devolver();
        Assert.assertEquals(1, alunoGrad.obterEmprestimos(true).size());
        Assert.assertEquals(2, alunoGrad.obterEmprestimos(false).size());

        alunoGrad.obterEmprestimos(false).get(1).devolver();
        Assert.assertEquals(0, alunoGrad.obterEmprestimos(true).size());
        Assert.assertEquals(2, alunoGrad.obterEmprestimos(false).size());

        Assert.assertEquals(exemplar1, alunoGrad.obterEmprestimos(false).get(0).getExemplar());
        Assert.assertEquals(exemplar2, alunoGrad.obterEmprestimos(false).get(1).getExemplar());
        Assert.assertEquals(1001, alunoGrad.obterEmprestimos(false).get(0).getExemplar().getCodigoLivro());
        Assert.assertEquals(1002, alunoGrad.obterEmprestimos(false).get(1).getExemplar().getCodigoLivro());
    }

    @Test
    public void obterEmprestimosRetornaListaComOsExemplaresEmprestadosQuandoApenasAlgunsEmprestimosEstaoEmAbertoDevolucaoPeloSistemaTest() {
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 500);

        biblioteca.realizarDevolucao(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertEquals(1, alunoGrad.obterEmprestimos(true).size());
        Assert.assertEquals(2, alunoGrad.obterEmprestimos(false).size());

        biblioteca.realizarDevolucao(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        Assert.assertEquals(0, alunoGrad.obterEmprestimos(true).size());
        Assert.assertEquals(2, alunoGrad.obterEmprestimos(false).size());

        biblioteca.realizarDevolucao(alunoGrad.getCodigo(), 100);
        Assert.assertEquals(0, alunoGrad.obterEmprestimos(true).size());
        Assert.assertEquals(2, alunoGrad.obterEmprestimos(false).size());

        Assert.assertEquals(exemplar1, alunoGrad.obterEmprestimos(false).get(0).getExemplar());
        Assert.assertEquals(exemplar2, alunoGrad.obterEmprestimos(false).get(1).getExemplar());
        Assert.assertEquals(1001, alunoGrad.obterEmprestimos(false).get(0).getExemplar().getCodigoLivro());
        Assert.assertEquals(1002, alunoGrad.obterEmprestimos(false).get(1).getExemplar().getCodigoLivro());
    }

    @Test
    public void obterEmprestimoEmAbertoPorCodigoDoLivroTest() {
        Assert.assertNull(alunoGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar1.getCodigoLivro()));
        Assert.assertNull(alunoGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar2.getCodigoLivro()));

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertEquals(exemplar1, alunoGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar1.getCodigoLivro()).getExemplar());
        Assert.assertNull(alunoGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar2.getCodigoLivro()));

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        Assert.assertEquals(exemplar1, alunoGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar1.getCodigoLivro()).getExemplar());
        Assert.assertEquals(exemplar2, alunoGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar2.getCodigoLivro()).getExemplar());

        biblioteca.realizarDevolucao(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertNull(alunoGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar1.getCodigoLivro()));
        Assert.assertEquals(exemplar2, alunoGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar2.getCodigoLivro()).getExemplar());

        biblioteca.realizarDevolucao(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        Assert.assertNull(alunoGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar1.getCodigoLivro()));
        Assert.assertNull(alunoGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar2.getCodigoLivro()));

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        Assert.assertEquals(exemplar1, alunoGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar1.getCodigoLivro()).getExemplar());
        Assert.assertEquals(exemplar2, alunoGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar2.getCodigoLivro()).getExemplar());

        biblioteca.realizarDevolucao(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertNull(alunoGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar1.getCodigoLivro()));
        Assert.assertEquals(exemplar2, alunoGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar2.getCodigoLivro()).getExemplar());

        biblioteca.realizarDevolucao(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        Assert.assertNull(alunoGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar1.getCodigoLivro()));
        Assert.assertNull(alunoGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar2.getCodigoLivro()));
    }

    @Test
    public void quantidadeDeEmprestimosEmAbertoComecaZeradaTest() {
        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
    }

    @Test
    public void quantidadeDeEmprestimosEmAbertoAumentaComNovosEmprestimosNaoRepetidosTest() {
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 500);
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
    }

    @Test
    public void quantidadeDeEmprestimosEmAbertoNaoAumentaComEmprestimosRepetidosTest() {
        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());

        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);

        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
    }

    @Test
    public void quantidadeDeEmprestimosEmAbertoNaoUltrapassaLimiteTest() {
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 200);

        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(alunoGrad.maxEmprestimos(), alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 300);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 400);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 501);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 600);

        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(alunoGrad.maxEmprestimos(), alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());
    }

    @Test
    public void quantidadeDeEmprestimosEmAbertoDiminuiAoDevolverTest() {
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 200);

        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(alunoGrad.maxEmprestimos(), alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        alunoGrad.obterEmprestimos(false).get(0).devolver();
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        alunoGrad.obterEmprestimos(false).get(1).devolver();
        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());
    }

    @Test
    public void quantidadeDeEmprestimosEmAbertoDiminuiAoDevolverPeloSistemaTest() {
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 200);

        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(alunoGrad.maxEmprestimos(), alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.realizarDevolucao(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.realizarDevolucao(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());
    }

    @Test
    public void quantidadeDeEmprestimosEmAbertoAumentaAoRealizarNovosEmprestimosAposDevolverExemplaresTest() {
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 200);

        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(alunoGrad.maxEmprestimos(), alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        alunoGrad.obterEmprestimos(true).forEach(emprestimo -> emprestimo.getExemplar().devolver());

        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 200);

        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(alunoGrad.maxEmprestimos(), alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        alunoGrad.obterEmprestimos(true).forEach(emprestimo -> emprestimo.getExemplar().devolver());

        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 500);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 600);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);

        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(alunoGrad.maxEmprestimos(), alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        alunoGrad.obterEmprestimos(true).forEach(emprestimo -> emprestimo.devolver());

        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());
    }

    @Test
    public void quantidadeDeEmprestimosEmAbertoAumentaAoRealizarNovosEmprestimosAposDevolverExemplaresPeloSistemaTest() {
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 200);

        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(alunoGrad.maxEmprestimos(), alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        alunoGrad.obterEmprestimos(true).forEach(emprestimo -> biblioteca.realizarDevolucao(alunoGrad.getCodigo(),
                emprestimo.getExemplar().getCodigoLivro()));

        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 200);

        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(alunoGrad.maxEmprestimos(), alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        alunoGrad.obterEmprestimos(true).forEach(emprestimo -> biblioteca.realizarDevolucao(alunoGrad.getCodigo(),
                emprestimo.getExemplar().getCodigoLivro()));

        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 500);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 600);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);

        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(alunoGrad.maxEmprestimos(), alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        alunoGrad.obterEmprestimos(true).forEach(emprestimo -> biblioteca.realizarDevolucao(alunoGrad.getCodigo(),
                emprestimo.getExemplar().getCodigoLivro()));

        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());
    }

    @Test
    public void maxEmprestimosTest() {
        Assert.assertEquals(2, alunoGrad.maxEmprestimos());
    }

    @Test
    public void atingiuLimiteMaximoDeEmprestimosTest() {
        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Usuário inválido;
        biblioteca.realizarEmprestimo(-1, exemplar1.getCodigoLivro());
        // Livro inválido;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 1);

        Assert.assertTrue(exemplar1.isDisponivel());

        // Empréstimo bem sucedido;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertFalse(exemplar1.isDisponivel());

        Assert.assertEquals(1, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        // Exemplar indisponível;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertFalse(exemplar1.isDisponivel());

        Assert.assertTrue(exemplar2.isDisponivel());
        // Empréstimo bem sucedido;
        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar2.getCodigoLivro());
        Assert.assertFalse(exemplar2.isDisponivel());

        // Emprestimo bem sucedido;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 101);

        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Primeiro empréstimo bem sucedido do aluno de graduação;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Exemplar indisponível;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Exemplar indisponível;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);

        // Exemplar indisponível;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());

        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Esse livro não tem exemplares disponíveis;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 301);
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Segundo emprestimo bem sucedido do aluno de graduação;
        // Chegou ao limite maximo de emprestimos do aluno de graduaçao (Limite
        // Graduação = 2);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 400);
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // A partir daqui, o aluno de graduação não pode mais fazer empréstimos;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 401);
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 400);
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 500);
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 501);
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 600);
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);

        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());
    }

    @Test
    public void tempoDeEmprestimoMaximoTest() {
        Assert.assertEquals(4 * 24 * 60 * 60 * 1000,
                alunoGrad.tempoDeEmprestimoMaximo());
    }

    @Test
    public void jaTemEmprestimoDoLivroTest() {
        Assert.assertFalse(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar1.getCodigoLivro()));
        Assert.assertFalse(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar2.getCodigoLivro()));

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertTrue(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar1.getCodigoLivro()));
        Assert.assertFalse(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar2.getCodigoLivro()));

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        Assert.assertTrue(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar1.getCodigoLivro()));
        Assert.assertTrue(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar2.getCodigoLivro()));
    }

    @Test
    public void jaTemEmprestimoDoLivroFalsoAposDevolverLivrosTest() {
        Assert.assertFalse(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar1.getCodigoLivro()));
        Assert.assertFalse(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar2.getCodigoLivro()));

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());

        Assert.assertTrue(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar1.getCodigoLivro()));
        Assert.assertTrue(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar2.getCodigoLivro()));

        biblioteca.realizarDevolucao(alunoGrad.getCodigo(),exemplar1.getCodigoLivro());
        Assert.assertFalse(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar1.getCodigoLivro()));
        Assert.assertTrue(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar2.getCodigoLivro()));

        exemplar2.devolver();
        Assert.assertFalse(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar1.getCodigoLivro()));
        Assert.assertFalse(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar2.getCodigoLivro()));

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertTrue(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar1.getCodigoLivro()));
        Assert.assertFalse(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar2.getCodigoLivro()));

        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigoLivro());
        Assert.assertTrue(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar1.getCodigoLivro()));
        Assert.assertTrue(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar2.getCodigoLivro()));

        alunoGrad.obterEmprestimos(true).get(0).devolver();
        Assert.assertFalse(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar1.getCodigoLivro()));
        Assert.assertTrue(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar2.getCodigoLivro()));

        alunoGrad.obterEmprestimos(true).get(0).devolver();
        Assert.assertFalse(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar1.getCodigoLivro()));
        Assert.assertFalse(alunoGrad.jaTemEmprestimoDoLivroEmAberto(exemplar2.getCodigoLivro()));
    }
}
