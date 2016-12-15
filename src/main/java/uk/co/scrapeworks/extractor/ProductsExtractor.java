package uk.co.scrapeworks.extractor;

import java.util.List;

public interface ProductsExtractor {

    List<String> extract(String html);
}
