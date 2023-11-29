package Biblioteca;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BancoDeDados.IBancoDeDados;
import Usuarios.AlunoGraduacao;
import Usuarios.AlunoPosGraduacao;
import Usuarios.Usuario;

public class Biblioteca {
    IBancoDeDados bancoDeDados;

    public Biblioteca(IBancoDeDados bancoDeDados) {
        this.bancoDeDados = bancoDeDados;
    }

    public void adicionar(IEntidadeBiblioteca object) {
        this.bancoDeDados.add(object);
    }

    public void realizarEmprestimo(int codigoUsuario, int codigoLivro) {
        Usuario usuario = encontrarUsuario(codigoUsuario);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        Exemplar exemplar = (Exemplar) bancoDeDados.getFirtById(Exemplar.class, codigoLivro);
        if (exemplar == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        string prefixoSucesso = "Empréstimo realizado com sucesso para " + usuario.getNome() + " - Livro: " + exemplar.getTitulo();
        string prefixoErro = "Não foi possível realizar o empréstimo para " + usuario.getNome() + " - Livro: " + exemplar.getTitulo();

        boolean atrasado = temAtraso(usuario);

        if (exemplar.isDisponivel() && !atrasado) {
            if (usuario instanceof Aluno) {
                int numEmprestimos = contarEmprestimosAbertos(usuario);
                int maxEmprestimos = 
                    usuario instanceof AlunoPosGraduacao ? 4 
                    : usuario instanceof AlunoGraduacao ? 3
                    : 0;

                if (numEmprestimos >= maxEmprestimos) {
                    System.out.println(prefixoErro + " Usuário não pode ter mais que " + maxEmprestimos + " empréstimos em aberto simultaneamente.");
                }
            }

            Date dataSolicitacao = new Date();
            Date dataDevolucao = calcularDataDevolucao();

            Emprestimo emprestimo = new Emprestimo(exemplar, usuario, dataSolicitacao, dataDevolucao);
            this.bancoDeDados.add(emprestimo);
            exemplar.setDisponivel(false);
            System.out.println(prefixoSucesso + " Devolução até: " + formatarData(dataDevolucao));
        } else {
            System.out.println(prefixoErro +
                    (exemplar.isDisponivel() ? " Livro indisponível." : "") +
                    (atrasado ? " Usuário com atraso em devolução." : ""));
        }
    }

    private boolean temAtraso(Usuario usuario) {
        List<Emprestimo> emprestimosUsuario = obterEmprestimosPorUsuario(usuario, true);
        Date dataAtual = new Date();

        for (Emprestimo emprestimo : emprestimosUsuario) {
            if (!emprestimo.isDevolvido() && emprestimo.getDataDevolucao().before(dataAtual)) {
                return true; // Usuário tem pelo menos um empréstimo em atraso
            }
        }

        return false; // Usuário não tem empréstimos em atraso
    }

    private int contarEmprestimosAbertos(Usuario usuario) {
        List<Emprestimo> emprestimosUsuario = obterEmprestimosPorUsuario(usuario, true);
        return emprestimosUsuario.size();
    }

    private List<Emprestimo> obterEmprestimosPorUsuario(Usuario usuario, boolean apenasEmAberto) {
        List<Emprestimo> emprestimosUsuario = new ArrayList<>();
        List<? extends IEntidadeBiblioteca> entidades = bancoDeDados.getAll(Emprestimo.class);
        for (IEntidadeBiblioteca entidade : entidades) {
            if (entidade instanceof Emprestimo) {
                Emprestimo emprestimo = (Emprestimo) entidade;
                if (emprestimo.getUsuario().equals(usuario) && (!apenasEmAberto || !emprestimo.isDevolvido())) {   
                    emprestimosUsuario.add(emprestimo);
                }
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
        return (Usuario) bancoDeDados.getFirtById(Usuario.class, codigoUsuario);
    }

    private Exemplar encontrarExemplar(int codigoExemplar) {
        return (Exemplar) bancoDeDados.getExemplar(codigoExemplar);
    }
}
