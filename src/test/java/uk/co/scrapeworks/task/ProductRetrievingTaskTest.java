package uk.co.scrapeworks.retriever;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.scrapeworks.domain.Product;
import uk.co.scrapeworks.domain.WebPageResponse;
import uk.co.scrapeworks.extractor.ProductInfoExtractor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProductRetrievingTaskTest {
    private static final String PRODUCT_HTML = "<html></html>";
    private static final String TITLE = "Title";
    private static final String PRODUCT_URL = "http://www.awebsite.com";
    private static final double UNIT_PRICE = 20.76;

    @Mock
    private ProductInfoExtractor productInfoExtractor;

    @Mock
    private WebPageRetriever webPageRetriever;

    private ProductRetrievingTask productRetrievingTask;

    @Before
    public void setUp(){
        given(productInfoExtractor.getProductUrl(PRODUCT_HTML)).willReturn(PRODUCT_URL);
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
        WebPageResponse webPageResponse = new WebPageResponse("", 3456);
        given(productInfoExtractor.getProductUrl(PRODUCT_HTML)).willReturn(PRODUCT_URL);
        given(webPageRetriever.retrievePage(PRODUCT_URL)).willReturn(webPageResponse);

        Product result = productRetrievingTask.call();

        assertThat(result.getSize()).isEqualTo("3456kb");
    }


}