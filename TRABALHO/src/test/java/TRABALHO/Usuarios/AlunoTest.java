package TRABALHO.Usuarios;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;

public class AlunoTest extends BaseTest {
    @Test
    public void getCodigoTest() {
        Assert.assertEquals(1, aluno.getCodigo());
    }

    @Test
    public void getNomeTest() {
        Assert.assertEquals("João", aluno.getNome());
    }

    @Test
    public void temAtrasoTest() {
        Assert.assertFalse(aluno.temAtraso());
    }

    @Test
    public void obterEmprestimosTest() {
        Assert.assertEquals(0, aluno.obterEmprestimos(true).size());
        Assert.assertEquals(0, aluno.obterEmprestimos(false).size());

        biblioteca.realizarEmprestimo(aluno.getCodigo(), exemplar1.getCodigo());
        Assert.assertEquals(0, aluno.obterEmprestimos(true).size());
        Assert.assertEquals(0, aluno.obterEmprestimos(false).size());
    }

    @Test
    public void quantidadeDeEmprestimosEmAbertoTest() {
        Assert.assertEquals(0, aluno.quantidadeDeEmprestimosEmAberto());

        biblioteca.realizarEmprestimo(aluno.getCodigo(), exemplar1.getCodigo());
        Assert.assertEquals(0, aluno.quantidadeDeEmprestimosEmAberto());
    }

    @Test
    public void maxEmprestimosTest() {
        Assert.assertEquals(0, aluno.maxEmprestimos());
    }

    @Test
    public void atingiuLimiteMaximoDeEmprestimosTest() {
        // Não possui emprestimos em aberto;
        Assert.assertEquals(0, aluno.quantidadeDeEmprestimosEmAberto());
        // Limite máximo de empréstimos é 0;
        Assert.assertEquals(0, aluno.maxEmprestimos());
        // Por consequência lógica, já atingiu o limite máximo de empréstimos;
        Assert.assertTrue(aluno.quantidadeDeEmprestimosEmAberto() >= aluno.maxEmprestimos());
        Assert.assertTrue(aluno.atingiuLimiteMaximoDeEmprestimos());

        // Confirmando que outros alunos não atingiram o limite de empréstimos;
        Assert.assertEquals(0, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        // Usuário inválido;
        biblioteca.realizarEmprestimo(-1, exemplar1.getCodigo());
        // Livro inválido;
        biblioteca.realizarEmprestimo(aluno.getCodigo(), 1);

        Assert.assertTrue(exemplar1.isDisponivel());
        // Classe Aluno "pura" não pode fazer empréstimos;
        biblioteca.realizarEmprestimo(aluno.getCodigo(), exemplar1.getCodigo());
        biblioteca.realizarEmprestimo(aluno.getCodigo(), exemplar2.getCodigo());
        biblioteca.realizarEmprestimo(aluno.getCodigo(), 1);
        biblioteca.realizarEmprestimo(aluno.getCodigo(), 1001);
        biblioteca.realizarEmprestimo(aluno.getCodigo(), 1002);
        biblioteca.realizarEmprestimo(aluno.getCodigo(), 100);
        biblioteca.realizarEmprestimo(aluno.getCodigo(), 0);

        Assert.assertEquals(0, aluno.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(aluno.atingiuLimiteMaximoDeEmprestimos());

        Assert.assertTrue(exemplar1.isDisponivel());
        // Empréstimo bem sucedido;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigo());
        Assert.assertFalse(exemplar1.isDisponivel());

        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        // Exemplar indisponível;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigo());
        Assert.assertFalse(exemplar1.isDisponivel());

        Assert.assertTrue(exemplar2.isDisponivel());
        // Empréstimo bem sucedido;
        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar2.getCodigo());
        Assert.assertFalse(exemplar2.isDisponivel());

        // Emprestimo bem sucedido;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 101);

        Assert.assertEquals(0, aluno.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(aluno.atingiuLimiteMaximoDeEmprestimos());
    }

    @Test
    public void tempoDeEmprestimoMaximoTest() {
        Assert.assertEquals(0, aluno.tempoDeEmprestimoMaximo());
    }

    @Test
    public void jaTemEmprestimoDoLivroTest() {
        Assert.assertFalse(aluno.jaTemEmprestimoDoLivroEmAberto(exemplar1.getCodigo()));
        Assert.assertFalse(aluno.jaTemEmprestimoDoLivroEmAberto(exemplar2.getCodigo()));

        biblioteca.realizarEmprestimo(aluno.getCodigo(), exemplar1.getCodigo());
        Assert.assertFalse(aluno.jaTemEmprestimoDoLivroEmAberto(exemplar1.getCodigo()));
        Assert.assertFalse(aluno.jaTemEmprestimoDoLivroEmAberto(exemplar2.getCodigo()));

        biblioteca.realizarEmprestimo(aluno.getCodigo(), exemplar2.getCodigo());
        Assert.assertFalse(aluno.jaTemEmprestimoDoLivroEmAberto(exemplar1.getCodigo()));
        Assert.assertFalse(aluno.jaTemEmprestimoDoLivroEmAberto(exemplar2.getCodigo()));
    }
}
