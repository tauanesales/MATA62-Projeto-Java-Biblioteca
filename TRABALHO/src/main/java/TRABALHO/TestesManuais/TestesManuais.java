package TRABALHO.TestesManuais;

import java.util.List;

import TRABALHO.BancoDeDados.BancoDeDados;
import TRABALHO.Livros.Exemplar;
import TRABALHO.Livros.Livro;
import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;
import TRABALHO.Usuarios.Aluno;
import TRABALHO.Usuarios.AlunoGraduacao;
import TRABALHO.Usuarios.AlunoPosGraduacao;
import TRABALHO.Usuarios.Professor;

public class TestesManuais {
        public static void TesteBasico() {
                SistemaBiblioteca biblioteca = new SistemaBiblioteca();

                Aluno aluno = new Aluno(1, "João");
                AlunoGraduacao alunoGrad = new AlunoGraduacao(2, "Maria");
                AlunoPosGraduacao alunoPosGrad = new AlunoPosGraduacao(3, "Tay");
                Professor professor = new Professor(4, "Ana");

                Exemplar livro1 = new Exemplar(1001, 1, "Teste 1", "Editora 1", "Autor 1", "1ª", "2001");
                Exemplar livro2 = new Exemplar(1002, 2, "Teste 2", "Editora 2", "Autor 2", "2ª", "2002");

                List.of(aluno, alunoGrad, alunoPosGrad, professor, livro1, livro2)
                                .stream()
                                .forEach(dado -> BancoDeDados.add(dado));

                List<Livro> livros = List.of(
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
                livros.stream().forEach(livro -> BancoDeDados.add(livro));

                biblioteca.realizarEmprestimo(0, 1001);
                biblioteca.realizarEmprestimo(1, 1);
                biblioteca.realizarEmprestimo(1, 1001);
                biblioteca.realizarEmprestimo(2, 1001);
                biblioteca.realizarEmprestimo(2, 1001);
                biblioteca.realizarEmprestimo(3, 1001);
                biblioteca.realizarEmprestimo(3, 1002);
                biblioteca.realizarEmprestimo(4, 1002);
                biblioteca.realizarEmprestimo(4, 101);

                biblioteca.realizarEmprestimo(3, 100);
                biblioteca.realizarEmprestimo(3, 100);
                biblioteca.realizarEmprestimo(3, 100);
                biblioteca.realizarEmprestimo(3, 300);
                biblioteca.realizarEmprestimo(3, 300);
                biblioteca.realizarEmprestimo(3, 300);
                biblioteca.realizarEmprestimo(3, 301);
                biblioteca.realizarEmprestimo(3, 400);
                biblioteca.realizarEmprestimo(3, 400);
        }

        public static void TesteDoPDF() {
                // SistemaBiblioteca biblioteca = new SistemaBiblioteca();

                List<Aluno> alunos = List.of(
                                new AlunoGraduacao(123, "João da Silva"),
                                new AlunoPosGraduacao(456, "Luiz Fernando Rodrigues"),
                                new AlunoGraduacao(789, "Pedro Paulo"));
                Professor professor = new Professor(100, "Carlos Lucena");

                List<Livro> livros = List.of(
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

                alunos.stream().forEach(aluno -> BancoDeDados.add(aluno));
                BancoDeDados.add(professor);
                livros.stream().forEach(livro -> BancoDeDados.add(livro));

                // biblioteca.realizarEmprestimo(1, 101);
                // biblioteca.realizarEmprestimo(2, 101);
                // biblioteca.realizarEmprestimo(3, 102);
                // biblioteca.realizarEmprestimo(4, 102);

                // biblioteca.realizarDevolucao(1, 101);
                // biblioteca.realizarDevolucao(2, 101);
                // biblioteca.realizarDevolucao(3, 102);
                // biblioteca.realizarDevolucao(4, 102);
        }
}
