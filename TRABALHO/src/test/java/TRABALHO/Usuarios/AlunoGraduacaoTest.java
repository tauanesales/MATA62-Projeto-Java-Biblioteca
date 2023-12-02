package TRABALHO.Usuarios;

import TRABALHO.BaseTest;
import org.junit.Assert;
// import org.junit.BeforeClass;
import org.junit.Test;

public class AlunoGraduacaoTest extends BaseTest {
  @Test
  public void maxEmprestimosTest() {
    Assert.assertEquals(3, alunoGrad.maxEmprestimos());
  }

  @Test
  public void tempoDeEmprestimoMaximoTest() {
    Assert.assertEquals(3 * 24 * 60 * 60 * 1000,
                        alunoGrad.tempoDeEmprestimoMaximo());
  }

  @Test
  public void atingiuLimiteMaximoDeEmprestimosTest() {
    Assert.assertEquals(0, alunoGrad.emprestimosAbertos());
    Assert.assertEquals(false, alunoGrad.atingiuLimiteMaximoDeEmprestimos());

    biblioteca.realizarEmprestimo(-1, exemplar1.getCodigo());
    biblioteca.realizarEmprestimo(aluno.getCodigo(), 1);
    biblioteca.realizarEmprestimo(aluno.getCodigo(), exemplar1.getCodigo());
    biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigo());
    Assert.assertFalse(exemplar1.isDisponivel());
    Assert.assertEquals(1, alunoGrad.emprestimosAbertos());
    biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigo());
    Assert.assertFalse(exemplar1.isDisponivel());

    biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar2.getCodigo());
    Assert.assertFalse(exemplar2.isDisponivel());

    biblioteca.realizarEmprestimo(professor.getCodigo(), 101);

    Assert.assertEquals(0, alunoGrad.emprestimosAbertos());
    Assert.assertEquals(false, alunoGrad.atingiuLimiteMaximoDeEmprestimos());

    // Primeiro emprestimo bem sucedido
    biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
    Assert.assertEquals(1, alunoGrad.emprestimosAbertos());
    Assert.assertEquals(false, alunoGrad.atingiuLimiteMaximoDeEmprestimos());

    biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigo());
    biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigo());
    biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigo());
    biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigo());
    Assert.assertEquals(1, alunoGrad.emprestimosAbertos());
    Assert.assertEquals(false, alunoGrad.atingiuLimiteMaximoDeEmprestimos());

    biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigo());
    Assert.assertEquals(1, alunoGrad.emprestimosAbertos());
    Assert.assertEquals(false, alunoGrad.atingiuLimiteMaximoDeEmprestimos());
    biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
    Assert.assertEquals(1, alunoGrad.emprestimosAbertos());
    Assert.assertEquals(false, alunoGrad.atingiuLimiteMaximoDeEmprestimos());
    biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
    Assert.assertEquals(1, alunoGrad.emprestimosAbertos());
    Assert.assertEquals(false, alunoGrad.atingiuLimiteMaximoDeEmprestimos());
    biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);

    Assert.assertEquals(1, alunoGrad.emprestimosAbertos());
    Assert.assertEquals(false, alunoGrad.atingiuLimiteMaximoDeEmprestimos());

    // biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
    // biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar2.getCodigo());

    // Assert.assertEquals(1, alunoGrad.emprestimosAbertos());
    // Assert.assertEquals(false, alunoGrad.atingiuLimiteMaximoDeEmprestimos());

    // // Segundo emprestimo bem sucedido
    // biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 300);
    // Assert.assertEquals(2, alunoGrad.emprestimosAbertos());
    // Assert.assertEquals(false, alunoGrad.atingiuLimiteMaximoDeEmprestimos());

    // biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 301);
    // Assert.assertEquals(2, alunoGrad.emprestimosAbertos());
    // Assert.assertEquals(false, alunoGrad.atingiuLimiteMaximoDeEmprestimos());

    // // Atingiu limite maximo de emprestimos do aluno de graduaçao (3)
    // biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 400);
    // Assert.assertEquals(3, alunoGrad.emprestimosAbertos());
    // Assert.assertEquals(true, alunoGrad.atingiuLimiteMaximoDeEmprestimos());

    // biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 401);
    // Assert.assertEquals(3, alunoGrad.emprestimosAbertos());
    // Assert.assertEquals(true, alunoGrad.atingiuLimiteMaximoDeEmprestimos());

    // biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 400);
    // Assert.assertEquals(3, alunoGrad.emprestimosAbertos());
    // Assert.assertEquals(true, alunoGrad.atingiuLimiteMaximoDeEmprestimos());

    // biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 500);
    // Assert.assertEquals(3, alunoGrad.emprestimosAbertos());
    // Assert.assertEquals(true, alunoGrad.atingiuLimiteMaximoDeEmprestimos());

    // biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 501);
    // Assert.assertEquals(3, alunoGrad.emprestimosAbertos());
    // Assert.assertEquals(true, alunoGrad.atingiuLimiteMaximoDeEmprestimos());

    // biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 600);
    // Assert.assertEquals(3, alunoGrad.emprestimosAbertos());
    // Assert.assertEquals(true, alunoGrad.atingiuLimiteMaximoDeEmprestimos());

    // biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
    // biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
    // biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);
    // biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 100);

    // Assert.assertEquals(3, alunoGrad.emprestimosAbertos());
    // Assert.assertEquals(true, alunoGrad.atingiuLimiteMaximoDeEmprestimos());
  }
}
