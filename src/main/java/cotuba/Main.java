package cotuba;

import java.util.List;

public class Main {

  public static void main(String[] args) {

    boolean modoVerboso = false;

    try {

      var opcoesCLI = new LeitorOpcoesCLI(args);
      var renderizador = new RenderizadorMDParaHtml();
      List<Capitulo> capitulos = renderizador.renderiza(opcoesCLI.getDiretorioDosMD());
      if ("pdf".equals(opcoesCLI.getFormato())) {

        Ebook ebook = new Ebook();
        ebook.setFormato(opcoesCLI.getFormato());
        ebook.setArquivoDeSaida(opcoesCLI.getArquivoDeSaida());
        ebook.setCapitulos(capitulos);

        var geradorPDF = new GeradorPDF();
        geradorPDF.gera(ebook);

      } else if ("epub".equals(opcoesCLI.getFormato())) {
        Ebook ebook = new Ebook();
        ebook.setFormato(opcoesCLI.getFormato());
        ebook.setArquivoDeSaida(opcoesCLI.getArquivoDeSaida());
        ebook.setCapitulos(capitulos);
        var geradorEPUB = new GeradorEPUB();
        geradorEPUB.gera(ebook);

      } else {
        throw new IllegalArgumentException("Formato do ebook inválido: " + opcoesCLI.getFormato());
      }

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
