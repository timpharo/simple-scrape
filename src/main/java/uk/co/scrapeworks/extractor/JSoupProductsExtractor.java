package uk.co.scrapeworks.extractor;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.co.scrapeworks.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class JSoupProductsExtractor implements ProductsExtractor {
    @Override
    public List<Product> extract(String html) {
        List<Product> products = new ArrayList<>();
        Document htmlDocument = Jsoup.parse(html);

        Elements productElements = htmlDocument.select(".product");

        productElements.forEach(productElement -> {
                Product product = new Product();
                product.setTitle(extractTitle(productElement));
                product.setUnitPrice(extractPrice(productElement));
                product.setProductUrl(extractProductUrl(productElement));
                products.add(product);
        });

        return products;
    }


}
