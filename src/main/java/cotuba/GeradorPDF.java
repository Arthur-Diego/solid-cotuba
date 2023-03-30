package cotuba;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.property.AreaBreakType;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.stream.Stream;

public class GeradorPDF {
    
    public void gera(Ebook ebook){

        Path arquivoDeSaida = ebook.getArquivoDeSaida();

        try(var writer = new PdfWriter(Files.newOutputStream(ebook.getArquivoDeSaida()));

            var pdf = new PdfDocument(writer);
            var pdfDocument = new Document(pdf)) {

            for(Capitulo capitulo: ebook.getCapitulos()) {

                String html = capitulo.getConteudoHTML();
                List<IElement> convertToElements = HtmlConverter.convertToElements(html);

                for (IElement element : convertToElements) {
                    pdfDocument.add((IBlockElement) element);
                }

                if(!ebook.isUltimoCapitulo(capitulo)) {
                    pdfDocument.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                }
            };

        } catch (Exception ex) {
            throw new IllegalStateException("Erro ao criar arquivo PDF: " + arquivoDeSaida.toAbsolutePath(), ex);
        }

    }
}
