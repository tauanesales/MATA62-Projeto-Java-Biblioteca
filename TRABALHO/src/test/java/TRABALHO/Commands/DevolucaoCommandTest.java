package TRABALHO.Commands;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;

public class DevolucaoCommandTest extends BaseTest {
    @Test
    public void executeTest() {
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
}
