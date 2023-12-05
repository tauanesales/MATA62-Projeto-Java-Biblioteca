package TRABALHO.Commands;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;

public class EmprestimoCommandTest extends BaseTest {
    @Test
    public void executeTest() {
        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        String codigoAluno = String.valueOf(aluno.getCodigo());
        String codigoAlunoGrad = String.valueOf(alunoGrad.getCodigo());
        String codigoAlunoPosGrad = String.valueOf(alunoPosGrad.getCodigo());
        String codigoProfessor = String.valueOf(professor.getCodigo());
        String codigoExemplar1 = String.valueOf(exemplar1.getCodigo());
        String codigoExemplar2 = String.valueOf(exemplar2.getCodigo());

        // Usuário inválido;
        biblioteca.executeCommand("emp", "-1", codigoExemplar1);
        // Livro inválido;
        biblioteca.executeCommand("emp", codigoAluno, "1");

        Assert.assertTrue(exemplar1.isDisponivel());
        // Classe Aluno "pura" não pode fazer empréstimos;
        biblioteca.executeCommand("emp", codigoAluno, codigoExemplar1);

        Assert.assertTrue(exemplar1.isDisponivel());
        // Empréstimo bem sucedido;
        biblioteca.executeCommand("emp", codigoAlunoPosGrad, codigoExemplar1);
        Assert.assertFalse(exemplar1.isDisponivel());

        Assert.assertEquals(1, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        // Exemplar indisponível;
        biblioteca.executeCommand("emp", codigoAlunoPosGrad, codigoExemplar1);
        Assert.assertFalse(exemplar1.isDisponivel());

        Assert.assertTrue(exemplar2.isDisponivel());
        // Empréstimo bem sucedido;
        biblioteca.executeCommand("emp", codigoProfessor, codigoExemplar2);
        Assert.assertFalse(exemplar2.isDisponivel());

        // Emprestimo bem sucedido;
        biblioteca.executeCommand("emp", codigoProfessor, "101");

        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Primeiro empréstimo bem sucedido do aluno de graduação;
        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), "100");
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Exemplar indisponível;
        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), codigoExemplar1);
        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), codigoExemplar1);
        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), codigoExemplar1);
        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), codigoExemplar1);
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Exemplar indisponível;
        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), codigoExemplar1);
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), "100");
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), "100");
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), "100");
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Já pegou esse livro emprestado, portanto não pode pegar novamente;
        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), "100");

        // Exemplar indisponível;
        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), codigoExemplar2);

        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Segundo emprestimo bem sucedido do aluno de graduação;
        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), "300");
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Esse livro não tem exemplares disponíveis;
        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), "301");
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // Terceiro emprestimo bem sucedido do aluno de graduação;
        // Chegou ao limite maximo de emprestimos do aluno de graduaçao (Limite
        // Graduação = 3);
        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), "400");
        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        // A partir daqui, o aluno de graduação não pode mais fazer empréstimos;
        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), "401");
        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), "400");
        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), "500");
        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), "501");
        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), "600");
        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), "100");
        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), "100");
        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), "100");
        biblioteca.executeCommand("emp", String.valueOf(codigoAlunoGrad), "100");

        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());
    }
}
