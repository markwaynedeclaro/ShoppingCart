package com.automatic.shoppingcart.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Tour implements Serializable {

    private String id;
    private String name;
    private BigDecimal price;
    private boolean originalTag;
    private boolean promoApplied;

    public Tour(String id, String name, BigDecimal price, boolean originalTag, boolean promoApplied) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.originalTag = originalTag;
        this.promoApplied = promoApplied;
    }

    public Tour(Tour tour) {
        this.id = tour.getId();
        this.name = tour.getName();
        this.price = tour.getPrice();
        this.originalTag = tour.isOriginalTag();
        this.promoApplied = tour.isPromoApplied();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isOriginalTag() {
        return originalTag;
    }

    public void setOriginalTag(boolean originalTag) {
        this.originalTag = originalTag;
    }

    public boolean isPromoApplied() { return promoApplied; }

    public void setPromoApplied(boolean promoApplied) { this.promoApplied = promoApplied; }
}
