package TRABALHO.Reserva;

import TRABALHO.Livros.Livro;
import TRABALHO.SistemaBiblioteca.IEntidadeBiblioteca;
import TRABALHO.Usuarios.IUsuario;

public class Reserva implements IEntidadeBiblioteca {
    private int codigo;
    private IUsuario usuario;
    private Livro livro;
    private boolean ativa;

    public Reserva(IUsuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.ativa = true;

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
        return "Código Usuário: " + usuario.getCodigo() + " | " +
                "Código Livro: " + livro.getCodigo() + " | " +
                "Reserva Ativa: " + (reservaEstaAtiva() ? "Sim" : "Não");
    }
}
