package TRABALHO.BancoDeDados;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import TRABALHO.BaseTest;
import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.Livros.Exemplar.Exemplar;
import TRABALHO.Livros.Livro.ILivro;
import TRABALHO.Livros.Livro.ILivroObservavel;
import TRABALHO.Livros.Livro.Livro;
import TRABALHO.SistemaBiblioteca.IEntidadeBiblioteca;
import TRABALHO.Usuarios.AlunoGraduacao;
import TRABALHO.Usuarios.AlunoPosGraduacao;
import TRABALHO.Usuarios.IUsuario;
import TRABALHO.Usuarios.Professor;

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

        for (ILivroObservavel livro : livros) {
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

    @Test
    public void assertSingletonDBTest() {
        Assert.assertEquals(db, BancoDeDados.getInstance());
        Assert.assertEquals(BancoDeDados.getInstance(), BancoDeDados.getInstance());
        Assert.assertEquals(BancoDeDados.getInstance(), db);
    }

    @Test
    public void assertSingletonAfterResetDBTest() {
        db.reset();
        Assert.assertEquals(db, BancoDeDados.getInstance());
        Assert.assertEquals(BancoDeDados.getInstance(), BancoDeDados.getInstance());
        Assert.assertEquals(BancoDeDados.getInstance(), db);
    }

    @Test
    public void assertResetClearsTheDBTest() {
        Assert.assertTrue(db.getAll(IEntidadeBiblioteca.class).size() > 0);
        db.reset();
        Assert.assertEquals(0, db.getAll(IEntidadeBiblioteca.class).size());
    }

    @Test
    public void assertResetClearsTheDB2Test() {
        Assert.assertTrue(db.getAll(IEntidadeBiblioteca.class).size() > 0);
        BancoDeDados.getInstance().reset();
        Assert.assertEquals(0, db.getAll(IEntidadeBiblioteca.class).size());
    }

    @Test
    public void insertAndGetFromDBWorksTest() {
        db.reset();
        Assert.assertEquals(0, db.getAll(IEntidadeBiblioteca.class).size());

        db.insert(livro1);
        Assert.assertEquals(1, db.getAll(IEntidadeBiblioteca.class).size());
        Assert.assertEquals(livro1, db.getAll(IEntidadeBiblioteca.class).get(0));

        db.insert(livro2);
        Assert.assertEquals(2, db.getAll(IEntidadeBiblioteca.class).size());
        Assert.assertEquals(livro1, db.getAll(IEntidadeBiblioteca.class).get(0));
        Assert.assertEquals(livro2, db.getAll(IEntidadeBiblioteca.class).get(1));

        db.insert(alunoGrad);
        Assert.assertEquals(3, db.getAll(IEntidadeBiblioteca.class).size());
        Assert.assertEquals(livro1, db.getAll(IEntidadeBiblioteca.class).stream()
                .filter(livro -> livro.getCodigo() == livro1.getCodigo()).findFirst().get());
        Assert.assertEquals(livro2, db.getAll(IEntidadeBiblioteca.class).stream()
                .filter(livro -> livro.getCodigo() == livro2.getCodigo()).findFirst().get());
        Assert.assertEquals(alunoGrad, db.getAll(IEntidadeBiblioteca.class).stream()
                .filter(aluno -> aluno.getCodigo() == alunoGrad.getCodigo()).findFirst().get());

        Assert.assertEquals(0, db.getAll(Exemplar.class).size());
        Assert.assertEquals(2, db.getAll(Livro.class).size());
        Assert.assertEquals(1, db.getAll(IUsuario.class).size());

        Assert.assertEquals(livro1, db.getFirstById(ILivroObservavel.class, livro1.getCodigo()));
        Assert.assertEquals(livro2, db.getFirstById(ILivroObservavel.class, livro2.getCodigo()));

        Assert.assertEquals(livro1, db.getFirstById(ILivro.class, livro1.getCodigo()));
        Assert.assertEquals(livro2, db.getFirstById(ILivro.class, livro2.getCodigo()));

        Assert.assertEquals(livro1, db.getFirstById(IEntidadeBiblioteca.class, livro1.getCodigo()));
        Assert.assertEquals(livro2, db.getFirstById(IEntidadeBiblioteca.class, livro2.getCodigo()));

        Assert.assertNull(db.getFirstById(IUsuario.class, livro1.getCodigo()));
        Assert.assertNull(db.getFirstById(IUsuario.class, livro2.getCodigo()));

        Assert.assertEquals(alunoGrad, db.getFirstById(AlunoGraduacao.class, alunoGrad.getCodigo()));
        Assert.assertEquals(alunoGrad, db.getFirstById(IUsuario.class, alunoGrad.getCodigo()));
        Assert.assertEquals(alunoGrad, db.getFirstById(IEntidadeBiblioteca.class, alunoGrad.getCodigo()));
        Assert.assertNull(db.getFirstById(AlunoPosGraduacao.class, alunoGrad.getCodigo()));
        Assert.assertNull(db.getFirstById(Professor.class, alunoGrad.getCodigo()));

        Assert.assertEquals(livro1, db.getLivro(livro1.getCodigo()));
        Assert.assertEquals(livro2, db.getLivro(livro2.getCodigo()));

        Assert.assertArrayEquals(
                List.of(livro1, livro2).toArray(),
                db.getAll(ILivroObservavel.class).toArray());
    }

    @Test
    public void assertEmprestimosCriadosTest() {
        biblioteca.executeCommand("emp", String.valueOf(alunoGrad.getCodigo()),
                String.valueOf(exemplar1.getCodigoLivro()));
        biblioteca.executeCommand("emp", String.valueOf(alunoGrad.getCodigo()),
                String.valueOf(exemplar2.getCodigoLivro()));
        biblioteca.executeCommand("emp", String.valueOf(alunoGrad.getCodigo()), "200");

        Assert.assertEquals(3, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertEquals(alunoGrad.maxEmprestimos(), alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertTrue(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        Assert.assertEquals(db.getEmprestimos(true, alunoGrad.getCodigo()).size(),
                alunoGrad.quantidadeDeEmprestimosEmAberto());

        biblioteca.executeCommand("dev", String.valueOf(alunoGrad.getCodigo()),
                String.valueOf(exemplar1.getCodigoLivro()));
        Assert.assertEquals(2, alunoGrad.quantidadeDeEmprestimosEmAberto());
        Assert.assertFalse(alunoGrad.atingiuLimiteMaximoDeEmprestimos());

        Assert.assertEquals(db.getEmprestimos(true, alunoGrad.getCodigo()).size(),
                alunoGrad.quantidadeDeEmprestimosEmAberto());

        db.getAll(Emprestimo.class).forEach(emprestimo -> {
            Assert.assertEquals(alunoGrad, emprestimo.getUsuario());
        });

        Assert.assertEquals(3, db.getAll(Emprestimo.class).size());
    }
}
