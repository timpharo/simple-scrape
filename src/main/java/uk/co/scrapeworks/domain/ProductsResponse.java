package uk.co.scrapeworks.domain;

import java.util.List;

public class ProductsResponse {
    private final List<Product> products;
    private final Double total;

    public ProductsResponse(List<Product> products, Double total) {
        this.products = products;
        this.total = total;
    }
}
