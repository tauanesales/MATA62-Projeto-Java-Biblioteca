package SistemaBiblioteca;

import java.util.Date;

import Usuarios.Usuario;

public class Emprestimo implements IEntidadeBiblioteca {
    private Livro livro;
    private Usuario usuario;
    private Date dataSolicitacao;
    private Date dataDevolucao;
    private boolean devolvido;
    private int codigo;

    public Emprestimo(Livro livro, Usuario usuario, Date dataSolicitacao, Date dataDevolucao) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataSolicitacao = dataSolicitacao;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = false;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
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
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "livro=" + livro +
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
