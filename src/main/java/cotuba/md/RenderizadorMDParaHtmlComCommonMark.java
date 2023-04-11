package cotuba.md;

import cotuba.application.RenderizadorMDParaHtml;
import cotuba.domain.Capitulo;
import org.commonmark.node.Node;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RenderizadorMDParaHtmlComCommonMark implements RenderizadorMDParaHtml {

    @Override
    public List<Capitulo> renderiza(Path diretorioDosMD){

        return obtemArquivosMD(diretorioDosMD).stream()
                .map(arquivoMD -> {
                    Capitulo capitulo = new Capitulo();
                    Node document = RenderizadorMDParaHtml.parseDoMD(arquivoMD, capitulo);
                    RenderizadorMDParaHtml.renderizaParaHTML(arquivoMD, capitulo, document);
                    return capitulo;
                }).toList();
    }
    @Override
    public List<Path> obtemArquivosMD(Path diretorioDosMD){
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");
        try (Stream<Path> arquivosMD = Files.list(diretorioDosMD)){
            return arquivosMD.filter(matcher::matches).collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException("Erro tentando encontrar arquivos .md em " + diretorioDosMD.toAbsolutePath(), e);
        }
    }

}
