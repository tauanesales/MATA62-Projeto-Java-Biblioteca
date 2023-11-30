package SistemaBiblioteca;

import java.text.SimpleDateFormat;
import java.util.Date;

import BancoDeDados.MyORM;
import Console.Mensagens;
import Usuarios.Usuario;

public class SistemaBiblioteca {
    public void realizarEmprestimo(int codigoUsuario, int codigoLivro) {
        Usuario usuario = MyORM.getUsuario(codigoUsuario);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        if (!MyORM.livroExiste(codigoLivro)) {
            Mensagens.MensagemDeErroEmprestimo("Livro não encontrado.", usuario, null);
            return;
        }

        Exemplar exemplar = MyORM.getExemplarDisponivelPorCodigoLivro(codigoLivro);
        if (exemplar == null) {
            Mensagens.MensagemDeErroEmprestimo("Não há exemplares disponíveis desse livro.", usuario,
                    MyORM.getLivro(codigoLivro));
            return;
        }

        String prefixoSucesso = "Empréstimo realizado com sucesso para " + usuario.getNome() + " - Livro: "
                + exemplar.getTitulo();

        if (usuario.temAtraso()) {
            Mensagens.MensagemDeErroEmprestimo("Usuário com atraso.", usuario, exemplar);
            return;
        }

        if (usuario.atingiuLimiteMaximoDeEmprestimos()) {
            Mensagens.MensagemDeErroEmprestimo("Usuário não pode ter mais que " + usuario.maxEmprestimos()
                    + " empréstimos em aberto simultaneamente.", usuario, exemplar);
            return;
        }

        Date dataSolicitacao = new Date();
        Date dataDevolucao = calcularDataDevolucao();

        Emprestimo emprestimo = new Emprestimo(exemplar, usuario, dataSolicitacao, dataDevolucao);
        MyORM.add(emprestimo);
        exemplar.setDisponivel(false);
        System.out.println(prefixoSucesso + " Devolução até: " + formatarData(dataDevolucao));

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
}
