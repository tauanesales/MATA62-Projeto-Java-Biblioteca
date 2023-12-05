package TRABALHO.Emprestimo;

import java.util.Date;

import TRABALHO.Livros.Exemplar;
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

        exemplar.setDisponivel(this.devolvido);
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

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
        exemplar.setDisponivel(devolvido);
    }

    public String toString() {
        return "Exemplar: " + exemplar.getTitulo() + " | " +
                "Código Livro: " + exemplar.getCodigo() + " | " +
                "Código Exemplar: " + exemplar.getCodigoExemplar() + " | " +
                "Usuario: " + usuario.getNome() + " | " +
                "DataSolicitacao: " + dataSolicitacao + " | " +
                "DataDevolucao: " + dataDevolucao + " | " +
                "Devolvido: " + devolvido;
    }

    public int getCodigo() {
        return codigo;
    }
}
