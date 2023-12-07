package TRABALHO.SistemaBiblioteca;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;
import TRABALHO.Livros.EstadoExemplar.ExemplarEmprestado;

public class SistemaBibliotecaTest extends BaseTest {
    @Test
    public void commandDesconhecidoNaoLancaExcecaoTest() {
        biblioteca.executeCommand("comandoDesconhecido", "0", "0");
    }

    @Test
    public void realizarDevolucaoNaoDevolveExemplaresEmprestadosAOutroUsuarioTest() {
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar2.getCodigoLivro());

        Assert.assertEquals(2, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(0, professor.quantidadeDeEmprestimosEmAberto());

        biblioteca.realizarDevolucao(professor.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarDevolucao(professor.getCodigo(), exemplar2.getCodigoLivro());

        Assert.assertEquals(2, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(0, professor.quantidadeDeEmprestimosEmAberto());
    }

    @Test
    public void realizarDevolucaoDevolveCorretamenteTest() {
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar2.getCodigoLivro());

        Assert.assertEquals(2, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(0, professor.quantidadeDeEmprestimosEmAberto());

        biblioteca.realizarDevolucao(alunoPosGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarDevolucao(alunoPosGrad.getCodigo(), exemplar2.getCodigoLivro());

        Assert.assertEquals(0, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(0, professor.quantidadeDeEmprestimosEmAberto());
    }

    @Test
    public void realizarEmprestimoNaoEmprestaExemplaresJaEmprestadosTest() {
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar2.getCodigoLivro());

        Assert.assertEquals(2, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(0, professor.quantidadeDeEmprestimosEmAberto());

        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar2.getCodigoLivro());

        Assert.assertEquals(2, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(0, professor.quantidadeDeEmprestimosEmAberto());
    }

    @Test
    public void realizarEmprestimoFuncionaComExemplaresJaDevolvidosTest() {
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar2.getCodigoLivro());

        Assert.assertEquals(2, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(0, professor.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(exemplar1,
                alunoPosGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar1.getCodigoLivro()).getExemplar());
        Assert.assertEquals(exemplar2,
                alunoPosGrad.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar2.getCodigoLivro()).getExemplar());

        biblioteca.realizarDevolucao(alunoPosGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarDevolucao(alunoPosGrad.getCodigo(), exemplar2.getCodigoLivro());

        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(professor.getCodigo(), exemplar2.getCodigoLivro());

        Assert.assertEquals(0, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(2, professor.quantidadeDeEmprestimosEmAberto());

        Assert.assertEquals(exemplar1,
                professor.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar1.getCodigoLivro()).getExemplar());
        Assert.assertEquals(exemplar2,
                professor.obterEmprestimoEmAbertoPorCodigoDoLivro(exemplar2.getCodigoLivro()).getExemplar());
    }

    @Test
    public void realizarEmprestimoNaoEmprestaExemplaresParaUsuariosComAtraso() {
        // Pegou e devolveu exemplar 2;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar2.getCodigoLivro());
        biblioteca.realizarDevolucao(alunoPosGrad.getCodigo(), exemplar2.getCodigoLivro());
        Assert.assertEquals(0, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(1, alunoPosGrad.obterEmprestimos(false).size());

        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1);

        // Entra em situação de atraso por ter um empréstimo antigo;
        exemplar1.setEstado(new ExemplarEmprestado(exemplar1, alunoPosGrad, db, calendar.getTime()));
        Assert.assertTrue(alunoPosGrad.temAtraso());
        Assert.assertEquals(1, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(2, alunoPosGrad.obterEmprestimos(false).size());

        // Tenta pegar novos empréstimos;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar2.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);

        // Não consegue pegar novos empréstimos;
        Assert.assertEquals(1, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(2, alunoPosGrad.obterEmprestimos(false).size());
        Assert.assertEquals(exemplar1, alunoPosGrad.obterEmprestimos(true).get(0).getExemplar());

        // Devolve exemplar 1;
        biblioteca.realizarDevolucao(alunoPosGrad.getCodigo(), exemplar1.getCodigoLivro());
        Assert.assertEquals(0, alunoPosGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(2, alunoPosGrad.obterEmprestimos(false).size());

        // Consegue pegar novos empréstimos;
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), exemplar2.getCodigoLivro());
        biblioteca.realizarEmprestimo(alunoPosGrad.getCodigo(), 100);

        Assert.assertEquals(3, alunoPosGrad.quantidadeDeEmprestimosEmAberto());

        Assert.assertEquals(exemplar1, alunoPosGrad.obterEmprestimos(true).get(0).getExemplar());
        Assert.assertEquals(exemplar2, alunoPosGrad.obterEmprestimos(true).get(1).getExemplar());
        Assert.assertEquals(100, alunoPosGrad.obterEmprestimos(true).get(2).getExemplar().getCodigoLivro());

        Assert.assertEquals(5, alunoPosGrad.obterEmprestimos(false).size());
    }

    @Test
    public void realizarReservaNaoUltrapassaLimiteDeReservasTest() {
        biblioteca.realizarReserva(alunoPosGrad.getCodigo(), exemplar1.getCodigoLivro());
        biblioteca.realizarReserva(alunoPosGrad.getCodigo(), exemplar2.getCodigoLivro());
        biblioteca.realizarReserva(alunoPosGrad.getCodigo(), 100);

        Assert.assertEquals(3, alunoPosGrad.obterReservasAtivas().size());
        Assert.assertEquals(exemplar1.getLivro(), alunoPosGrad.obterReservasAtivas().get(0).getLivro());
        Assert.assertEquals(exemplar2.getLivro(), alunoPosGrad.obterReservasAtivas().get(1).getLivro());
        Assert.assertEquals(100, alunoPosGrad.obterReservasAtivas().get(2).getLivro().getCodigo());

        biblioteca.realizarReserva(alunoPosGrad.getCodigo(), 101);
        biblioteca.realizarReserva(alunoPosGrad.getCodigo(), 200);
        biblioteca.realizarReserva(alunoPosGrad.getCodigo(), 201);
        biblioteca.realizarReserva(alunoPosGrad.getCodigo(), 400);
        biblioteca.realizarReserva(alunoPosGrad.getCodigo(), 500);
        biblioteca.realizarReserva(alunoPosGrad.getCodigo(), 600);

        Assert.assertEquals(alunoPosGrad.maxReservas(), alunoPosGrad.obterReservasAtivas().size());
        Assert.assertTrue(alunoPosGrad.atingiuLimiteMaximoDeReservas());
    }
}
