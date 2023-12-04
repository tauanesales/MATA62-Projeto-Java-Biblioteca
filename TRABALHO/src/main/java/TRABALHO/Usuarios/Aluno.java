package TRABALHO.Usuarios;

import TRABALHO.BancoDeDados.IBancoDeDados;

public class Aluno extends Usuario {
    public Aluno(int codigo_identificador, String nome, IBancoDeDados db) {
        super(codigo_identificador, nome, db);
    }
}
