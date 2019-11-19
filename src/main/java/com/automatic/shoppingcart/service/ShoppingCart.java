package com.automatic.shoppingcart.service;

import com.automatic.shoppingcart.model.Tour;

import java.math.BigDecimal;
import java.util.List;

/**
 The main interface that will process the checking out process
 */
public interface ShoppingCart {

    void add(Tour tour);

    BigDecimal total();

    void displayCart(List<Tour> tours);

}
