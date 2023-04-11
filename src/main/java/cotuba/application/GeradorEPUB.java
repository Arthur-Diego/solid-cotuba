package cotuba.application;

import cotuba.domain.Ebook;
import cotuba.epub.GeradorEPUBComEpublib;

public interface GeradorEPUB {
    void gera(Ebook ebook);

    static GeradorEPUBComEpublib cria(){
        return new GeradorEPUBComEpublib();
    }
}
