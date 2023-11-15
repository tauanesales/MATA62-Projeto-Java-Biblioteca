package Usuarios;

public class Usuario implements IUsuario {
    private int codigo_identificador;
    private String nome;

    public Usuario(int codigo_identificador, String nome) {
        this.codigo_identificador = codigo_identificador;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo_identificador;
    }

    public String getNome() {
        return nome;
    }
}
