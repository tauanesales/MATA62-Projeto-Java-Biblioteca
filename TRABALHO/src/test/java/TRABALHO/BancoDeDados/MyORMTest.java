package TRABALHO.BancoDeDados;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;
import TRABALHO.SistemaBiblioteca.Exemplar;
import TRABALHO.SistemaBiblioteca.Livro;
import TRABALHO.Usuarios.Usuario;

public class MyORMTest extends BaseTest {
    @Test
    public void getUsuarioTest() {
        Assert.assertEquals(null, MyORM.getUsuario(-1));

        for (Usuario usuario : List.of(aluno, alunoGrad, alunoPosGrad, professor)) {
            Usuario response = MyORM.getUsuario(usuario.getCodigo());
            Assert.assertEquals(usuario.getClass(), response.getClass());
            Assert.assertEquals(usuario.getCodigo(), response.getCodigo());
            Assert.assertEquals(usuario.getNome(), response.getNome());
        }
    }

    @Test
    public void getExemplarTest() {
        Assert.assertEquals(null, MyORM.getExemplar(0, 0));

        List<Exemplar> exemplares = livros.stream()
                .filter(livro -> livro instanceof Exemplar)
                .map(exemplar -> (Exemplar) exemplar)
                .collect(Collectors.toList());

        for (Exemplar exemplar : exemplares) {
            Exemplar response = MyORM.getExemplar(exemplar.getCodigoExemplar(), exemplar.getCodigo());
            Assert.assertEquals(exemplar.getClass(), response.getClass());
            Assert.assertEquals(exemplar.getCodigoExemplar(), response.getCodigoExemplar());
            Assert.assertEquals(exemplar.getCodigo(), response.getCodigo());
            Assert.assertEquals(exemplar.getTitulo(), response.getTitulo());
            Assert.assertEquals(exemplar.getEditora(), response.getEditora());
            Assert.assertEquals(exemplar.getAutores(), response.getAutores());
            Assert.assertEquals(exemplar.getEdicao(), response.getEdicao());
            Assert.assertEquals(exemplar.getAnoPublicacao(), response.getAnoPublicacao());
        }
    }

    @Test
    public void livroExisteTest() {
        Assert.assertEquals(false, MyORM.livroExiste(0));

        List<Exemplar> exemplares = livros.stream()
                .filter(livro -> livro instanceof Exemplar)
                .map(exemplar -> (Exemplar) exemplar)
                .collect(Collectors.toList());

        for (Exemplar exemplar : exemplares) {
            Assert.assertEquals(true, MyORM.livroExiste(exemplar.getCodigo()));
        }
    }

    @Test
    public void getExemplarDisponivelPorCodigoLivroTest() {
        Assert.assertEquals(null, MyORM.getExemplarDisponivelPorCodigoLivro(0));

        for (Livro livro : livros) {
            Exemplar response = MyORM.getExemplarDisponivelPorCodigoLivro(livro.getCodigo());
            if (response == null) {
                Assert.assertEquals(Livro.class, livro.getClass());
                continue;
            }
            Assert.assertEquals(livro.getClass(), response.getClass());
            Assert.assertEquals(livro.getCodigo(), response.getCodigo());
            Assert.assertEquals(livro.getTitulo(), response.getTitulo());
            Assert.assertEquals(livro.getEditora(), response.getEditora());
            Assert.assertEquals(livro.getAutores(), response.getAutores());
            Assert.assertEquals(livro.getEdicao(), response.getEdicao());
            Assert.assertEquals(livro.getAnoPublicacao(), response.getAnoPublicacao());
        }
    }
}