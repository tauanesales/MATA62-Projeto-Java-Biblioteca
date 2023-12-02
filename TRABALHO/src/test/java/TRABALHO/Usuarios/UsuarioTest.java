package TRABALHO.Usuarios;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;

public class UsuarioTest extends BaseTest {
    @Test
    public void maxEmprestimosTest() {
        Assert.assertEquals(0, usuario.maxEmprestimos());
    }

    @Test
    public void tempoDeEmprestimoMaximoTest() {
        Assert.assertEquals(0, usuario.tempoDeEmprestimoMaximo());
    }

    @Test
    public void emprestimosAbertosTest() {
        Assert.assertEquals(0, usuario.emprestimosAbertos());
    }

    @Test
    public void atingiuLimiteMaximoDeEmprestimosTest() {
        // Não possui emprestimos em aberto;
        Assert.assertEquals(0, usuario.emprestimosAbertos());
        // Limite máximo de empréstimos é 0;
        Assert.assertEquals(0, usuario.maxEmprestimos());
        // Por consequência lógica, já atingiu o limite máximo de empréstimos;
        Assert.assertTrue(usuario.emprestimosAbertos() >= usuario.maxEmprestimos());
        Assert.assertEquals(true, usuario.atingiuLimiteMaximoDeEmprestimos());

        // Confirmando que existem classes de aluno que não atingiram o limite de
        // empréstimos ainda;
        Assert.assertEquals(0, alunoPosGrad.emprestimosAbertos());
        Assert.assertEquals(false, alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

        // Usuário inválido;
        biblioteca.realizarEmprestimo(-1, exemplar1.getCodigo());
        // Livro inválido;
        biblioteca.realizarEmprestimo(usuario.getCodigo(), 1);

        Assert.assertTrue(exemplar1.isDisponivel());
        // Classe Usuário "puro" não pode fazer empréstimos;
        biblioteca.realizarEmprestimo(usuario.getCodigo(), exemplar1.getCodigo());
        biblioteca.realizarEmprestimo(usuario.getCodigo(), exemplar2.getCodigo());
        biblioteca.realizarEmprestimo(usuario.getCodigo(), 1);
        biblioteca.realizarEmprestimo(usuario.getCodigo(), 1001);
        biblioteca.realizarEmprestimo(usuario.getCodigo(), 1002);
        biblioteca.realizarEmprestimo(usuario.getCodigo(), 100);
        biblioteca.realizarEmprestimo(usuario.getCodigo(), 0);

        Assert.assertEquals(0, usuario.emprestimosAbertos());
        Assert.assertEquals(true, usuario.atingiuLimiteMaximoDeEmprestimos());

        Assert.assertTrue(exemplar1.isDisponivel());
        // Empréstimo bem sucedido;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigo());
        Assert.assertFalse(exemplar1.isDisponivel());

        Assert.assertEquals(1, alunoGrad.emprestimosAbertos());
        // Exemplar indisponível;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigo());
        Assert.assertFalse(exemplar1.isDisponivel());

        Assert.assertTrue(exemplar2.isDisponivel());
        // Empréstimo bem sucedido;
        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar2.getCodigo());
        Assert.assertFalse(exemplar2.isDisponivel());

        // Emprestimo bem sucedido;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 101);

        Assert.assertEquals(0, usuario.emprestimosAbertos());
        Assert.assertEquals(true, usuario.atingiuLimiteMaximoDeEmprestimos());
    }
}
