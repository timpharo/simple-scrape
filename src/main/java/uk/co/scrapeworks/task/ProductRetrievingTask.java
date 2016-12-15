package uk.co.scrapeworks.retriever;

import uk.co.scrapeworks.domain.Product;
import uk.co.scrapeworks.domain.WebPageResponse;
import uk.co.scrapeworks.extractor.ProductInfoExtractor;

import java.util.concurrent.Callable;

public class ProductRetrievingTask implements Callable<Product> {

    private final ProductInfoExtractor productInfoExtractor;
    private final WebPageRetriever webPageRetriever;
    private final String productHtml;

    public ProductRetrievingTask(ProductInfoExtractor productInfoExtractor, WebPageRetriever webPageRetriever,
                                 String productHtml){
        this.productInfoExtractor = productInfoExtractor;
        this.webPageRetriever = webPageRetriever;
        this.productHtml = productHtml;
    }

    @Override
    public Product call() throws Exception {
        Product product = new Product();
        product.setTitle(productInfoExtractor.getTitle(productHtml));
        product.setUnitPrice(productInfoExtractor.getUnitPrice(productHtml));

        String productUrl = productInfoExtractor.getProductUrl(productHtml);

        WebPageResponse productResponse = webPageRetriever.retrievePage(productUrl);
        product.setSize(String.valueOf(productResponse.getResponseSize()) + "kb");

        return product;
    }
}
