package TRABALHO.Commands;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;

public class ObservarLivroCommandTest extends BaseTest {
    @Test
    public void executeTest() {
        String codigoAlunoGrad = String.valueOf(alunoGrad.getCodigo());
        String codigoProfessor = String.valueOf(professor.getCodigo());
        String codigoLivro1 = String.valueOf(exemplar1.getCodigoLivro());
        String codigoLivro2 = String.valueOf(exemplar2.getCodigoLivro());

        biblioteca.executeCommand("obs", codigoAlunoGrad, codigoLivro1);
        Assert.assertEquals(0, exemplar1.getLivro().getObservadores().size());
        biblioteca.executeCommand("obs", "-1", codigoLivro1);
        Assert.assertEquals(0, exemplar1.getLivro().getObservadores().size());

        biblioteca.executeCommand("obs", codigoProfessor, codigoLivro1);
        Assert.assertEquals(1, exemplar1.getLivro().getObservadores().size());
        Assert.assertEquals(professor, exemplar1.getLivro().getObservadores().get(0));

        biblioteca.executeCommand("obs", codigoAlunoGrad, codigoLivro2);
        Assert.assertEquals(0, exemplar2.getLivro().getObservadores().size());

        biblioteca.executeCommand("obs", codigoProfessor, codigoLivro2);
        Assert.assertEquals(1, exemplar2.getLivro().getObservadores().size());
        Assert.assertEquals(professor, exemplar2.getLivro().getObservadores().get(0));
    }

    @Test
    public void professorRecebeNotificacoesDeLivrosObservadosTest() {
        String codigoAlunoGrad = String.valueOf(alunoGrad.getCodigo());
        String codigoAlunoPosGrad = String.valueOf(alunoPosGrad.getCodigo());
        String codigoProfessor = String.valueOf(professor.getCodigo());
        String codigoLivro1 = String.valueOf(exemplar1.getCodigoLivro());
        String codigoLivro2 = String.valueOf(exemplar2.getCodigoLivro());

        biblioteca.executeCommand("obs", codigoProfessor, codigoLivro1);
        biblioteca.executeCommand("obs", codigoProfessor, codigoLivro2);

        Assert.assertEquals(0, professor.getQuantidadeDeVezesQueFoiNotificado());

        biblioteca.executeCommand("res", codigoAlunoGrad, codigoLivro1);
        biblioteca.executeCommand("res", codigoAlunoPosGrad, codigoLivro1);
        
        Assert.assertEquals(0, professor.getQuantidadeDeVezesQueFoiNotificado());
        
        biblioteca.executeCommand("res", "789", codigoLivro1);
        
        Assert.assertEquals(1, professor.getQuantidadeDeVezesQueFoiNotificado());
    }
}
