package TRABALHO.Commands;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;

public class EmprestimoCommandTest extends BaseTest {
    @Test
    public void executeTest() {
        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        String codigoAlunoGrad = String.valueOf(alunoGrad.getCodigo());
        String codigoAlunoPosGrad = String.valueOf(alunoPosGrad.getCodigo());
        String codigoProfessor = String.valueOf(professor.getCodigo());
        String codigoLivro1 = String.valueOf(exemplar1.getCodigoLivro());
        String codigoLivro2 = String.valueOf(exemplar2.getCodigoLivro());

        // Usuário inválido;
        biblioteca.executeCommand("emp", "-1", codigoLivro1);
        // Livro inválido;
        biblioteca.executeCommand("emp", codigoProfessor, "1");

        Assert.assertTrue(exemplar1.isDisponivel());
        // Empréstimo bem sucedido;
        biblioteca.executeCommand("emp", codigoAlunoPosGrad, codigoLivro1);
        Assert.assertFalse(exemplar1.isDisponivel());

        Assert.assertEquals(1, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        // Exemplar indisponível;
        biblioteca.executeCommand("emp", codigoAlunoPosGrad, codigoLivro1);
        Assert.assertFalse(exemplar1.isDisponivel());

        Assert.assertTrue(exemplar2.isDisponivel());
        // Empréstimo bem sucedido;
        biblioteca.executeCommand("emp", codigoProfessor, codigoLivro2);
        Assert.assertFalse(exemplar2.isDisponivel());

        // Emprestimo bem sucedido;
        biblioteca.executeCommand("emp", codigoProfessor, "101");

        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Primeiro empréstimo bem sucedido do aluno de graduação;
        biblioteca.executeCommand("emp", codigoAlunoGrad, "100");
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Exemplar indisponível;
        biblioteca.executeCommand("emp", codigoAlunoGrad, codigoLivro1);
        biblioteca.executeCommand("emp", codigoAlunoGrad, codigoLivro1);
        biblioteca.executeCommand("emp", codigoAlunoGrad, codigoLivro1);
        biblioteca.executeCommand("emp", codigoAlunoGrad, codigoLivro1);
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Exemplar indisponível;
        biblioteca.executeCommand("emp", codigoAlunoGrad, codigoLivro1);
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.executeCommand("emp", codigoAlunoGrad, "100");
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.executeCommand("emp", codigoAlunoGrad, "100");
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.executeCommand("emp", codigoAlunoGrad, "100");
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.executeCommand("emp", codigoAlunoGrad, "100");

        // Exemplar indisponível;
        biblioteca.executeCommand("emp", codigoAlunoGrad, codigoLivro2);

        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Esse livro não tem exemplares disponíveis;
        biblioteca.executeCommand("emp", codigoAlunoGrad, "301");
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Segundo emprestimo bem sucedido do aluno de graduação;
        // Chegou ao limite maximo de emprestimos do aluno de graduaçao (Limite
        // Graduação = 2);
        biblioteca.executeCommand("emp", codigoAlunoGrad, "400");
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // A partir daqui, o aluno de graduação não pode mais fazer empréstimos;
        biblioteca.executeCommand("emp", codigoAlunoGrad, "401");
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("emp", codigoAlunoGrad, "400");
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("emp", codigoAlunoGrad, "500");
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("emp", codigoAlunoGrad, "501");
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("emp", codigoAlunoGrad, "600");
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("emp", codigoAlunoGrad, "100");
        biblioteca.executeCommand("emp", codigoAlunoGrad, "100");
        biblioteca.executeCommand("emp", codigoAlunoGrad, "100");
        biblioteca.executeCommand("emp", codigoAlunoGrad, "100");

        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());
    }
}
