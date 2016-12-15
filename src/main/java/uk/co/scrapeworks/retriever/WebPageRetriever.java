package uk.co.scrapeworks.retrieval;

import java.net.URI;

public interface WebpageRetriever {

    WebpageDetails retrievePage(URI uri);

}
