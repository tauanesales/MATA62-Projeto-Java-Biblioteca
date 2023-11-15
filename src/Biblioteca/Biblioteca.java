package Biblioteca;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Usuarios.Usuario;

public class Biblioteca {
    private List<Usuario> usuarios;
    private List<Livro> livros;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.usuarios = new ArrayList<Usuario>();
        this.livros = new ArrayList<Livro>();
        this.emprestimos = new ArrayList<Emprestimo>();
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void realizarEmprestimo(int codigoUsuario, int codigoLivro) {
        Usuario usuario = encontrarUsuario(codigoUsuario);
        Livro livro = encontrarLivro(codigoLivro);

        if (usuario != null && livro != null && livro.isDisponivel() && !temAtraso(usuario)) {
            Date dataSolicitacao = new Date();
            Date dataDevolucao = calcularDataDevolucao();

            Emprestimo emprestimo = new Emprestimo(livro, usuario, dataSolicitacao, dataDevolucao);
            emprestimos.add(emprestimo);
            livro.setDisponivel(false);
            System.out.println("Empréstimo realizado com sucesso para " + usuario.getNome() +
                    " - Livro: " + livro.getTitulo() +
                    " - Devolução até: " + formatarData(dataDevolucao));
        } else {
            System.out.println("Não foi possível realizar o empréstimo para " + usuario.getNome() +
                    " - Livro: " + livro.getTitulo() +
                    (livro.isDisponivel() ? " Livro indisponível." : "") +
                    (temAtraso(usuario) ? " Usuário com atraso em devolução." : ""));
        }
    }

    private boolean temAtraso(Usuario usuario) {
        List<Emprestimo> emprestimosUsuario = obterEmprestimosPorUsuario(usuario);
        Date dataAtual = new Date();

        for (Emprestimo emprestimo : emprestimosUsuario) {
            if (!emprestimo.isDevolvido() && emprestimo.getDataDevolucao().before(dataAtual)) {
                return true; // Usuário tem pelo menos um empréstimo em atraso
            }
        }

        return false; // Usuário não tem empréstimos em atraso
    }

    private List<Emprestimo> obterEmprestimosPorUsuario(Usuario usuario) {
        List<Emprestimo> emprestimosUsuario = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().equals(usuario)) {
                emprestimosUsuario.add(emprestimo);
            }
        }
        return emprestimosUsuario;
    }

    private Date calcularDataDevolucao() {
        // Adiciona 7 dias à data atual para obter a data de devolução
        long milissegundosPorDia = 24 * 60 * 60 * 1000;
        Date dataAtual = new Date();
        return new Date(dataAtual.getTime() + 7 * milissegundosPorDia);
    }

    private String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }

    private Usuario encontrarUsuario(int codigoUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCodigo() == codigoUsuario) {
                return usuario;
            }
        }
        return null;
    }

    private Livro encontrarLivro(int codigoLivro) {
        for (Livro livro : livros) {
            if (livro.getCodigo() == codigoLivro) {
                return livro;
            }
        }
        return null;
    }
}