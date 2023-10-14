package com.project;

import com.project.model.cart.Cart;
import com.project.model.item.DefaultItem;
import com.project.model.item.Item;
import com.project.service.PriceService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest {

    private Cart cart;
    private PriceService priceService;

    @Before
    public void setUp() {
        cart = new Cart();
        priceService = new PriceService();
    }

    @Test
    public void add_one_default_item_to_chart() {
        Item item = new DefaultItem(1, 100, 200, 5);
        double price = priceService.getPriceBySellerAndItemId(String.valueOf(1), 1);
        item.setPrice(price);
        // Sepete item ekleyin
        boolean result = cart.addItem(item);
        assertTrue(result);

        assertEquals(1, cart.getItems().size());

        assertEquals(item.getTotalPrice(), cart.getTotalAmount());
    }
}
