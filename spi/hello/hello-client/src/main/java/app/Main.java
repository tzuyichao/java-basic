package app;

import org.greenrivers.segmenter.SegmenterProvider;
import org.greenrivers.synonym.SynonymManager;
import org.greenrivers.synonym.SynonymProvider;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        ServiceLoader<SynonymProvider> synonymProviderLoader = ServiceLoader.load(SynonymProvider.class);
        Iterator<SynonymProvider> synonymProviderIterator = synonymProviderLoader.iterator();
        while(synonymProviderIterator.hasNext()) {
            SynonymProvider synonymProvider = synonymProviderIterator.next();
            System.out.println(synonymProvider);
            SynonymManager synonymManager = synonymProvider.create();
            System.out.println(synonymManager.expand("idea"));
            System.out.println(synonymManager.expand("test"));
        }

        ServiceLoader<SegmenterProvider> segmenterProviderServiceLoader = ServiceLoader.load(SegmenterProvider.class);
        if(!segmenterProviderServiceLoader.iterator().hasNext()) {
            System.out.println("no segmenter provider implements found");
        }
    }
}
