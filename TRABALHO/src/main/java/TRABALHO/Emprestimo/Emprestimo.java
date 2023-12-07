package TRABALHO.Emprestimo;

import java.util.Date;

import TRABALHO.App;
import TRABALHO.Livros.Exemplar.Exemplar;
import TRABALHO.SistemaBiblioteca.IEntidadeBiblioteca;
import TRABALHO.Usuarios.IUsuario;

public class Emprestimo implements IEntidadeBiblioteca {
    private Exemplar exemplar;
    private IUsuario usuario;
    private Date dataSolicitacao;
    private Date dataDevolucao;
    private boolean devolvido;
    private int codigo;

    public Emprestimo(Exemplar exemplar, IUsuario usuario) {
        this(exemplar, usuario, new Date());
    }

    public Emprestimo(Exemplar exemplar, IUsuario usuario, Date dataSolicitacao) {
        this.exemplar = exemplar;
        this.usuario = usuario;
        this.devolvido = false;

        this.dataSolicitacao = dataSolicitacao != null ? dataSolicitacao : new Date();
        this.dataDevolucao = this.calcularDataDevolucao();
    }

    private Date calcularDataDevolucao() {
        return new Date(this.dataSolicitacao.getTime() + usuario.tempoDeEmprestimoMaximo());
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public IUsuario getUsuario() {
        return usuario;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void devolver() {
        this.devolvido = true;
        dataDevolucao = new Date();
    }

    public String toString() {
        return "Título: " + exemplar.getTitulo() + " | " +
                "Solicitação: " + App.df.format(getDataSolicitacao()) + " | " +
                "Status do empréstimo: " + (isDevolvido() ? "Finalizado" : "Em curso"   ) + " | " +
                "Devolução: " + App.df.format(getDataDevolucao()) + " | " +
                "Livro: " + exemplar.getCodigoLivro() + " | " +
                "Exemplar: " + exemplar.getCodigo() + " | " +
                "Usuário: " + usuario.getCodigo();
    }

    public int getCodigo() {
        return codigo;
    }
}
