package TRABALHO.Reserva;

import java.util.Date;

import TRABALHO.Livros.Livro.ILivroObservavel;
import TRABALHO.SistemaBiblioteca.IEntidadeBiblioteca;
import TRABALHO.Usuarios.IUsuario;
import TRABALHO.App;

public class Reserva implements IEntidadeBiblioteca {
    private int codigo;
    private IUsuario usuario;
    private ILivroObservavel livro;
    private boolean ativa;
    private Date dataSolicitacao;

    public Reserva(IUsuario usuario, ILivroObservavel livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.ativa = true;
        this.dataSolicitacao = new Date();

        livro.incrementarQuantidadeDeReservas();
    }

    public int getCodigo() {
        return codigo;
    }

    public IUsuario getUsuario() {
        return usuario;
    }

    public ILivroObservavel getLivro() {
        return livro;
    }

    public boolean reservaEstaAtiva() {
        return ativa;
    }

    public void removerReserva() {
        this.ativa = false;
        this.livro.decrementarQuantidadeDeReservas();
    }

    public String toString() {
        return "Título: " + livro.getTitulo() + " | " +
                "Data Reserva: " + App.df.format(getDataReserva()) + " | " +
                "Reserva Ativa: " + (reservaEstaAtiva() ? "Sim" : "Não") + " | " +
                "Livro: " + livro.getCodigo() + " | " +
                "Usuário: " + usuario.getCodigo() + " | ";
    }

    private Date getDataReserva() {
        return dataSolicitacao;
    }
}
