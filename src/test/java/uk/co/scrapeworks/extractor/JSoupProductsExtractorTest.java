package uk.co.scrapeworks.contentextractor;

import org.junit.Before;
import org.junit.Test;
import uk.co.scrapeworks.domain.Product;
import uk.co.scrapeworks.extractor.JSoupProductsExtractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JSoupProductsExtractorTest {
    private static final String FIXTURES_PATH = "src/test/resources/fixture/";

    private JSoupProductsExtractor jSoupProductExtractor;

    private String productsFixture;

    @Before
    public void setUp() throws IOException {
        productsFixture = new String(Files.readAllBytes(Paths.get(FIXTURES_PATH + "products.html")));
        jSoupProductExtractor = new JSoupProductsExtractor();
    }

    @Test
    public void parsesRightNumberOfProducts(){
        List<Product> results = jSoupProductExtractor.extract(productsFixture);

        assertThat(results.size()).isEqualTo(2);
    }

    @Test
    public void productsHaveCorrectTitles(){
        List<Product> results = jSoupProductExtractor.extract(productsFixture);

        assertThat(results.get(0).getTitle()).isEqualTo("Sainsbury's Apricot Ripe & Ready x5");
        assertThat(results.get(1).getTitle()).isEqualTo("Sainsbury's Avocado Ripe & Ready XL Loose 300g");
    }

    @Test
    public void productsHaveCorrectPrice(){
        List<Product> results = jSoupProductExtractor.extract(productsFixture);

        assertThat(results.get(0).getUnitPrice()).isEqualTo(3.50);
        assertThat(results.get(1).getUnitPrice()).isEqualTo(1.50);
    }
    @Test
    public void productsHaveCorrectProductUrl(){
        List<Product> results = jSoupProductExtractor.extract(productsFixture);

        assertThat(results.get(0).getProductUrl()).isEqualTo("http://hiring-tests/sainsburys-apricot-ripe---ready-320g.html");
        assertThat(results.get(1).getProductUrl()).isEqualTo("http://hiring-tests/sainsburys-avocado-xl-pinkerton-loose-300g.html");
    }


}