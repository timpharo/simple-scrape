package uk.co.scrapeworks.extractor;

import uk.co.scrapeworks.domain.Product;

import java.util.List;

public interface ProductsExtractor {

    List<Product> extract(String html);

}
