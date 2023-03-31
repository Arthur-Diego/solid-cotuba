package cotuba.cli;

import cotuba.application.Cotuba;

public class Main {

  public static void main(String[] args) {

    boolean modoVerboso = false;

    try {

      var opcoesCLI = new LeitorOpcoesCLI(args);

      var cotuba = new Cotuba();
      cotuba.executa(opcoesCLI.getFormato(), opcoesCLI.getDiretorioDosMD(), opcoesCLI.getArquivoDeSaida());

      System.out.println("Arquivo gerado com sucesso: " + opcoesCLI.getArquivoDeSaida());

    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      if (modoVerboso) {
        ex.printStackTrace();
      }
      System.exit(1);
    }
  }

}
