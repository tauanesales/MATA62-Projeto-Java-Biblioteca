package TRABALHO.Reserva;

import TRABALHO.Livros.ILivro;
import TRABALHO.SistemaBiblioteca.IEntidadeBiblioteca;
import TRABALHO.Usuarios.IUsuario;

public class Reserva implements IEntidadeBiblioteca {
    private int codigo;
    private IUsuario usuario;
    private ILivro livro;
    private boolean ativa;

    public Reserva(IUsuario usuario, ILivro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.ativa = true;
    }

    public int getCodigo() {
        return codigo;
    }

    public IUsuario getUsuario() {
        return usuario;
    }

    public ILivro getLivro() {
        return livro;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public String toString() {
        return "Código Usuário: " + usuario.getCodigo() + " | " +
                "Código Livro: " + livro.getCodigo() + " | " +
                "Reserva Ativa: " + (isAtiva() ? "Sim" : "Não");
    }
}
