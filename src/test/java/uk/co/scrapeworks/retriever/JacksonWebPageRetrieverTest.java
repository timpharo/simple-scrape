package uk.co.scrapeworks.retrieval;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class JacksonWebpageRetrieverTest {
    private static final String FIXTURES_PATH = "src/test/resources/";
    private static final int WIREMOCK_PORT = 9080;
    private static final URI WEBPAGE_URI = URI.create("http://localhost:" + WIREMOCK_PORT);

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(WIREMOCK_PORT);

    private String testWebpageFixture;

    private JacksonWebpageRetriever webpageRetriever;

    @Before
    public void setUp() throws IOException {
        testWebpageFixture = new String(Files.readAllBytes(Paths.get(FIXTURES_PATH +"test-webpage.html")));
        webpageRetriever = new JacksonWebpageRetriever();
    }

    @Test
    public void returnsCorrectHtmlAndContentSize(){
        stubFor(get(urlPathMatching("/"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/html")
                        .withHeader("Content-Length", "100")
                        .withBody(testWebpageFixture)));

        WebpageDetails result = webpageRetriever.retrievePage(WEBPAGE_URI);

        assertThat(result.getResponse()).isEqualTo(testWebpageFixture);
        assertThat(result.getResponseSize()).isEqualTo(100);
    }

}