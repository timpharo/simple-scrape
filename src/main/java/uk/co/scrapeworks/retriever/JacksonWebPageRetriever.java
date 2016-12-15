package uk.co.scrapeworks.retriever;

import uk.co.scrapeworks.domain.WebPageResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class JacksonWebPageRetriever implements WebPageRetriever {

    /**
     * A simple method that retrieves the content of a webpage and returns a non http client specific
     * object (loose coupling).  In a production application we would handle exceptions and
     * non 200 responses by throwing non client specific Exceptions
     */
    @Override
    public WebPageResponse retrievePage(String webpageUrl) {
        Client client = ClientBuilder.newBuilder().build();

        Response response = client.target(webpageUrl).request().get();

        return new WebPageResponse(response.readEntity(String.class), response.getLength());
    }
}
