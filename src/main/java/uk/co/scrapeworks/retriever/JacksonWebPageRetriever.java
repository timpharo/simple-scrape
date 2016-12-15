package uk.co.scrapeworks.retrieval;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.net.URI;

public class JacksonWebpageRetriever implements WebpageRetriever {

    @Override
    public WebpageDetails retrievePage(URI webpageUri) {
        Client client = ClientBuilder.newBuilder().build();

        Response response = client.target(webpageUri).request().get();

        return new WebpageDetails(response.readEntity(String.class), response.getLength());
    }
}
