package TRABALHO.Usuarios;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;

public class AlunoTest extends BaseTest {
    @Test
    public void maxEmprestimosTest() {
        Assert.assertEquals(0, aluno.maxEmprestimos());
    }

    @Test
    public void tempoDeEmprestimoMaximoTest() {
        Assert.assertEquals(0, aluno.tempoDeEmprestimoMaximo());
    }

    @Test
    public void emprestimosAbertosTest() {
        Assert.assertEquals(0, aluno.emprestimosAbertos());
    }

    @Test
    public void atingiuLimiteMaximoDeEmprestimosTest() {
        // Não possui emprestimos em aberto;
        Assert.assertEquals(0, aluno.emprestimosAbertos());
        // Limite máximo de empréstimos é 0;
        Assert.assertEquals(0, aluno.maxEmprestimos());
        // Por consequência lógica, já atingiu o limite máximo de empréstimos;
        Assert.assertTrue(aluno.emprestimosAbertos() >= aluno.maxEmprestimos());
        Assert.assertEquals(true, aluno.atingiuLimiteMaximoDeEmprestimos());

        // Confirmando que outros alunos não atingiram o limite de empréstimos;
        Assert.assertEquals(0, alunoPosGrad.emprestimosAbertos());
        Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

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

        Assert.assertEquals(0, aluno.emprestimosAbertos());
        Assert.assertEquals(true, aluno.atingiuLimiteMaximoDeEmprestimos());

        Assert.assertTrue(exemplar1.isDisponivel());
        // Empréstimo bem sucedido;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigo());
        Assert.assertFalse(exemplar1.isDisponivel());

        Assert.assertEquals(1, alunoGrad.emprestimosAbertos());
        // Exemplar indisponível;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigo());
        Assert.assertFalse(exemplar1.isDisponivel());

        Assert.assertTrue(exemplar2.isDisponivel());
        // Empréstimo bem sucedido;
        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar2.getCodigo());
        Assert.assertFalse(exemplar2.isDisponivel());

        // Emprestimo bem sucedido;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 101);

        Assert.assertEquals(0, aluno.emprestimosAbertos());
        Assert.assertEquals(true, aluno.atingiuLimiteMaximoDeEmprestimos());
    }
}
