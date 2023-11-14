package TrabalhoEngSoft;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        Aluno aluno = new Aluno(1, "João");
        AlunoGraduacao alunoGrad = new AlunoGraduacao(2, "Maria");
        AlunoPosGraduacao alunoPosGrad = new AlunoPosGraduacao(3, "Carlos");
        Professor professor = new Professor(4, "Ana");

        Livro livro1 = new Livro(101, "Teste 1");
        Livro livro2 = new Livro(102, "Teste 2");

        biblioteca.adicionarUsuario(aluno);
        biblioteca.adicionarUsuario(alunoGrad);
        biblioteca.adicionarUsuario(alunoPosGrad);
        biblioteca.adicionarUsuario(professor);

        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);

        biblioteca.realizarEmprestimo(1, 101);
        biblioteca.realizarEmprestimo(2, 101);
        biblioteca.realizarEmprestimo(3, 102);
        biblioteca.realizarEmprestimo(4, 102);
    }
}