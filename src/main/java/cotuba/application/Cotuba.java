package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
public class Cotuba {

    private final RenderizadorMDParaHtml renderizadorMDParaHtml;
    private final GeradorEPUB geradorEPUB;
    private final GeradorPDF geradorPDF;

    public Cotuba(RenderizadorMDParaHtml renderizadorMDParaHtml, GeradorEPUB geradorEPUB, GeradorPDF geradorPDF) {
        this.renderizadorMDParaHtml = renderizadorMDParaHtml;
        this.geradorEPUB = geradorEPUB;
        this.geradorPDF = geradorPDF;
    }

    public void executa(ParametrosCotuba parametros){
        Path diretorioDosMD = parametros.getDiretorioDosMD();
        Path arquivoDeSaida = parametros.getArquivoDeSaida();
        String formato = parametros.getFormato();

        List<Capitulo> capitulos = renderizadorMDParaHtml.renderiza(diretorioDosMD);
        if ("pdf".equals(formato)) {

            Ebook ebook = new Ebook();
            ebook.setFormato(formato);
            ebook.setArquivoDeSaida(arquivoDeSaida);
            ebook.setCapitulos(capitulos);

            geradorPDF.gera(ebook);

        } else if ("epub".equals(formato)) {
            Ebook ebook = new Ebook();
            ebook.setFormato(formato);
            ebook.setArquivoDeSaida(arquivoDeSaida);
            ebook.setCapitulos(capitulos);

            geradorEPUB.gera(ebook);

        } else {
            throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
        }
    }
}
