package com.project;

import com.project.exception.ItemQuantityExceededException;
import com.project.exception.VasItemLimitExceededException;
import com.project.exception.VasItemPriceExceededException;
import com.project.model.cart.Cart;
import com.project.exception.ChartEmptyException;
import com.project.model.item.DefaultItem;
import com.project.model.item.DigitalItem;
import com.project.model.item.Item;
import com.project.model.item.VasItem;
import com.project.repository.PriceRepository;
import com.project.service.PriceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    private Cart cart;
    private PriceService priceService;

    private PriceRepository priceRepository;

    @Before
    public void setUp() {
        cart = new Cart();
        priceRepository = new PriceRepository();
        priceService = new PriceService(priceRepository);
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

    @Test
    @DisplayName("Maximum quantity of a Item cannot exceed 10")
    public void add_item_with_quantity_11() {
        Item item = new DefaultItem(1, 100, 200, 11);
        double price = priceService.getPriceBySellerAndItemId(String.valueOf(1), 1);
        item.setPrice(price);

        assertThrows(ItemQuantityExceededException.class, () -> cart.addItem(item));
    }


    @Test
    @DisplayName("The total number of products cannot exceed 30")
    public void add_32_item_to_chart() {
        Item defaultItem = new DefaultItem(1, 100, 200, 6);
        defaultItem.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 1));

        DigitalItem digitalItem = new DigitalItem(2, 100, 200, 26);
        digitalItem.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 2));


        boolean addDefaultItem = cart.addItem(defaultItem);

        assertTrue(addDefaultItem);
        assertThrows(RuntimeException.class, () -> cart.addItem(digitalItem));
    }

    @Test
    @DisplayName("The total amount of the Cart cannot exceed 500,000 TL")
    public void add_item_to_chart_with_total_amount_exceed_500000() {
        Item defaultItem = new DefaultItem(1, 100, 200, 1);
        defaultItem.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 3));

        assertThrows(RuntimeException.class, () -> cart.addItem(defaultItem));
    }

    @Test
    @DisplayName("Adding more than 10 unique items (excluding VasItems).")
    public void add_11_item_to_chart() {
        Item defaultItem = new DefaultItem(1, 100, 200, 1);
        defaultItem.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 4));

        Item defaultItem2 = new DefaultItem(2, 100, 200, 1);
        defaultItem2.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 5));

        Item defaultItem3 = new DefaultItem(3, 100, 200, 1);
        defaultItem3.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 6));

        Item defaultItem4 = new DefaultItem(4, 100, 200, 1);
        defaultItem4.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 7));

        Item defaultItem5 = new DefaultItem(5, 100, 200, 1);
        defaultItem5.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 8));

        Item defaultItem6 = new DefaultItem(6, 100, 200, 1);
        defaultItem6.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 9));

        Item defaultItem7 = new DefaultItem(7, 100, 200, 1);
        defaultItem7.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 10));

        Item defaultItem8 = new DefaultItem(8, 100, 200, 1);
        defaultItem8.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 11));

        Item defaultItem9 = new DefaultItem(9, 100, 200, 1);
        defaultItem9.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 12));

        Item defaultItem10 = new DefaultItem(10, 100, 200, 1);
        defaultItem10.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 13));

        Item defaultItem11 = new DefaultItem(11, 100, 200, 1);
        defaultItem11.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 14));

        cart.addItem(defaultItem);
        cart.addItem(defaultItem2);
        cart.addItem(defaultItem3);
        cart.addItem(defaultItem4);
        cart.addItem(defaultItem5);
        cart.addItem(defaultItem6);
        cart.addItem(defaultItem7);
        cart.addItem(defaultItem8);
        cart.addItem(defaultItem9);
        cart.addItem(defaultItem10);

        assertThrows(RuntimeException.class, () -> cart.addItem(defaultItem11));
    }

    @Test
    @DisplayName("Adding more than 10 unique items (excluding VasItems).")
    public void add_11_item_to_chart_including_vas_items() {
        VasItem vasItem = new VasItem(1, 1, 1, 1, 1);
        vasItem.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 4));


        DefaultItem defaultItem = new DefaultItem(1, 100, 200, 1);
        defaultItem.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 1));
        defaultItem.addVasItem(vasItem);

        DefaultItem defaultItem2 = new DefaultItem(2, 100, 200, 1);
        defaultItem2.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 5));
        defaultItem2.addVasItem(vasItem);

        Item defaultItem3 = new DefaultItem(3, 100, 200, 1);
        defaultItem3.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 6));

        Item defaultItem4 = new DefaultItem(4, 100, 200, 1);
        defaultItem4.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 7));

        Item defaultItem5 = new DefaultItem(5, 100, 200, 1);
        defaultItem5.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 8));

        Item defaultItem6 = new DefaultItem(6, 100, 200, 1);
        defaultItem6.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 9));

        Item defaultItem7 = new DefaultItem(7, 100, 200, 1);
        defaultItem7.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 10));

        Item defaultItem8 = new DefaultItem(8, 100, 200, 1);
        defaultItem8.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 11));

        Item defaultItem9 = new DefaultItem(9, 100, 200, 1);
        defaultItem9.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 12));

        Item defaultItem10 = new DefaultItem(10, 100, 200, 1);
        defaultItem10.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 13));

        Item defaultItem11 = new DefaultItem(11, 100, 200, 1);
        defaultItem11.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 14));

        cart.addItem(defaultItem);
        cart.addItem(defaultItem2);
        cart.addItem(defaultItem3);
        cart.addItem(defaultItem4);
        cart.addItem(defaultItem5);
        cart.addItem(defaultItem6);
        cart.addItem(defaultItem7);
        cart.addItem(defaultItem8);
        cart.addItem(defaultItem9);
        cart.addItem(defaultItem10);

        assertThrows(RuntimeException.class, () -> cart.addItem(defaultItem11));
    }

    @Test
    @DisplayName("A maximum of 3 VasItems  can be added to a DefaultItem.")
    public void add_4_vas_item_to_default_item() {
        VasItem vasItem = new VasItem(1, 1, 1, 1, 1);
        vasItem.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 4));

        DefaultItem defaultItem = new DefaultItem(1, 100, 200, 1);
        defaultItem.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 1));
        defaultItem.addVasItem(vasItem);
        defaultItem.addVasItem(vasItem);
        defaultItem.addVasItem(vasItem);
        defaultItem.addVasItem(vasItem);

        assertThrows(VasItemLimitExceededException.class, () -> defaultItem.addVasItem(vasItem));
    }

    @Test
    @DisplayName("Price of VasItem cannot exceed default item price")
    public void add_vas_item_to_default_item_with_price_exceed_default_item_price() {
        VasItem vasItem = new VasItem(1, 1, 1, 1, 1);
        vasItem.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 1));

        DefaultItem defaultItem = new DefaultItem(1, 100, 200, 1);
        defaultItem.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 4));


        assertThrows(VasItemPriceExceededException.class, () -> defaultItem.addVasItem(vasItem));
    }
    @Test
    @DisplayName("Remove Item from Empty Cart")
    public void remove_item_from_empty_cart() throws ChartEmptyException {

       assertThrows(ChartEmptyException.class, () -> cart.removeItem(1));
    }

    @Test
    @DisplayName("Remove Item from Cart")
    public void remove_item_from_cart() throws ChartEmptyException {
        Item defaultItem = new DefaultItem(1, 100, 200, 1);
        defaultItem.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 1));

        cart.addItem(defaultItem);
        boolean result = cart.removeItem(1);
        assertTrue(result);
        assertEquals(0, cart.getItems().size());
    }

    @Test
    @DisplayName("Reset Cart")
    public void reset_cart() throws ChartEmptyException {
        Item defaultItem = new DefaultItem(1, 100, 200, 1);
        defaultItem.setPrice(priceService.getPriceBySellerAndItemId(String.valueOf(1), 1));

        cart.addItem(defaultItem);
        cart.resetCart();
        assertEquals(0, cart.getItems().size());
        assertEquals(0, cart.getTotalAmount());
        assertEquals(0, cart.getTotalDiscount());
        assertEquals(0, cart.getAppliedPromotionId());
    }



}


