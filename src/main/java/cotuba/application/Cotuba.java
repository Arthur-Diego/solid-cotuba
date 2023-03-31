package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.epub.GeradorEPUB;
import cotuba.md.RenderizadorMDParaHtml;
import cotuba.pdf.GeradorPDF;

import java.nio.file.Path;
import java.util.List;

public class Cotuba {
    
    public void executa(String formato, Path diretorioDosMD, Path arquivoDeSaida){
        var renderizador = new RenderizadorMDParaHtml();
        List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);
        if ("pdf".equals(formato)) {

            Ebook ebook = new Ebook();
            ebook.setFormato(formato);
            ebook.setArquivoDeSaida(arquivoDeSaida);
            ebook.setCapitulos(capitulos);

            var geradorPDF = new GeradorPDF();
            geradorPDF.gera(ebook);

        } else if ("epub".equals(formato)) {
            Ebook ebook = new Ebook();
            ebook.setFormato(formato);
            ebook.setArquivoDeSaida(arquivoDeSaida);
            ebook.setCapitulos(capitulos);
            var geradorEPUB = new GeradorEPUB();
            geradorEPUB.gera(ebook);

        } else {
            throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
        }
    }
}
