package TRABALHO;

import java.util.Locale;

import TRABALHO.TestesManuais.TestesManuais;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        Locale.setDefault(new Locale("pt", "BR"));
        System.out.println("*********************INICIANDO TESTES*********************");
        TestesManuais.TesteBasico2();
        System.out.println("********************TESTES FINALIZADOS********************");
    }
}
