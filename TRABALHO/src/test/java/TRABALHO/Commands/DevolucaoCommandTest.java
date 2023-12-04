package TRABALHO.Commands;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;

public class DevolucaoCommandTest extends BaseTest {
    @Test
    public void executeTest() {
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), exemplar1.getCodigo());
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), exemplar2.getCodigo());
        biblioteca.executeCommand("emp", alunoGrad.getCodigo(), 200);

        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(alunoGrad.maxEmprestimos(), alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", alunoGrad.getCodigo(), exemplar1.getCodigo());
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", alunoGrad.getCodigo(), exemplar2.getCodigo());
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", alunoGrad.getCodigo(), 200);
        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());
    }
}
