package uk.co.scrapeworks.extractor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

public class JSoupProductsExtractor implements ProductsExtractor {

    @Override
    public List<String> extract(String html) {
        Document htmlDocument = Jsoup.parse(html);
        Elements productElements = htmlDocument.select(".product");
        return productElements.stream().map(Element::html).collect(Collectors.toList());
    }


}
