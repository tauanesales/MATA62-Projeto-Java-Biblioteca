package TRABALHO.BancoDeDados;

import static junit.framework.Assert.assertTrue;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import TRABALHO.BancoDeDados.MyORM;
import TRABALHO.SistemaBiblioteca.Exemplar;
import TRABALHO.SistemaBiblioteca.Livro;
import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;
import TRABALHO.Usuarios.Aluno;
import TRABALHO.Usuarios.AlunoGraduacao;
import TRABALHO.Usuarios.AlunoPosGraduacao;
import TRABALHO.Usuarios.Professor;
import TRABALHO.Usuarios.Usuario;

public class MyORMTest {
        private static SistemaBiblioteca biblioteca;

        private static Aluno aluno;
        private static AlunoGraduacao alunoGrad;
        private static AlunoPosGraduacao alunoPosGrad;
        private static Professor professor;

        private static Exemplar livro1;
        private static Exemplar livro2;

        private static List<Livro> livros;

        @Before
        public void init() {
                biblioteca = new SistemaBiblioteca();
                aluno = new Aluno(1, "João");
                alunoGrad = new AlunoGraduacao(2, "Maria");
                alunoPosGrad = new AlunoPosGraduacao(3, "Tay");
                professor = new Professor(4, "Ana");

                livro1 = new Exemplar(1001, 1, "Teste 1", "Editora 1", "Autor 1", "1ª", "2001");
                livro2 = new Exemplar(1002, 2, "Teste 2", "Editora 2", "Autor 2", "2ª", "2002");

                List.of(aluno, alunoGrad, alunoPosGrad, professor, livro1, livro2)
                                .stream()
                                .forEach(dado -> MyORM.add(dado));

                List<Aluno> alunos = List.of(
                                new AlunoGraduacao(123, "João da Silva"),
                                new AlunoPosGraduacao(456, "Luiz Fernando Rodrigues"),
                                new AlunoGraduacao(789, "Pedro Paulo"));
                Professor professor = new Professor(100, "Carlos Lucena");
                livros = List.of(
                                new Exemplar(100, 1, "Engenharia de Software", "AddisonWesley", "Ian Sommerville", "6ª",
                                                "2000"),
                                new Exemplar(100, 2, "Engenharia de Software", "AddisonWesley", "Ian Sommerville", "6ª",
                                                "2000"),
                                new Exemplar(101, 3, "UML - Guia do Usuário", "Campus",
                                                "Grady Booch, James Rumbaugh, Ivar Jacobson", "7ª",
                                                "2000"),
                                new Exemplar(200, 4, "Code Complete", "Microsoft Press", "Steve McConnell", "2ª",
                                                "2014"),
                                new Exemplar(201, 5, "Agile Software Development, Principles, Patterns and Practices",
                                                "Prentice Hall",
                                                "Robert Martin", "1ª", "2002"),
                                new Exemplar(300, 6, "Refactoring: Improving the Design of Existing Code",
                                                "Addison-Wesley Professional",
                                                "Martin Fowler", "1ª", "1999"),
                                new Exemplar(300, 7, "Refactoring: Improving the Design of Existing Code",
                                                "Addison-Wesley Professional",
                                                "Martin Fowler", "1ª", "1999"),
                                new Livro(301, "Software Metrics: A Rigorous and Practical Approach", "CRC Press",
                                                "Norman Fenton, James Bieman", "3ª", "2014"),
                                new Exemplar(400, 8, "Design Patterns: Elements of Reusable Object-Oriented Software",
                                                "Addison-Wesley Professional",
                                                "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "1ª",
                                                "1994"),
                                new Exemplar(400, 9, "Design Patterns: Elements of Reusable Object-Oriented Software",
                                                "Addison-Wesley Professional",
                                                "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "1ª",
                                                "1994"),
                                new Livro(401,
                                                "UML Distilled: A Brief Guide to the Standard Object Modeling Language",
                                                "Addison-Wesley Professional", "Martin Fowler", "3ª", "2003"));

                List.of(aluno, alunoGrad, alunoPosGrad, professor, livro1, livro2)
                                .stream()
                                .forEach(dado -> MyORM.add(dado));

                livros.stream().forEach(livro -> MyORM.add(livro));
        }

        @Test
        public void getUsuarioTest() {
                Assert.assertEquals(null, MyORM.getUsuario(0));

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
