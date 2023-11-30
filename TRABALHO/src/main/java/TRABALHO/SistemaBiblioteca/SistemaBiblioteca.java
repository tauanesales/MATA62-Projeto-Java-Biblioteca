package TRABALHO.SistemaBiblioteca;

import java.text.SimpleDateFormat;
import java.util.Date;

import TRABALHO.BancoDeDados.MyORM;
import TRABALHO.Console.Mensagens;
import TRABALHO.Usuarios.Usuario;

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

        Emprestimo emprestimo = new Emprestimo(exemplar, usuario);
        MyORM.add(emprestimo);

        System.out.println(prefixoSucesso + " Devolução até: " + formatarData(emprestimo.getDataDevolucao()));

    }

    private String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }
}
