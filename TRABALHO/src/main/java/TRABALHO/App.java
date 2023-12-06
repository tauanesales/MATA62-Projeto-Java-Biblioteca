package TRABALHO;

import java.util.List;
import TRABALHO.BancoDeDados.BancoDeDados;
import TRABALHO.BancoDeDados.IBancoDeDados;
import TRABALHO.Console.Interaction;
import TRABALHO.Livros.Exemplar;
import TRABALHO.Livros.ILivro;
import TRABALHO.Livros.Livro;
import TRABALHO.SistemaBiblioteca.SistemaBiblioteca;
import TRABALHO.Usuarios.AlunoGraduacao;
import TRABALHO.Usuarios.AlunoPosGraduacao;
import TRABALHO.Usuarios.IUsuario;
import TRABALHO.Usuarios.Professor;

public class App {
    public static void main(String[] args) {
        IBancoDeDados db = BancoDeDados.getInstance();
        SistemaBiblioteca biblioteca = SistemaBiblioteca.getInstance(db);

        instanciarDadosEmMemoria(db, biblioteca);
        Interaction.waitRequests(biblioteca);
    }

    public static void instanciarDadosEmMemoria(IBancoDeDados db, SistemaBiblioteca sistemaBiblioteca) {
        List<IUsuario> usuarios = List.of(
                new AlunoGraduacao(123, "João da Silva", db),
                new AlunoPosGraduacao(456, "Luiz Fernando Rodrigues", db),
                new AlunoGraduacao(789, "Pedro Paulo", db),
                new Professor(100, "Carlos Lucena", db));

        List<ILivro> livros = List.of(
                new Livro(100, "Engenharia de Software", "AddisonWesley", "Ian Sommerville", "6ª",
                        "2000"),
                new Livro(101, "UML - Guia do Usuário", "Campus",
                        "Grady Booch, James Rumbaugh, Ivar Jacobson", "7ª",
                        "2000"),
                new Livro(200, "Code Complete", "Microsoft Press", "Steve McConnell", "2ª",
                        "2014"),
                new Livro(201, "Agile Software Development, Principles, Patterns and Practices",
                        "Prentice Hall",
                        "Robert Martin", "1ª", "2002"),
                new Livro(300, "Refactoring: Improving the Design of Existing Code",
                        "Addison-Wesley Professional",
                        "Martin Fowler", "1ª", "1999"),
                new Livro(301, "Software Metrics: A Rigorous and Practical Approach", "CRC Press",
                        "Norman Fenton, James Bieman", "3ª", "2014"),
                new Livro(400, "Design Patterns: Elements of Reusable Object-Oriented Software",
                        "Addison-Wesley Professional",
                        "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "1ª",
                        "1994"),
                new Livro(401,
                        "UML Distilled: A Brief Guide to the Standard Object Modeling Language",
                        "Addison-Wesley Professional", "Martin Fowler", "3ª", "2003"));

        List<Exemplar> exemplares = List.of(
                new Exemplar(1, livros.get(0)),
                new Exemplar(2, livros.get(0)),
                new Exemplar(3, livros.get(1)),
                new Exemplar(4, livros.get(2)),
                new Exemplar(5, livros.get(3)),
                new Exemplar(6, livros.get(4)),
                new Exemplar(7, livros.get(4)),
                new Exemplar(8, livros.get(6)),
                new Exemplar(9, livros.get(6)));

        usuarios.stream().forEach(aluno -> db.insert(aluno));
        livros.stream().forEach(livro -> db.insert(livro));
        exemplares.stream().forEach(exemplar -> db.insert(exemplar));
    }
}
