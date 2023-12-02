package TRABALHO;

import TRABALHO.BancoDeDados.MyORM;
import TRABALHO.SistemaBiblioteca.Exemplar;
import TRABALHO.SistemaBiblioteca.Livro;
import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;
import TRABALHO.Usuarios.Aluno;
import TRABALHO.Usuarios.AlunoGraduacao;
import TRABALHO.Usuarios.AlunoPosGraduacao;
import TRABALHO.Usuarios.Professor;
import TRABALHO.Usuarios.Usuario;

import java.util.List;

import org.junit.Before;

public class BaseTest {
    protected static SistemaBiblioteca biblioteca;

    protected static Usuario usuario;
    protected static Aluno aluno;
    protected static AlunoGraduacao alunoGrad;
    protected static AlunoPosGraduacao alunoPosGrad;
    protected static Professor professor;

    protected static Exemplar exemplar1;
    protected static Exemplar exemplar2;

    protected static List<Livro> livros;

    @Before
    public void init() {
        MyORM.init();
        usuario = new Usuario(0, "Usuário");
        biblioteca = new SistemaBiblioteca();
        aluno = new Aluno(1, "João");
        alunoGrad = new AlunoGraduacao(2, "Maria");
        alunoPosGrad = new AlunoPosGraduacao(3, "Tay");
        professor = new Professor(4, "Ana");

        exemplar1 = new Exemplar(1001, 1, "Teste 1", "Editora 1", "Autor 1", "1ª", "2001");
        exemplar2 = new Exemplar(1002, 2, "Teste 2", "Editora 2", "Autor 2", "2ª", "2002");

        livros = List.of(
                new Exemplar(100, 1, "Engenharia de Software", "AddisonWesley",
                        "Ian Sommerville", "6ª", "2000"),
                new Exemplar(100, 2, "Engenharia de Software", "AddisonWesley",
                        "Ian Sommerville", "6ª", "2000"),
                new Exemplar(100, 3, "Engenharia de Software", "AddisonWesley",
                        "Ian Sommerville", "6ª", "2000"),
                new Exemplar(101, 3, "UML - Guia do Usuário", "Campus",
                        "Grady Booch, James Rumbaugh, Ivar Jacobson", "7ª",
                        "2000"),
                new Exemplar(200, 4, "Code Complete", "Microsoft Press",
                        "Steve McConnell", "2ª", "2014"),
                new Exemplar(
                        201, 5,
                        "Agile Software Development, Principles, Patterns and Practices",
                        "Prentice Hall", "Robert Martin", "1ª", "2002"),
                new Exemplar(
                        300, 6, "Refactoring: Improving the Design of Existing Code",
                        "Addison-Wesley Professional", "Martin Fowler", "1ª", "1999"),
                new Exemplar(
                        300, 7, "Refactoring: Improving the Design of Existing Code",
                        "Addison-Wesley Professional", "Martin Fowler", "1ª", "1999"),
                new Livro(301, "Software Metrics: A Rigorous and Practical Approach",
                        "CRC Press", "Norman Fenton, James Bieman", "3ª", "2014"),
                new Exemplar(
                        400, 8,
                        "Design Patterns: Elements of Reusable Object-Oriented Software",
                        "Addison-Wesley Professional",
                        "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "1ª",
                        "1994"),
                new Exemplar(
                        400, 9,
                        "Design Patterns: Elements of Reusable Object-Oriented Software",
                        "Addison-Wesley Professional",
                        "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "1ª",
                        "1994"),
                new Livro(
                        401,
                        "UML Distilled: A Brief Guide to the Standard Object Modeling Language",
                        "Addison-Wesley Professional", "Martin Fowler", "3ª", "2003"),
                new Exemplar(500, 10, "Head First Design Patterns", "O'Reilly Media",
                        "Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra",
                        "1ª", "2004"),
                new Exemplar(501, 10, "Head First Design Patterns", "O'Reilly Media",
                        "Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra",
                        "1ª", "2004"),
                new Exemplar(600, 11, "Agile Principles, Patterns, and Practices in C#",
                        "Prentice Hall", "Robert Martin", "1ª", "2006"));

        List<Aluno> alunos = List.of(new AlunoGraduacao(123, "João da Silva"),
                new AlunoPosGraduacao(456, "Luiz Fernando Rodrigues"),
                new AlunoGraduacao(789, "Pedro Paulo"));
        Professor professor2 = new Professor(100, "Carlos Lucena");

        List.of(usuario, aluno, alunoGrad, alunoPosGrad, professor, professor2, exemplar1, exemplar2)
                .stream()
                .forEach(dado -> MyORM.add(dado));

        livros.stream().forEach(livro -> MyORM.add(livro));
        alunos.stream().forEach(aluno -> MyORM.add(aluno));
    }
}