package Biblioteca;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BancoDeDados.IBancoDeDados;
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

        if (usuario != null && exemplar != null && exemplar.isDisponivel() && !temAtraso(usuario)) {
            Date dataSolicitacao = new Date();
            Date dataDevolucao = calcularDataDevolucao();

            Emprestimo emprestimo = new Emprestimo(exemplar, usuario, dataSolicitacao, dataDevolucao);
            this.bancoDeDados.add(emprestimo);
            exemplar.setDisponivel(false);
            System.out.println("Empréstimo realizado com sucesso para " + usuario.getNome() +
                    " - Livro: " + exemplar.getTitulo() +
                    " - Devolução até: " + formatarData(dataDevolucao));
        } else {
            System.out.println("Não foi possível realizar o empréstimo para " + usuario.getNome() +
                    " - Livro: " + exemplar.getTitulo() +
                    (exemplar.isDisponivel() ? " Livro indisponível." : "") +
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
        List<? extends IEntidadeBiblioteca> entidades = bancoDeDados.getAll(Emprestimo.class);
        for (IEntidadeBiblioteca entidade : entidades) {
            if (entidade instanceof Emprestimo && ((Emprestimo) entidade).getUsuario().equals(usuario)) {
                emprestimosUsuario.add((Emprestimo) entidade);
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
