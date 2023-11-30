package Usuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BancoDeDados.MyORM;
import SistemaBiblioteca.Emprestimo;
import SistemaBiblioteca.IEntidadeBiblioteca;

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

    public boolean temAtraso() {
        List<Emprestimo> emprestimosUsuario = this.obterEmprestimos(true);
        Date dataAtual = new Date();

        for (Emprestimo emprestimo : emprestimosUsuario) {
            if (!emprestimo.isDevolvido() && emprestimo.getDataDevolucao().before(dataAtual)) {
                return true; // Usuário tem pelo menos um empréstimo em atraso
            }
        }

        return false; // Usuário não tem empréstimos em atraso
    }

    public List<Emprestimo> obterEmprestimos(boolean apenasEmAberto) {
        List<Emprestimo> emprestimosUsuario = new ArrayList<>();
        List<? extends IEntidadeBiblioteca> entidades = MyORM.getAll(Emprestimo.class);
        for (IEntidadeBiblioteca entidade : entidades) {
            if (entidade instanceof Emprestimo) {
                Emprestimo emprestimo = (Emprestimo) entidade;
                if (emprestimo.getUsuario().equals(this) && (!apenasEmAberto || !emprestimo.isDevolvido())) {
                    emprestimosUsuario.add(emprestimo);
                }
            }
        }
        return emprestimosUsuario;
    }

    public int emprestimosAbertos() {
        return this.obterEmprestimos(true).size();
    }

    public int maxEmprestimos() {
        return 0;
    }

    public boolean atingiuLimiteMaximoDeEmprestimos() {
        return this.emprestimosAbertos() >= this.maxEmprestimos();
    }

}
