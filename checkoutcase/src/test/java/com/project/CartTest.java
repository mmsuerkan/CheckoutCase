package com.project;

import com.project.model.cart.Cart;
import com.project.model.item.DefaultItem;
import com.project.model.item.DigitalItem;
import com.project.model.item.Item;
import com.project.service.PriceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

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
    @DisplayName("Add one default item to chart")
    public void add_one_default_item_to_chart() {
        Item item = new DefaultItem(1, 100, 200, 5);
        double price = priceService.getPriceBySellerAndItemId(String.valueOf(1), 1);
        item.setPrice(price);

        boolean result = cart.addItem(item);
        assertTrue(result);

        assertEquals(1, cart.getItems().size());

        assertEquals(item.getTotalPrice(), cart.getTotalAmount());
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("The total number of products cannot exceed 30")
    public void add_32_item_to_chart() {
        Item defaultItem = new DefaultItem(1, 100, 200, 6);
        defaultItem.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 1));

        DigitalItem digitalItem = new DigitalItem(2, 100, 200, 26);
        digitalItem.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 2));


        boolean addDefaultItem = cart.addItem(defaultItem);
        boolean addDigitalItem = cart.addItem(digitalItem);

        assertTrue(addDefaultItem);
        assertTrue(addDigitalItem);
    }
    @Test(expected = RuntimeException.class)
    @DisplayName("The total amount of the Cart cannot exceed 500,000 TL")
    public void add_item_to_chart_with_total_amount_exceed_500000() {
        Item defaultItem = new DefaultItem(1, 100, 200, 1);
        defaultItem.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 3));

        boolean addDefaultItem = cart.addItem(defaultItem);

        assertTrue(addDefaultItem);
    }
}
