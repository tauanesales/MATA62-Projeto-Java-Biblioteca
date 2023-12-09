package TRABALHO.Commands;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;

public class DevolucaoCommandTest extends BaseTest {
    @Test
    public void executeCommandAlunoGradTest() {
        String codigoAlunoGrad = String.valueOf(alunoGrad.getCodigo());
        String codigoLivro1 = String.valueOf(exemplar1.getCodigoLivro());
        String codigoLivro2 = String.valueOf(exemplar2.getCodigoLivro());

        biblioteca.executeCommand("emp", codigoAlunoGrad, codigoLivro1);
        biblioteca.executeCommand("emp", codigoAlunoGrad, codigoLivro2);
        biblioteca.executeCommand("emp", codigoAlunoGrad, "200");

        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(alunoGrad.maxEmprestimos(), alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoAlunoGrad, codigoLivro1);
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoAlunoGrad, codigoLivro2);
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoAlunoGrad, "200");
        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());
    }

    @Test
    public void executeCommandAlunoPosGradTest() {
        String codigoAlunoPosGrad = String.valueOf(alunoPosGrad.getCodigo());
        String codigoLivro1 = String.valueOf(exemplar1.getCodigoLivro());
        String codigoLivro2 = String.valueOf(exemplar2.getCodigoLivro());

        biblioteca.executeCommand("emp", codigoAlunoPosGrad, codigoLivro1);
        biblioteca.executeCommand("emp", codigoAlunoPosGrad, codigoLivro2);
        biblioteca.executeCommand("emp", codigoAlunoPosGrad, "200");
        biblioteca.executeCommand("emp", codigoAlunoPosGrad, "201");

        Assert.assertEquals(4, alunoPosGrad.quantidadeDeEmprestimosEmAberto());

        Assert.assertTrue(alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoAlunoPosGrad, codigoLivro1);
        Assert.assertEquals(3, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoAlunoPosGrad, codigoLivro2);
        Assert.assertEquals(2, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoAlunoPosGrad, "200");
        Assert.assertEquals(1, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoAlunoPosGrad, "201");
        Assert.assertEquals(0, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());
    }

    @Test
    public void executeCommandProfessorTest() {
        String codigoProfessor = String.valueOf(professor.getCodigo());
        String codigoLivro1 = String.valueOf(exemplar1.getCodigoLivro());
        String codigoLivro2 = String.valueOf(exemplar2.getCodigoLivro());

        biblioteca.executeCommand("emp", codigoProfessor, codigoLivro1);
        biblioteca.executeCommand("emp", codigoProfessor, codigoLivro2);
        biblioteca.executeCommand("emp", codigoProfessor, "200");
        biblioteca.executeCommand("emp", codigoProfessor, "201");
        biblioteca.executeCommand("emp", codigoProfessor, "300");
        biblioteca.executeCommand("emp", codigoProfessor, "400");

        Assert.assertEquals(6, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(6, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(professor.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoProfessor, codigoLivro1);
        Assert.assertEquals(5, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(professor.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoProfessor, codigoLivro2);
        Assert.assertEquals(4, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(professor.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoProfessor, "200");
        Assert.assertEquals(3, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(professor.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoProfessor, "201");
        Assert.assertEquals(2, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(professor.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoProfessor, "300");
        Assert.assertEquals(1, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(professor.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoProfessor, "400");
        Assert.assertEquals(0, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(professor.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoProfessor, codigoLivro1);
        Assert.assertEquals(0, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(professor.atingiuLimiteMaximoDeEmprestimos());
    }

    @Test
    public void executeCommandWontMakeUserHaveNegativeEmprestimosTest() {
        String codigoAlunoGrad = String.valueOf(alunoGrad.getCodigo());
        String codigoLivro1 = String.valueOf(exemplar1.getCodigoLivro());
        String codigoLivro2 = String.valueOf(exemplar2.getCodigoLivro());

        
        biblioteca.executeCommand("dev", codigoAlunoGrad, "200");
        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoAlunoGrad, codigoLivro1);
        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("emp", codigoAlunoGrad, codigoLivro1);
        biblioteca.executeCommand("emp", codigoAlunoGrad, codigoLivro2);
        biblioteca.executeCommand("emp", codigoAlunoGrad, "200");

        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(alunoGrad.maxEmprestimos(), alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoAlunoGrad, codigoLivro1);
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoAlunoGrad, codigoLivro2);
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoAlunoGrad, "200");
        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoAlunoGrad, codigoLivro1);
        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());
    }
}
