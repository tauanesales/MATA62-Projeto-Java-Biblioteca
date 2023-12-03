package TRABALHO.Usuarios;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;

public class UsuarioTest extends BaseTest {
    @Test
    public void getCodigoTest() {
        Assert.assertEquals(0, usuario.getCodigo());
    }

    @Test
    public void getNomeTest() {
        Assert.assertEquals("Usuário", usuario.getNome());
    }

    @Test
    public void temAtrasoTest() {
        Assert.assertFalse(usuario.temAtraso());
    }

    @Test
    public void obterEmprestimosTest() {
        Assert.assertEquals(0, usuario.obterEmprestimos(true).size());
        Assert.assertEquals(0, usuario.obterEmprestimos(false).size());

        biblioteca.realizarEmprestimo(usuario.getCodigo(), exemplar1.getCodigo());
        Assert.assertEquals(0, usuario.obterEmprestimos(true).size());
        Assert.assertEquals(0, usuario.obterEmprestimos(false).size());
    }

    @Test
    public void quantidadeDeEmprestimosEmAbertoTest() {
        Assert.assertEquals(0, usuario.quantidadeDeEmprestimosEmAberto());

        biblioteca.realizarEmprestimo(usuario.getCodigo(), exemplar1.getCodigo());
        Assert.assertEquals(0, usuario.quantidadeDeEmprestimosEmAberto());
    }

    @Test
    public void maxEmprestimosTest() {
        Assert.assertEquals(0, usuario.maxEmprestimos());
    }

    @Test
    public void atingiuLimiteMaximoDeEmprestimosTest() {
        // Não possui emprestimos em aberto;
        Assert.assertEquals(0, usuario.quantidadeDeEmprestimosEmAberto());
        // Limite máximo de empréstimos é 0;
        Assert.assertEquals(0, usuario.maxEmprestimos());
        // Por consequência lógica, já atingiu o limite máximo de empréstimos;
        Assert.assertTrue(usuario.quantidadeDeEmprestimosEmAberto() >= usuario.maxEmprestimos());
        Assert.assertTrue(usuario.atingiuLimiteMaximoDeEmprestimos());

        // Confirmando que existem classes de aluno que não atingiram o limite de
        // empréstimos ainda;
        Assert.assertEquals(0, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoPosGrad.atingiuLimiteMaximoDeEmprestimos());

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

        Assert.assertEquals(0, usuario.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(usuario.atingiuLimiteMaximoDeEmprestimos());

        Assert.assertTrue(exemplar1.isDisponivel());
        // Empréstimo bem sucedido;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigo());
        Assert.assertFalse(exemplar1.isDisponivel());

        Assert.assertEquals(1, alunoGrad.quantidadeDeEmprestimosEmAberto());
        // Exemplar indisponível;
        biblioteca.realizarEmprestimo(alunoGrad.getCodigo(), exemplar1.getCodigo());
        Assert.assertFalse(exemplar1.isDisponivel());

        Assert.assertTrue(exemplar2.isDisponivel());
        // Empréstimo bem sucedido;
        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar2.getCodigo());
        Assert.assertFalse(exemplar2.isDisponivel());

        // Emprestimo bem sucedido;
        biblioteca.realizarEmprestimo(professor.getCodigo(), 101);

        Assert.assertEquals(0, usuario.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(usuario.atingiuLimiteMaximoDeEmprestimos());
    }

    @Test
    public void tempoDeEmprestimoMaximoTest() {
        Assert.assertEquals(0, usuario.tempoDeEmprestimoMaximo());
    }

    @Test
    public void jaTemEmprestimoDoLivroTest() {
        Assert.assertFalse(usuario.jaTemEmprestimoDoLivro(exemplar1.getCodigo()));
        Assert.assertFalse(usuario.jaTemEmprestimoDoLivro(exemplar2.getCodigo()));

        biblioteca.realizarEmprestimo(usuario.getCodigo(), exemplar1.getCodigo());
        Assert.assertFalse(usuario.jaTemEmprestimoDoLivro(exemplar1.getCodigo()));
        Assert.assertFalse(usuario.jaTemEmprestimoDoLivro(exemplar2.getCodigo()));

        biblioteca.realizarEmprestimo(usuario.getCodigo(), exemplar2.getCodigo());
        Assert.assertFalse(usuario.jaTemEmprestimoDoLivro(exemplar1.getCodigo()));
        Assert.assertFalse(usuario.jaTemEmprestimoDoLivro(exemplar2.getCodigo()));
    }
}
