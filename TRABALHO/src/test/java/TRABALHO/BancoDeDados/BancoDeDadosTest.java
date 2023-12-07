package TRABALHO.BancoDeDados;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;
import TRABALHO.Livros.Exemplar.Exemplar;
import TRABALHO.Livros.Livro.Livro;
import TRABALHO.Usuarios.IUsuario;

public class BancoDeDadosTest extends BaseTest {
    @Test
    public void getUsuarioTest() {
        Assert.assertEquals(null, db.getUsuario(-1));

        for (IUsuario usuario : List.of(alunoGrad, alunoPosGrad, professor)) {
            IUsuario response = db.getUsuario(usuario.getCodigo());
            Assert.assertEquals(usuario.getClass(), response.getClass());
            Assert.assertEquals(usuario.getCodigo(), response.getCodigo());
            Assert.assertEquals(usuario.getNome(), response.getNome());
        }
    }

    @Test
    public void getExemplarTest() {
        Assert.assertEquals(null, db.getExemplar(0, 0));

        for (Exemplar exemplar : exemplares) {
            Exemplar response = db.getExemplar(exemplar.getCodigo(), exemplar.getCodigoLivro());
            Assert.assertEquals(exemplar.getClass(), response.getClass());
            Assert.assertEquals(exemplar.getCodigo(), response.getCodigo());
            Assert.assertEquals(exemplar.getCodigoLivro(), response.getCodigoLivro());
            Assert.assertEquals(exemplar.getTitulo(), response.getTitulo());
            Assert.assertEquals(exemplar.getEditora(), response.getEditora());
            Assert.assertEquals(exemplar.getAutores(), response.getAutores());
            Assert.assertEquals(exemplar.getEdicao(), response.getEdicao());
            Assert.assertEquals(exemplar.getAnoPublicacao(), response.getAnoPublicacao());
        }
    }

    @Test
    public void livroExisteTest() {
        Assert.assertEquals(false, db.livroExiste(0));

        for (Exemplar exemplar : exemplares) {
            Assert.assertEquals(true, db.livroExiste(exemplar.getCodigoLivro()));
        }
    }

    @Test
    public void getExemplarDisponivelPorCodigoLivroTest() {
        Assert.assertEquals(null, db.getExemplarDisponivelPorCodigoLivro(0));

        for (Livro livro : livros) {
            Exemplar response = db.getExemplarDisponivelPorCodigoLivro(livro.getCodigo());
            if (response == null) {
                continue;
            }
            Assert.assertEquals(livro.getClass(), response.getLivro().getClass());
            Assert.assertEquals(livro.getCodigo(), response.getCodigoLivro());
            Assert.assertEquals(livro.getTitulo(), response.getTitulo());
            Assert.assertEquals(livro.getEditora(), response.getEditora());
            Assert.assertEquals(livro.getAutores(), response.getAutores());
            Assert.assertEquals(livro.getEdicao(), response.getEdicao());
            Assert.assertEquals(livro.getAnoPublicacao(), response.getAnoPublicacao());
        }
    }
}
