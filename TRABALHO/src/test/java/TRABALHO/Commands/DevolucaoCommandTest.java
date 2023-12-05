package TRABALHO.Commands;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;

public class DevolucaoCommandTest extends BaseTest {
    @Test
    public void executeTest() {
        String codigoAlunoGrad = String.valueOf(alunoGrad.getCodigo());
        String codigoExemplar1 = String.valueOf(exemplar1.getCodigo());
        String codigoExemplar2 = String.valueOf(exemplar2.getCodigo());

        biblioteca.executeCommand("emp", codigoAlunoGrad, codigoExemplar1);
        biblioteca.executeCommand("emp", codigoAlunoGrad, codigoExemplar2);
        biblioteca.executeCommand("emp", codigoAlunoGrad, "200");

        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(alunoGrad.maxEmprestimos(), alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoAlunoGrad, codigoExemplar1);
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoAlunoGrad, codigoExemplar2);
        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        biblioteca.executeCommand("dev", codigoAlunoGrad, "200");
        Assert.assertEquals(0, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());
    }
}
