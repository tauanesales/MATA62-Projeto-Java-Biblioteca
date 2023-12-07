package TRABALHO.Reserva;

import java.util.Date;

import TRABALHO.Livros.Livro.Livro;
import TRABALHO.SistemaBiblioteca.IEntidadeBiblioteca;
import TRABALHO.Usuarios.IUsuario;
import TRABALHO.App;

public class Reserva implements IEntidadeBiblioteca {
    private int codigo;
    private IUsuario usuario;
    private Livro livro;
    private boolean ativa;
    private Date dataSolicitacao;

    public Reserva(IUsuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.ativa = true;
        this.dataSolicitacao = new Date();

        livro.incrementarQuantidadeDeReservas();

        if (livro.getQuantidadeDeReservas() > 2) {
            livro.getObservadores().forEach(observador -> observador.notificar());
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public IUsuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
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
