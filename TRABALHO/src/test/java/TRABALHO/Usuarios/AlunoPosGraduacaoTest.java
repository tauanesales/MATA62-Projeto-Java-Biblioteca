package TRABALHO.Usuarios;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;

import org.junit.Before;

public class AlunoPosGraduacaoTest extends BaseTest {
    private static AlunoPosGraduacao alunoPosGrad;

    @Before
    public void setUp() {
        alunoPosGrad = new AlunoPosGraduacao(1, "AlunoPosGraduacao");
    }

    @Test
    public void maxEmprestimosTest() {
        Assert.assertEquals(4, alunoPosGrad.maxEmprestimos());
    }

    @Test
    public void tempoDeEmprestimoMaximoTest() {
        Assert.assertEquals(4 * 24 * 60 * 60 * 1000, alunoPosGrad.tempoDeEmprestimoMaximo());
    }

    
  // @Test
  // public void atingiuLimiteMaximoDeEmprestimosTest() {
  //   Assert.assertEquals(false, alunoGrad.atingiuLimiteMaximoDeEmprestimos());

  //   biblioteca.realizarEmprestimo(-1, 1001);
  //   biblioteca.realizarEmprestimo(aluno.getCodigo(), 1);
  //   biblioteca.realizarEmprestimo(aluno.getCodigo(), 1001);
  //   biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 1001);
  //   biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), 1001);
  //   biblioteca.realizarEmprestimo(professor.getCodigo(), 1002);
  //   biblioteca.realizarEmprestimo(professor.getCodigo(), 101);

  //   Assert.assertEquals(0, alunoPosGrad.emprestimosAbertos());
  //   Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());
  //   Assert.assertEquals(0, alunoPosGrad.emprestimosAbertos());

  //   // Primeiro emprestimo bem sucedido
  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);
  //   Assert.assertEquals(1, alunoPosGrad.emprestimosAbertos());
  //   Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 1001);
  //   Assert.assertEquals(1, alunoPosGrad.emprestimosAbertos());
  //   Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);
  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);
  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);
  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);

  //   Assert.assertEquals(1, alunoPosGrad.emprestimosAbertos());
  //   Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);
  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 1002);

  //   Assert.assertEquals(1, alunoPosGrad.emprestimosAbertos());
  //   Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

  //   // Segundo emprestimo bem sucedido
  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 300);
  //   Assert.assertEquals(2, alunoPosGrad.emprestimosAbertos());
  //   Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 301);
  //   Assert.assertEquals(2, alunoPosGrad.emprestimosAbertos());
  //   Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

  //   // Terceiro emprestimo bem sucedido
  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 400);
  //   Assert.assertEquals(3, alunoPosGrad.emprestimosAbertos());
  //   Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 401);
  //   Assert.assertEquals(3, alunoPosGrad.emprestimosAbertos());
  //   Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 400);
  //   Assert.assertEquals(3, alunoPosGrad.emprestimosAbertos());
  //   Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

  //   // Quarto emprestimo bem sucedido
  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 500);
  //   Assert.assertEquals(4, alunoPosGrad.emprestimosAbertos());
  //   Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

  //   // Atingiu limite maximo de emprestimos
  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 501);
  //   Assert.assertEquals(4, alunoPosGrad.emprestimosAbertos());
  //   Assert.assertEquals(true, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());
    
  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 600);
  //   Assert.assertEquals(4, alunoPosGrad.emprestimosAbertos());
  //   Assert.assertEquals(true, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());
    
  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);
  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);
  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);
  //   biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);

  //   Assert.assertEquals(4, alunoPosGrad.emprestimosAbertos());
  //   Assert.assertEquals(true, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());
  // }
}
