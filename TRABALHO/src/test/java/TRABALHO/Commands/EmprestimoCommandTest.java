package TRABALHO.Commands;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;

public class EmprestimoCommandTest extends BaseTest {
    @Test
    public void executeTest() {
        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Usuário inválido;
        biblioteca.executeCommand("emp", -1, exemplar1.getCodigo());
        // Livro inválido;
        biblioteca.executeCommand("emp", aluno.getCodigo(), 1);

        Assert.assertTrue(exemplar1.isDisponivel());
        // Classe Aluno "pura" não pode fazer empréstimos;
        biblioteca.executeCommand("emp", aluno.getCodigo(), exemplar1.getCodigo());

        Assert.assertTrue(exemplar1.isDisponivel());
        // Empréstimo bem sucedido;
        biblioteca.executeCommand("emp", alunoPosGrad.getCodigo(), exemplar1.getCodigo());
        Assert.assertFalse(exemplar1.isDisponivel());

        Assert.assertEquals(1, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        // Exemplar indisponível;
        biblioteca.executeCommand("emp", alunoPosGrad.getCodigo(), exemplar1.getCodigo());
        Assert.assertFalse(exemplar1.isDisponivel());

        Assert.assertTrue(exemplar2.isDisponivel());
        // Empréstimo bem sucedido;
        biblioteca.executeCommand("emp", professor.getCodigo(), exemplar2.getCodigo());
        Assert.assertFalse(exemplar2.isDisponivel());

        // Emprestimo bem sucedido;
        biblioteca.executeCommand("emp", professor.getCodigo(), 101);

        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Primeiro empréstimo bem sucedido do aluno de graduação;
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), 100);
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Exemplar indisponível;
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), exemplar1.getCodigo());
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), exemplar1.getCodigo());
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), exemplar1.getCodigo());
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), exemplar1.getCodigo());
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Exemplar indisponível;
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), exemplar1.getCodigo());
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), 100);
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), 100);
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), 100);
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), 100);

        // Exemplar indisponível;
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), exemplar2.getCodigo());

        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Segundo emprestimo bem sucedido do aluno de graduação;
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), 300);
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Esse livro não tem exemplares disponíveis;
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), 301);
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Terceiro emprestimo bem sucedido do aluno de graduação;
        // Chegou ao limite maximo de emprestimos do aluno de graduaçao (Limite
        // Graduação = 3);
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), 400);
        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // A partir daqui, o aluno de graduação não pode mais fazer empréstimos;
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), 401);
        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), 400);
        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), 500);
        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), 501);
        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), 600);
        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), 100);
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), 100);
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), 100);
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), 100);

        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());
    }
}
