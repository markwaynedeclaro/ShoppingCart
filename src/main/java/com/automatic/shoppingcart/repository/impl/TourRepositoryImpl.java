package com.automatic.shoppingcart.repository.impl;

import com.automatic.shoppingcart.model.Tour;
import com.automatic.shoppingcart.repository.TourRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TourRepositoryImpl implements TourRepository {

    private Map<String, Tour> tours;

    public TourRepositoryImpl() {
        tours = new HashMap<>();
        tours.put("OH", new Tour("OH", "Opera house tour",    new BigDecimal(300.00).setScale(2, BigDecimal.ROUND_HALF_UP), true, false));
        tours.put("BC", new Tour("BC", "Sydney Bridge Climb", new BigDecimal(110.00).setScale(2, BigDecimal.ROUND_HALF_UP), true, false));
        tours.put("SK", new Tour("SK", "Sydney Sky Tower",    new BigDecimal(300.00).setScale(2, BigDecimal.ROUND_HALF_UP), true, false));
    }

    @Override
    public Tour getTour(String id) {
        return tours.get(id);
    }

}
