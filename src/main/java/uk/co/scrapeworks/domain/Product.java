package uk.co.scrapeworks.domain;

public class ProductDetails {

    private final String title;
    private final String size;
    private final Double unitPrice;
    private final String description;

    public ProductDetails(String title, String size, Double unitPrice, String description) {
        this.title = title;
        this.size = size;
        this.unitPrice = unitPrice;
        this.description = description;
    }

}
