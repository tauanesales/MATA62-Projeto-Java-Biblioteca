package TRABALHO.Usuarios;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;

public class AlunoPosGraduacaoTest extends BaseTest {
    @Test
    public void maxEmprestimosTest() {
        Assert.assertEquals(4, alunoPosGrad.maxEmprestimos());
    }

    @Test
    public void tempoDeEmprestimoMaximoTest() {
        Assert.assertEquals(4 * 24 * 60 * 60 * 1000, alunoPosGrad.tempoDeEmprestimoMaximo());
    }

    @Test
    public void atingiuLimiteMaximoDeEmprestimosTest() {
        Assert.assertEquals(0, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        // Usuário inválido;
        biblioteca.realizarEmprestimo(-1, exemplar1.getCodigoLivro());
        // Livro inválido;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 1);

        Assert.assertTrue(exemplar1.isDisponivel());
        
        // Empréstimo bem sucedido;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertFalse(exemplar1.isDisponivel());

        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        // Exemplar indisponível;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertFalse(exemplar1.isDisponivel());

        Assert.assertTrue(exemplar2.isDisponivel());
        // Empréstimo bem sucedido;
        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar2.getCodigoLivro());
        Assert.assertFalse(exemplar2.isDisponivel());

        // Emprestimo bem sucedido;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 101);

        Assert.assertEquals(0, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        // Primeiro empréstimo bem sucedido do aluno de pós-graduação;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);
        Assert.assertEquals(1, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        // Exemplar indisponível;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertEquals(1, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        // Exemplar indisponível;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertEquals(1, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);
        Assert.assertEquals(1, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);
        Assert.assertEquals(1, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);
        Assert.assertEquals(1, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);

        // Exemplar indisponível;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar2.getCodigoLivro());

        Assert.assertEquals(1, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        // Segundo emprestimo bem sucedido do aluno de pós-graduação;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 300);
        Assert.assertEquals(2, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        // Esse livro não tem exemplares disponíveis;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 301);
        Assert.assertEquals(2, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        // Terceiro emprestimo bem sucedido do aluno de pós-graduação;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 400);
        Assert.assertEquals(3, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        // Esse livro não tem exemplares disponíveis;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 401);
        Assert.assertEquals(3, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 400);
        Assert.assertEquals(3, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        // Quarto emprestimo bem sucedido do aluno de pós-graduação;
        // Chegou ao limite maximo de emprestimos do aluno de graduaçao (Limite
        // Pós Graduação = 4);
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 500);
        Assert.assertEquals(4, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(true, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        // A partir daqui, o aluno de pós-graduação não pode mais fazer empréstimos;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 501);
        Assert.assertEquals(4, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(true, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 600);
        Assert.assertEquals(4, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(true, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);

        Assert.assertEquals(4, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(true, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());
    }
}
