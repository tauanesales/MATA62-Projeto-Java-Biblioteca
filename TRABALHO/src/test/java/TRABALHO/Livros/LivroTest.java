package TRABALHO.Livros;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;

public class LivroTest extends BaseTest {
    @Test
    public void basicLivroFunctionsAreWorkingTest() {
        Assert.assertEquals(0, livro1.getObservadores().size());
        livro1.adicionarObservador(professor);
        Assert.assertEquals(1, livro1.getObservadores().size());
        Assert.assertEquals(professor, livro1.getObservadores().get(0));

        Assert.assertEquals(0, livro1.getQuantidadeDeReservas());
        livro1.decrementarQuantidadeDeReservas();
        Assert.assertEquals(0, livro1.getQuantidadeDeReservas());

        livro1.incrementarQuantidadeDeReservas();
        Assert.assertEquals(1, livro1.getQuantidadeDeReservas());
        livro1.incrementarQuantidadeDeReservas();
        Assert.assertEquals(2, livro1.getQuantidadeDeReservas());

        livro1.decrementarQuantidadeDeReservas();
        Assert.assertEquals(1, livro1.getQuantidadeDeReservas());
        livro1.decrementarQuantidadeDeReservas();
        Assert.assertEquals(0, livro1.getQuantidadeDeReservas());
        livro1.decrementarQuantidadeDeReservas();
        Assert.assertEquals(0, livro1.getQuantidadeDeReservas());

        livro1.incrementarQuantidadeDeReservas();
        livro1.incrementarQuantidadeDeReservas();
        livro1.incrementarQuantidadeDeReservas();
        
        Assert.assertEquals(3, livro1.getQuantidadeDeReservas());
        livro1.decrementarQuantidadeDeReservas();
        Assert.assertEquals(2, livro1.getQuantidadeDeReservas());

        Assert.assertEquals(1, professor.getQuantidadeDeVezesQueFoiNotificado());
    }
}
