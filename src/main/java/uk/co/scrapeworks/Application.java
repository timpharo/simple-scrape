package uk.co.scrapeworks;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import uk.co.scrapeworks.domain.Product;
import uk.co.scrapeworks.domain.ProductsResponse;
import uk.co.scrapeworks.extractor.JSoupProductInfoExtractor;
import uk.co.scrapeworks.extractor.JSoupProductsExtractor;
import uk.co.scrapeworks.extractor.ProductInfoExtractor;
import uk.co.scrapeworks.extractor.ProductsExtractor;
import uk.co.scrapeworks.retriever.JacksonWebPageRetriever;
import uk.co.scrapeworks.task.ProductRetrievingTask;
import uk.co.scrapeworks.domain.WebPageResponse;
import uk.co.scrapeworks.retriever.WebPageRetriever;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Application {
    //In the real world this would be in a config file somewhere
    private static final String PRODUCT_PAGE_URL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";

    private final ExecutorService executorService;

    public Application(){
        executorService = Executors.newFixedThreadPool(5);
    }

    public String run() throws InterruptedException {
        //As these all have interfaces, in a real product we may wish to use something like Guice to inject these
        final WebPageRetriever retriever = new JacksonWebPageRetriever();
        final ProductInfoExtractor productInfoExtractor = new JSoupProductInfoExtractor();
        final ProductsExtractor productsExtractor = new JSoupProductsExtractor();
        final WebPageRetriever webPageRetriever = new JacksonWebPageRetriever();

        final List<Product> products = new ArrayList<>();

        //Retrieve the products page
        final WebPageResponse productsPageResponse = retriever.retrievePage(PRODUCT_PAGE_URL);

        //Extract each products html
        final List<String> productsHtmlSnippets = productsExtractor.extract(productsPageResponse.getResponse());

        //Create callable's so that each product html snippet can be processed
        final List<ProductRetrievingTask> productsToProcess = productsHtmlSnippets.stream()
                .map(extractedHtml -> new ProductRetrievingTask(productInfoExtractor, webPageRetriever, extractedHtml))
                .collect(Collectors.toList());

        //Start the processing of all products and then get all the response objects
        executorService.invokeAll(productsToProcess).forEach(future -> {
            try {
                products.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        //Calculate the total price
        Double productsTotal = products.stream().mapToDouble(Product::getUnitPrice).sum();

        //Create our Wrapper object that contains all the details that will be returned as Json
        ProductsResponse response = new ProductsResponse(products, productsTotal);

        //Currently this outputs system specific and should produce a UTF-8 string
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        return gson.toJson(response);
    }
}
