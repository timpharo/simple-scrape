package uk.co.scrapeworks.extractor;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class JSoupProductInfoExtractorTest {

    private JSoupProductInfoExtractor jSoupProductInfoExtractor;

    @Before
    public void setUp() throws IOException {
        jSoupProductInfoExtractor = new JSoupProductInfoExtractor();
    }

    @Test
    public void parsesTitleCorrectly(){
        String productHtml = "<div class=\"productInfo\"><a href=\"#\">Apricots</p></div>";

        String result = jSoupProductInfoExtractor.getTitle(productHtml);

        assertThat(result).isEqualTo("Apricots");
    }

    @Test
    public void parsesUnitPriceCorrectly(){
        String productHtml = "<div><p class=\"pricePerUnit\">&pound3.50/unit</p></div>";

        Double result = jSoupProductInfoExtractor.getUnitPrice(productHtml);

        assertThat(result).isEqualTo(3.50);
    }

    @Test
    public void parsesProductUrlCorrectly(){
        String productHtml = "<div class=productInfo><a href=\"http://www.products.com/apricot\">Apricots </a></div>";

        String result = jSoupProductInfoExtractor.getProductUrl(productHtml);

        assertThat(result).isEqualTo("http://www.products.com/apricot");
    }

}