package uk.co.scrapeworks.extractor;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JSoupProductInfoExtractor implements ProductInfoExtractor {

    @Override
    public String getTitle(String productHtml) {
        Document htmlDocument = Jsoup.parse(productHtml);
        return htmlDocument.select(".productInfo a").text();
    }

    @Override
    public Double getUnitPrice(String productHtml) {
        Document htmlDocument = Jsoup.parse(productHtml);
        String dirtyValue = htmlDocument.select("p.pricePerUnit").text();
        String cleanValue = StringUtils.remove(dirtyValue, "&pound");
        cleanValue = StringUtils.remove(cleanValue, "/unit");
        return Double.parseDouble(cleanValue);
    }

    @Override
    public String getProductUrl(String productHtml) {
        Document htmlDocument = Jsoup.parse(productHtml);
        return htmlDocument.select(".productInfo a").attr("href");
    }


}
