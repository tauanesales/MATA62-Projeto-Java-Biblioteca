package SistemaBiblioteca;

import java.text.SimpleDateFormat;
import java.util.Date;

import BancoDeDados.BancoDeDados;
import BancoDeDados.IBancoDeDados;
import Usuarios.Aluno;
import Usuarios.AlunoGraduacao;
import Usuarios.AlunoPosGraduacao;
import Usuarios.Usuario;

public class SistemaBiblioteca {
    public SistemaBiblioteca() {
    }

    public void adicionar(IEntidadeBiblioteca object) {
        BancoDeDados.add(object);
    }

    public void realizarEmprestimo(int codigoUsuario, int codigoLivro) {
        Usuario usuario = encontrarUsuario(codigoUsuario);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        Exemplar exemplar = (Exemplar) BancoDeDados.getFirtById(Exemplar.class, codigoLivro);
        if (exemplar == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        String prefixoSucesso = "Empréstimo realizado com sucesso para " + usuario.getNome() + " - Livro: " + exemplar.getTitulo();
        String prefixoErro = "Não foi possível realizar o empréstimo para " + usuario.getNome() + " - Livro: " + exemplar.getTitulo();

        if (exemplar.isDisponivel() && !usuario.temAtraso()) {
            if (usuario instanceof Aluno) {
                int numEmprestimos = usuario.emprestimosAbertos();
                int maxEmprestimos = 
                    usuario instanceof AlunoPosGraduacao ? 4 
                    : usuario instanceof AlunoGraduacao ? 3
                    : 0;

                if (numEmprestimos >= maxEmprestimos) {
                    System.out.println(prefixoErro + " Usuário não pode ter mais que " + maxEmprestimos + " empréstimos em aberto simultaneamente.");
                    return;
                }
            }

            Date dataSolicitacao = new Date();
            Date dataDevolucao = calcularDataDevolucao();

            Emprestimo emprestimo = new Emprestimo(exemplar, usuario, dataSolicitacao, dataDevolucao);
            BancoDeDados.add(emprestimo);
            exemplar.setDisponivel(false);
            System.out.println(prefixoSucesso + " Devolução até: " + formatarData(dataDevolucao));
        } else {
            System.out.println(prefixoErro +
                    (exemplar.isDisponivel() ? " Livro indisponível." : "") +
                    (usuario.temAtraso() ? " Usuário com atraso em devolução." : ""));
        }
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
        return (Usuario) BancoDeDados.getFirtById(Usuario.class, codigoUsuario);
    }

    private Exemplar encontrarExemplar(int codigoExemplar) {
        return (Exemplar) BancoDeDados.getExemplar(codigoExemplar);
    }
}
