package uk.co.scrapeworks.domain;

public class Response {
    private final String response;
    private final int responseSize;

    public Response(String response, int responseSize){
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
