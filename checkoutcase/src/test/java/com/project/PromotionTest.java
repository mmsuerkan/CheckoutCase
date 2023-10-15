package com.project;

import com.project.exception.ChartEmptyException;
import com.project.model.cart.Cart;
import com.project.model.item.DefaultItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromotionTest {

    private Cart cart;

    @Before
    public void setUp() {
        cart = new Cart();
    }

    @Test
    @DisplayName("Apply Same Seller Promotion")
    public void apply_same_seller_promotion() throws ChartEmptyException {
        DefaultItem defaultItem = new DefaultItem(1, 100, 200, 500, 1);

        cart.addItem(defaultItem);

        DefaultItem defaultItem2 = new DefaultItem(2, 100, 200, 1000, 1);

        cart.addItem(defaultItem2);

        DefaultItem defaultItem3 = new DefaultItem(3, 100, 200, 5000, 1);

        cart.addItem(defaultItem3);

        cart.applyPromotions();

        assertEquals(3, cart.getItems().size());
        assertEquals(9909, cart.getAppliedPromotionId());
        assertEquals(650.0, cart.getTotalDiscount());

    }

    @Test
    @DisplayName("Category Promotion")
    public void apply_category_promotion() throws ChartEmptyException {
        DefaultItem defaultItem = new DefaultItem(1, 3003, 1, 30, 1);

        cart.addItem(defaultItem);

        DefaultItem defaultItem2 = new DefaultItem(2, 3003, 2, 20, 1);

        cart.addItem(defaultItem2);

        DefaultItem defaultItem3 = new DefaultItem(3, 3003, 3, 50, 1);

        cart.addItem(defaultItem3);

        cart.applyPromotions();

        assertEquals(3, cart.getItems().size());
        assertEquals(5676, cart.getAppliedPromotionId());
        assertEquals(2.5, cart.getTotalDiscount());
    }

    @Test
    @DisplayName("Apply Total Price Promotion")
    public void apply_total_price_promotion() throws ChartEmptyException {
        DefaultItem defaultItem = new DefaultItem(1, 100, 1, 900, 4);

        cart.addItem(defaultItem);

        DefaultItem defaultItem2 = new DefaultItem(2, 200, 2, 5000, 5);

        cart.addItem(defaultItem2);

        DefaultItem defaultItem3 = new DefaultItem(3, 300, 3, 10000, 10);

        cart.addItem(defaultItem3);

        cart.applyPromotions();

        assertEquals(3, cart.getItems().size());
        assertEquals(1232, cart.getAppliedPromotionId());
        assertEquals(2000.0, cart.getTotalDiscount());
    }
}
