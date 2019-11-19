package com.automatic.shoppingcart.service.impl;
import com.automatic.shoppingcart.model.Promotion;
import com.automatic.shoppingcart.model.Tour;
import com.automatic.shoppingcart.repository.TourRepository;
import com.automatic.shoppingcart.repository.impl.TourRepositoryImpl;
import com.automatic.shoppingcart.service.PromotionalRule;

import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BuyXGetXPromo implements PromotionalRule {

    private Promotion promo;

    private TourRepository tourRepository;

    public BuyXGetXPromo(Promotion promo) {
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

        if (tourIDs.containsAll(this.promo.getQualifiers())) {
            return true;
        }
        return false;
    }

    @Override
    public List<Tour> applyPromotion(List<Tour> tours) {
        List<Tour> newTours = new ArrayList<>();
        List<String> tourIDs = tours.stream()
                .filter(t->t.isOriginalTag())
                .map(t->t.getId())
                .collect(Collectors.toList());
        List<String> qualifiers = this.promo.getQualifiers();
        List<String> freeItems = this.promo.getFreeItems();

        while (checkIfQualifiersExist(new ArrayList<>(tourIDs), qualifiers)) {

            for (String id : qualifiers) {

                Tour tour = tours.stream().filter(t->t.getId().equals(id)).findFirst().get();
                tours.remove(tour);

                newTours.add(editTourObjects(new Tour(tour), false));
                tourIDs.remove(id);

            }

            for (String id : freeItems) {

                if (tourIDs.contains(id)) {

                    Tour tour = tours.stream().filter(t->t.getId().equals(id)).findFirst().get();
                    tours.remove(tour);

                    newTours.add(editTourObjects(new Tour(tour), true));
                    tourIDs.remove(id);

                } else {
                    newTours.add(createTourObjects(id, false, true));
                }

            }

        }

        if (tourIDs.size() > 0) {
            for (Tour tour : tours) {
                newTours.add(editTourObjects(new Tour(tour), false));
            }
        }

        return newTours;
    }

    private Tour createTourObjects(String id, boolean original, boolean applyPromo) {

        Tour tour = new Tour(tourRepository.getTour(id));
        tour.setPromoApplied(true);
        tour.setOriginalTag(original);
        if (applyPromo) {
            tour.setPrice(new BigDecimal(0.0));
        }

        return tour;
    }

    private Tour editTourObjects(Tour tour, boolean applyPromo) {

        tour.setPromoApplied(true);
        if (applyPromo) {
            tour.setPrice(new BigDecimal(0.0));
        }

        return tour;
    }


    private boolean checkIfQualifiersExist(List<String> tourIDs, List<String> qualifiers) {
        boolean qualifierExist = true;

        for (String id : qualifiers) {
            if (tourIDs.contains(id)) {
                tourIDs.remove(id);
            } else {
                qualifierExist = false;
                break;
            }
        }

        return qualifierExist;
    }

}
