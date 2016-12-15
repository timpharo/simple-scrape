package uk.co.scrapeworks.domain;

public class WebPageResponse {
    private final String response;
    private final int responseSize;

    public WebPageResponse(String response, int responseSize){
        this.response = response;
        this.responseSize = responseSize;
    }

    public String getResponse() {
        return response;
    }

    public int getResponseSize() {
        return responseSize;
    }
}
