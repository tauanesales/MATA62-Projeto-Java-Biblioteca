package TRABALHO;

import TRABALHO.BancoDeDados.BancoDeDados;
import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.Livros.Exemplar.Exemplar;
import TRABALHO.Livros.Livro.Livro;
import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;
import TRABALHO.Usuarios.AlunoGraduacao;
import TRABALHO.Usuarios.AlunoPosGraduacao;
import TRABALHO.Usuarios.IUsuario;
import TRABALHO.Usuarios.Professor;

import java.util.List;

import org.junit.Before;

public class BaseTest {
    protected static SistemaBiblioteca biblioteca;
    protected static IBancoDeDados db;

    protected static AlunoGraduacao alunoGrad;
    protected static AlunoPosGraduacao alunoPosGrad;
    protected static Professor professor;

    protected static Livro livro1;
    protected static Livro livro2;

    protected static Exemplar exemplar1;
    protected static Exemplar exemplar2;

    protected static List<Livro> livros;
    protected static List<Exemplar> exemplares;

    @Before
    public void init() {
        db = BancoDeDados.getInstance();
        db.reset();

        alunoGrad = new AlunoGraduacao(2, "Maria", db);
        alunoPosGrad = new AlunoPosGraduacao(3, "Tay", db);
        professor = new Professor(4, "Ana", db);

        biblioteca = SistemaBiblioteca.getInstance(db);

        livro1 = new Livro(1001, "Teste 1", "Editora 1", "Autor 1", "1ª", "2001");
        livro2 = new Livro(1002, "Teste 2", "Editora 2", "Autor 2", "2ª", "2002");
        exemplar1 = new Exemplar(16, livro1);
        exemplar2 = new Exemplar(17, livro2);

        livros = List.of(
                new Livro(100, "Engenharia de Software", "AddisonWesley",
                        "Ian Sommerville", "6ª", "2000"),
                new Livro(101, "UML - Guia do Usuário", "Campus",
                        "Grady Booch, James Rumbaugh, Ivar Jacobson", "7ª",
                        "2000"),
                new Livro(200, "Code Complete", "Microsoft Press",
                        "Steve McConnell", "2ª", "2014"),
                new Livro(
                        201,
                        "Agile Software Development, Principles, Patterns and Practices",
                        "Prentice Hall", "Robert Martin", "1ª", "2002"),
                new Livro(
                        300, "Refactoring: Improving the Design of Existing Code",
                        "Addison-Wesley Professional", "Martin Fowler", "1ª", "1999"),
                new Livro(301, "Software Metrics: A Rigorous and Practical Approach",
                        "CRC Press", "Norman Fenton, James Bieman", "3ª", "2014"),
                new Livro(
                        400,
                        "Design Patterns: Elements of Reusable Object-Oriented Software",
                        "Addison-Wesley Professional",
                        "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "1ª",
                        "1994"),
                new Livro(
                        401,
                        "UML Distilled: A Brief Guide to the Standard Object Modeling Language",
                        "Addison-Wesley Professional", "Martin Fowler", "3ª", "2003"),
                new Livro(500, "Head First Design Patterns", "O'Reilly Media",
                        "Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra",
                        "1ª", "2004"),
                new Livro(501, "Head First Design Patterns", "O'Reilly Media",
                        "Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra",
                        "1ª", "2004"),
                new Livro(600, "Agile Principles, Patterns, and Practices in C#",
                        "Prentice Hall", "Robert Martin", "1ª", "2006"));

        exemplares = List.of(
                new Exemplar(1, livros.get(0)),
                new Exemplar(2, livros.get(0)),
                new Exemplar(15, livros.get(0)),
                new Exemplar(3, livros.get(1)),
                new Exemplar(4, livros.get(2)),
                new Exemplar(5, livros.get(3)),
                new Exemplar(6, livros.get(4)),
                new Exemplar(7, livros.get(4)),
                new Exemplar(9, livros.get(6)),
                new Exemplar(10, livros.get(6)),
                new Exemplar(12, livros.get(8)),
                new Exemplar(13, livros.get(9)),
                new Exemplar(14, livros.get(10)));
        
        List<IUsuario> alunos = List.of(new AlunoGraduacao(123, "João da Silva", db),
                new AlunoPosGraduacao(456, "Luiz Fernando Rodrigues", db),
                new AlunoGraduacao(789, "Pedro Paulo", db));
        Professor professor2 = new Professor(100, "Carlos Lucena", db);

        List.of(alunoGrad, alunoPosGrad, professor, professor2, livro1, livro2, exemplar1, exemplar2)
                .stream()
                .forEach(dado -> db.insert(dado));

        livros.stream().forEach(livro -> db.insert(livro));
        alunos.stream().forEach(aluno -> db.insert(aluno));
        exemplares.stream().forEach(exemplar -> db.insert(exemplar));
    }
}
