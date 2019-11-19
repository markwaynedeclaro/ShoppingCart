package com.automatic.shoppingcart.service;

import com.automatic.shoppingcart.model.Tour;

import java.util.List;

/**
 * Promotional Rules that will be applied on Checkout process
 */
public interface PromotionalRule {
/*
    buyXGetX
    order - discount to total if reached max
    product promo - % off
    freeProductDeal -
*/
    Boolean hasMatchedItems(List<Tour> tours);
    List<Tour> applyPromotion(List<Tour> tours);

}
