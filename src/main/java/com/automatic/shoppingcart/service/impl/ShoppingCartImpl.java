package com.automatic.shoppingcart.service.impl;

import com.automatic.shoppingcart.model.PromoEnum;
import com.automatic.shoppingcart.model.Promotion;
import com.automatic.shoppingcart.model.Tour;
import com.automatic.shoppingcart.service.PromotionalRule;
import com.automatic.shoppingcart.service.ShoppingCart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.automatic.shoppingcart.model.PromoEnum.ORDERCOUNTDISCOUNT;

public class ShoppingCartImpl implements ShoppingCart {

    private List<Tour> tours;
    private List<PromotionalRule> promotionalRules;

    public ShoppingCartImpl(List<Promotion> promotions) {
        this.promotionalRules = this.generatePromotionalRules(promotions);
        tours = new ArrayList<>();
    }

    /**
     * add a tour data
     * @param tour
     */
    @Override
    public void add(Tour tour) {
        tours.add(tour);
    }

    /**
     * Apply the promotional rules first, then compute the total amount
     * @return total
     */
    @Override
    public BigDecimal total() {

        BigDecimal total = new BigDecimal(0.0);
        List<Tour> tours = applyPromotions();

        for(Tour tour: tours) {
            total = total.add(tour.getPrice());
        }

        this.tours = tours;
        total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("Total : "+total);
        return total;
    }

    @Override
    public void displayCart(List<Tour> tours) {
        int i=0;
        for (Tour t : tours) {
            i++;
            System.out.println(i+" "+t.getId()+" - "+t.getName()+"  :  "+t.getPrice());
        }

    }


    /**
     * Generate Promotional Rules based from db data
     * @return newTours
     */
    private List<PromotionalRule> generatePromotionalRules(List<Promotion> promotions) {
        List<PromotionalRule> promotionalRules = new ArrayList<>();

        // Sort by priority
        Collections.sort(promotions);

        for (Promotion promo: promotions) {
            if (promo.getPromoType().equals(PromoEnum.BUYXGETX.toString())) {
                promotionalRules.add(new BuyXGetXPromo(promo));
            } else if (promo.getPromoType().equals(ORDERCOUNTDISCOUNT.toString())) {
                promotionalRules.add(new OrderCountDiscountPromo(promo));
            }
        }

        return promotionalRules;
    }

    /**
     * Apply the necessary promotional rules here
     * @return newTours
     */
    private List<Tour> applyPromotions() {

        List<Tour> newTours = this.tours;

        for (PromotionalRule promo: this.promotionalRules) {

            if (promo.hasMatchedItems(newTours)) {
                newTours = promo.applyPromotion(newTours);
            }

        }

        displayCart(newTours);

        return newTours;
    }

}
