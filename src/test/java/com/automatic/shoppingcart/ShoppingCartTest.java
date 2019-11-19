package com.automatic.shoppingcart;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


import com.automatic.shoppingcart.model.PromoEnum;
import com.automatic.shoppingcart.model.Promotion;
import com.automatic.shoppingcart.model.Tour;
import com.automatic.shoppingcart.repository.TourRepository;
import com.automatic.shoppingcart.repository.impl.TourRepositoryImpl;

import com.automatic.shoppingcart.service.ShoppingCart;
import com.automatic.shoppingcart.service.impl.ShoppingCartImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



import java.math.BigDecimal;
import java.util.*;

/**
 * Unit test for Shopping Cart App.
 */

public class ShoppingCartTest
{
    private List<Promotion> promotions;
    private Map<String, Tour> tours;

    private ShoppingCart sp;
    // private TourRepository tourRepository;


    @Before
    public void setUp() throws Exception {

        promotions = new ArrayList<>();
        promotions.add(new Promotion(
                1,
                false,
                PromoEnum.BUYXGETX.toString(),
                new BigDecimal(0.00),
                0,
                new ArrayList<String>(Arrays.asList("OH","OH")),
                new ArrayList<String>(Arrays.asList("OH"))
        ));
        promotions.add(new Promotion(
                2,
                true,
                PromoEnum.BUYXGETX.toString(),
                new BigDecimal(0.00),
                0,
                new ArrayList<String>(Arrays.asList("OH")),
                new ArrayList<String>(Arrays.asList("SK"))
        ));
        promotions.add(new Promotion(
                3,
                false,
                PromoEnum.ORDERCOUNTDISCOUNT.toString(),
                new BigDecimal(0.81818181818181818181818182),
                4,
                new ArrayList<String>(Arrays.asList("BC")),
                null)
        );

        tours = new HashMap<>();
        tours.put("OH", new Tour("OH", "Opera house tour",    new BigDecimal(300.00).setScale(2, BigDecimal.ROUND_HALF_UP), true, false));
        tours.put("BC", new Tour("BC", "Sydney Bridge Climb", new BigDecimal(110.00).setScale(2, BigDecimal.ROUND_HALF_UP), true, false));
        tours.put("SK", new Tour("SK", "Sydney Sky Tower",    new BigDecimal(300.00).setScale(2, BigDecimal.ROUND_HALF_UP), true, false));

        sp = new ShoppingCartImpl(promotions);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldApplyPromoOneAndTwo()
    {
        System.out.println("shouldApplyPromoOneAndTwo ======================================================================");
        sp.add(tours.get("OH"));
        sp.add(tours.get("OH"));
        sp.add(tours.get("OH"));
        sp.add(tours.get("BC"));
        sp.add(tours.get("OH"));
        sp.add(tours.get("OH"));
        assertEquals(new BigDecimal(1310.00).setScale(2, BigDecimal.ROUND_HALF_UP), sp.total());
    }

    @Test
    public void shouldApplyPromoTwo()
    {
        System.out.println("shouldApplyPromoTwo ======================================================================");
        sp.add(tours.get("OH"));
        assertEquals(new BigDecimal(300.00).setScale(2, BigDecimal.ROUND_HALF_UP), sp.total());
    }

    @Test
    public void shouldApplyPromoThree()
    {
        System.out.println("shouldApplyPromoThree ======================================================================");
        sp.add(tours.get("BC"));
        sp.add(tours.get("BC"));
        sp.add(tours.get("BC"));
        sp.add(tours.get("BC"));
        sp.add(tours.get("BC"));
        assertEquals(new BigDecimal(100.00).setScale(2, BigDecimal.ROUND_HALF_UP), sp.total());
    }


}
