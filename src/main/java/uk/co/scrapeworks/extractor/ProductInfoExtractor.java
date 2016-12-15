package uk.co.scrapeworks.extractor;

public interface ProductInfoExtractor {
    String getTitle(String productHtml);

    Double getUnitPrice(String productHtml);

    String getProductUrl(String productHtml);
}
