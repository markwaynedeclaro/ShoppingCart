package com.automatic.shoppingcart.service.impl;

import com.automatic.shoppingcart.model.Promotion;
import com.automatic.shoppingcart.model.Tour;
import com.automatic.shoppingcart.repository.TourRepository;
import com.automatic.shoppingcart.repository.impl.TourRepositoryImpl;
import com.automatic.shoppingcart.service.PromotionalRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OrderCountDiscountPromo implements PromotionalRule {

    private Promotion promo;

    private TourRepository tourRepository;

    public OrderCountDiscountPromo(Promotion promo) {
        tourRepository = new TourRepositoryImpl();
        this.promo = promo;
    }

    @Override
    public Boolean hasMatchedItems(List<Tour> tours) {

        List<String> tourIDs;

        if (promo.isOnTop()) {
            tourIDs = tours.stream()
                    .filter(t->t.isOriginalTag())
                    .map(t->t.getId())
                    .collect(Collectors.toList());
        } else {
            tourIDs = tours.stream()
                    .filter(t->t.isOriginalTag() && !t.isPromoApplied())
                    .map(t->t.getId())
                    .collect(Collectors.toList());
        }

        String qualifier = this.promo.getQualifiers().get(0);
        if (Collections.frequency(tourIDs, qualifier) >= this.promo.getQuantity())
            return true;
        return false;
    }

    @Override
    public List<Tour> applyPromotion(List<Tour> tours) {
        List<Tour> newTours = new ArrayList<>();
        List<String> tourIDs = tours.stream()
                .filter(t->t.isOriginalTag())
                .map(t->t.getId())
                .collect(Collectors.toList());
        String qualifier = this.promo.getQualifiers().get(0);

        if (Collections.frequency(tourIDs, qualifier) >= this.promo.getQuantity()) {
            for (String id : tourIDs) {
                if (id.equals(qualifier))
                    newTours.add(createTourObjects(id, false));
                else
                    newTours.add(createTourObjects(id, true));
            }
        }

        return newTours;
    }

    private Tour createTourObjects(String id, boolean original) {

        Tour tour = new Tour(tourRepository.getTour(id));
        if (!original) {
            tour.setOriginalTag(false);
            tour.setPrice(tour.getPrice().multiply((new BigDecimal(1.0)).subtract(this.promo.getDiscount())).setScale(2, BigDecimal.ROUND_HALF_UP));
        }

        return tour;
    }

}
