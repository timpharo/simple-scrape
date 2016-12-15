package uk.co.scrapeworks.extractor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.scrapeworks.domain.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
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
        List<String> results = jSoupProductExtractor.extract(productsFixture);

        assertThat(results.size()).isEqualTo(2);
    }
}