package uk.co.scrapeworks.retriever;

import uk.co.scrapeworks.domain.WebPageResponse;

public interface WebPageRetriever {

    WebPageResponse retrievePage(String string);
}
