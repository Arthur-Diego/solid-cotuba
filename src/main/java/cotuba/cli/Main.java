package cotuba.cli;

import cotuba.CotubaConfig;
import cotuba.application.Cotuba;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {

    boolean modoVerboso = false;

    try {

      var opcoesCLI = new LeitorOpcoesCLI(args);

      ApplicationContext applicationContext =  new AnnotationConfigApplicationContext(CotubaConfig.class);
      Cotuba cotuba = applicationContext.getBean(Cotuba.class);

      cotuba.executa(opcoesCLI);

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
