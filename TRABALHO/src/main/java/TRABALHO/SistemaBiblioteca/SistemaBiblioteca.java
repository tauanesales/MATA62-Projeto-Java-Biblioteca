package TRABALHO.SistemaBiblioteca;

import TRABALHO.BancoDeDados.MyORM;
import TRABALHO.Console.Mensagens;
import TRABALHO.Emprestimo.Emprestimo;
import TRABALHO.Livros.Exemplar;
import TRABALHO.Usuarios.Usuario;
import java.text.SimpleDateFormat;
import java.util.Date;
import TRABALHO.SistemaBiblioteca.ValidacoesSistemaBiblioteca.ValidacaoEmprestimo;

public class SistemaBiblioteca {
    public void realizarEmprestimo(int codigoUsuario, int codigoLivro) {
        Usuario usuario = MyORM.getUsuario(codigoUsuario);
        Exemplar exemplar = MyORM.getExemplarDisponivelPorCodigoLivro(codigoLivro);

        try {
            ValidacaoEmprestimo.validacaoPodeEmprestarExemplarParaUsuario(
                    codigoUsuario, codigoLivro, usuario, exemplar);
        } catch (ValidacaoEmprestimo.EmprestimoException e) {
            Mensagens.MensagemDeErroEmprestimo(e.getMessage(), usuario, exemplar);
            return;
        }

        Emprestimo emprestimo = new Emprestimo(exemplar, usuario);
        MyORM.add(emprestimo);

        String prefixoSucesso = "Empréstimo realizado com sucesso para " +
                usuario.getNome() +
                " - Livro: " + exemplar.getTitulo();
        System.out.println(prefixoSucesso + " Devolução até: " +
                formatarData(emprestimo.getDataDevolucao()));
    }

    private String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }
}
