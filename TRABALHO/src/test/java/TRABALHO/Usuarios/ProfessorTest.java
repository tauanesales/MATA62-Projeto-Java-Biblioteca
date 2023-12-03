package TRABALHO.Usuarios;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;

public class ProfessorTest extends BaseTest {
    @Test
    public void maxEmprestimosTest() {
        Assert.assertEquals(Integer.MAX_VALUE, professor.maxEmprestimos());
    }

    @Test
    public void tempoDeEmprestimoMaximoTest() {
        Assert.assertEquals(7 * 24 * 60 * 60 * 1000, professor.tempoDeEmprestimoMaximo());
    }

    @Test
    public void atingiuLimiteMaximoDeEmprestimosTest() {
        Assert.assertEquals(0, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        // Usuário inválido;
        biblioteca.realizarEmprestimo(-1, exemplar1.getCodigo());
        // Livro inválido;
        biblioteca.realizarEmprestimo(aluno.getCodigo(), 1);

        Assert.assertTrue(exemplar1.isDisponivel());
        // Classe Aluno "pura" não pode fazer empréstimos;
        biblioteca.realizarEmprestimo(aluno.getCodigo(), exemplar1.getCodigo());

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
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar2.getCodigo());
        Assert.assertFalse(exemplar2.isDisponivel());

        // Emprestimo bem sucedido;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 101);

        Assert.assertEquals(0, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, professor.atingiuLimiteMaximoDeEmprestimos());

        // Primeiro empréstimo bem sucedido do professor;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 100);
        Assert.assertEquals(1, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, professor.atingiuLimiteMaximoDeEmprestimos());

        // Exemplar indisponível;
        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar1.getCodigo());
        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar1.getCodigo());
        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar1.getCodigo());
        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar1.getCodigo());
        Assert.assertEquals(1, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, professor.atingiuLimiteMaximoDeEmprestimos());

        // Exemplar indisponível;
        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar1.getCodigo());
        Assert.assertEquals(1, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, professor.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 100);
        Assert.assertEquals(1, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, professor.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 100);
        Assert.assertEquals(1, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, professor.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 100);
        Assert.assertEquals(1, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, professor.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 100);

        // Exemplar indisponível;
        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar2.getCodigo());

        Assert.assertEquals(1, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, professor.atingiuLimiteMaximoDeEmprestimos());

        // Segundo emprestimo bem sucedido do professor;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 300);
        Assert.assertEquals(2, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, professor.atingiuLimiteMaximoDeEmprestimos());

        // Esse livro não tem exemplares disponíveis;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 301);
        Assert.assertEquals(2, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, professor.atingiuLimiteMaximoDeEmprestimos());

        // Terceiro emprestimo bem sucedido do professor;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 400);
        Assert.assertEquals(3, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, professor.atingiuLimiteMaximoDeEmprestimos());

        // Esse livro não tem exemplares disponíveis;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 401);
        Assert.assertEquals(3, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, professor.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 400);
        Assert.assertEquals(3, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, professor.atingiuLimiteMaximoDeEmprestimos());

        // Quarto emprestimo bem sucedido do professor;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 500);
        Assert.assertEquals(4, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, professor.atingiuLimiteMaximoDeEmprestimos());

        // Quinto emprestimo bem sucedido do professor;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 501);
        Assert.assertEquals(5, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, professor.atingiuLimiteMaximoDeEmprestimos());

        // Sexto emprestimo bem sucedido do professor;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 600);
        Assert.assertEquals(6, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, professor.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 100);
        biblioteca.realizarEmprestimo(professor.getCodigo(), 100);
        biblioteca.realizarEmprestimo(professor.getCodigo(), 100);
        biblioteca.realizarEmprestimo(professor.getCodigo(), 100);

        // Não há limite de empréstimos para professor;
        Assert.assertEquals(6, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, professor.atingiuLimiteMaximoDeEmprestimos());
    }
}
