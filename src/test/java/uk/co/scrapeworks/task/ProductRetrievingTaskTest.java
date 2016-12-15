package uk.co.scrapeworks.task;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.scrapeworks.domain.Product;
import uk.co.scrapeworks.domain.WebPageResponse;
import uk.co.scrapeworks.extractor.ProductInfoExtractor;
import uk.co.scrapeworks.retriever.WebPageRetriever;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProductRetrievingTaskTest {
    private static final String PRODUCT_HTML = "<html></html>";
    private static final String TITLE = "Title";
    private static final String PRODUCT_URL = "http://www.awebsite.com";
    private static final double UNIT_PRICE = 20.76;
    public static final String RESPONSE = "Hello products";
    public static final int RESPONSE_SIZE = 3456;

    @Mock
    private ProductInfoExtractor productInfoExtractor;

    @Mock
    private WebPageRetriever webPageRetriever;

    private WebPageResponse webPageResponse;

    private ProductRetrievingTask productRetrievingTask;

    @Before
    public void setUp(){
        webPageResponse = new WebPageResponse(RESPONSE, RESPONSE_SIZE);
        given(productInfoExtractor.getProductUrl(PRODUCT_HTML)).willReturn(PRODUCT_URL);
        given(webPageRetriever.retrievePage(PRODUCT_URL)).willReturn(webPageResponse);

        productRetrievingTask = new ProductRetrievingTask(productInfoExtractor, webPageRetriever, PRODUCT_HTML);
    }

    @Test
    public void setsExtractedProductInformationCorrectly() throws Exception {
        given(productInfoExtractor.getTitle(PRODUCT_HTML)).willReturn(TITLE);
        given(productInfoExtractor.getUnitPrice(PRODUCT_HTML)).willReturn(UNIT_PRICE);

        Product result = productRetrievingTask.call();

        assertThat(result.getTitle()).isEqualTo(TITLE);
        assertThat(result.getUnitPrice()).isEqualTo(UNIT_PRICE);
    }

    @Test
    public void setsSizeCorrectly() throws Exception {
        given(productInfoExtractor.getProductUrl(PRODUCT_HTML)).willReturn(PRODUCT_URL);


        Product result = productRetrievingTask.call();

        assertThat(result.getSize()).isEqualTo("3456kb");
    }


}