package TRABALHO.SistemaBiblioteca;

import java.util.Date;

import TRABALHO.Usuarios.IUsuario;

public class Emprestimo implements IEntidadeBiblioteca {
    private Exemplar exemplar;
    private IUsuario usuario;
    private Date dataSolicitacao;
    private Date dataDevolucao;
    private boolean devolvido;
    private int codigo;

    public Emprestimo(Exemplar exemplar, IUsuario usuario) {
        this.exemplar = exemplar;
        this.usuario = usuario;
        this.devolvido = false;

        this.dataSolicitacao = new Date();
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

    @Override
    public String toString() {
        return "Emprestimo{" +
                "exemplar=" + exemplar +
                ", usuario=" + usuario +
                ", dataSolicitacao=" + dataSolicitacao +
                ", dataDevolucao=" + dataDevolucao +
                ", devolvido=" + devolvido +
                '}';
    }

    public int getCodigo() {
        return codigo;
    }
}
