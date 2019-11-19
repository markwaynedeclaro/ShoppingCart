package com.automatic.shoppingcart.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Promotion implements Comparable<Promotion> {

    private int priority;
    private boolean onTop;

    private String promoType;
    private BigDecimal discount;
    private int quantity;

    /**
     if this list is in the cart, apply the promo
     */
    private List<String> qualifiers;

    /**
     id of free items once qualifiers are met
     */
    private List<String> freeItems;

    public Promotion(int priority, boolean onTop, String promoType, BigDecimal discount, int quantity, List<String> qualifiers, List<String> freeItems) {
        this.priority = priority;
        this.onTop = onTop;
        this.promoType = promoType;
        this.discount = discount;
        this.quantity = quantity;
        this.qualifiers = qualifiers;
        this.freeItems = freeItems;
    }


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isOnTop() {
        return onTop;
    }

    public void setOnTop(boolean onTop) {
        this.onTop = onTop;
    }

    public String getPromoType() {
        return promoType;
    }

    public void setPromoType(String promoType) {
        this.promoType = promoType;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<String> getQualifiers() {
        return qualifiers;
    }

    public void setQualifiers(List<String> qualifiers) {
        this.qualifiers = qualifiers;
    }

    public List<String> getFreeItems() {
        return freeItems;
    }

    public void setFreeItems(List<String> freeItems) {
        this.freeItems = freeItems;
    }

    @Override
    public int compareTo(Promotion promotion) {
        return (this.getPriority() < promotion.getPriority() ? -1 :
                (this.getPriority() == promotion.getPriority() ? 0 : 1));
    }
}
