package TRABALHO.Usuarios;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class UsuarioTest {
    private static Usuario usuario;

    @Before
    public void setUp() {
        usuario = new Usuario(1, "Usuario");
    }

    @Test
    public void maxEmprestimosTest() {
        Assert.assertEquals(0, usuario.maxEmprestimos());
    }

    @Test
    public void tempoDeEmprestimoMaximoTest() {
        Assert.assertEquals(0, usuario.tempoDeEmprestimoMaximo());
    }
}
