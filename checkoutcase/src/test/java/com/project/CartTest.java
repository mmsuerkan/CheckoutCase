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
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {

    private Cart cart;
    @Before
    public void setUp() {
        cart = new Cart();
    }

    @Test
    @DisplayName("Add one default item to chart")
    public void add_one_default_item_to_chart() {
        Item item = new DefaultItem(1, 100, 200, 5, 1);

        boolean result = cart.addItem(item);
        assertTrue(result);
        assertEquals(1, cart.getItems().size());
        assertEquals(item.getTotalPrice(), cart.getTotalAmount());
    }

    @Test
    @DisplayName("Maximum quantity of a Item cannot exceed 10")
    public void add_item_with_quantity_11() {
        Item item = new DefaultItem(1, 100, 200, 11, 11);

        assertThrows(ItemQuantityExceededException.class, () -> cart.addItem(item));
    }


    @Test
    @DisplayName("The total number of products cannot exceed 30")
    public void add_32_item_to_chart() {
        Item defaultItem = new DefaultItem(1, 100, 200, 300, 10);

        Item defaultItem1 = new DefaultItem(2, 100, 200, 300, 10);

        Item defaultItem2 = new DefaultItem(3, 100, 200, 300, 10);

        DigitalItem digitalItem = new DigitalItem(2, 100, 200, 200, 10);


        cart.addItem(defaultItem);
        cart.addItem(defaultItem1);
        cart.addItem(defaultItem2);


        assertThrows(RuntimeException.class, () -> cart.addItem(digitalItem));
    }

    @Test
    @DisplayName("The total amount of the Cart cannot exceed 500,000 TL")
    public void add_item_to_chart_with_total_amount_exceed_500000() {
        Item defaultItem = new DefaultItem(1, 100, 200, 55000, 10);

        assertThrows(RuntimeException.class, () -> cart.addItem(defaultItem));
    }

    @Test
    @DisplayName("Adding more than 10 unique items (excluding VasItems).")
    public void add_11_item_to_chart() {
        Item defaultItem = new DefaultItem(1, 100, 200, 1, 1);

        Item defaultItem2 = new DefaultItem(2, 100, 200, 1, 1);

        Item defaultItem3 = new DefaultItem(3, 100, 200, 1, 1);

        Item defaultItem4 = new DefaultItem(4, 100, 200, 1, 1);

        Item defaultItem5 = new DefaultItem(5, 100, 200, 1, 1);

        Item defaultItem6 = new DefaultItem(6, 100, 200, 1, 1);

        Item defaultItem7 = new DefaultItem(7, 100, 200, 1, 1);

        Item defaultItem8 = new DefaultItem(8, 100, 200, 1, 1);

        Item defaultItem9 = new DefaultItem(9, 100, 200, 1, 1);

        Item defaultItem10 = new DefaultItem(10, 100, 200, 1, 1);

        Item defaultItem11 = new DefaultItem(11, 100, 200, 1, 1);

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


        DefaultItem defaultItem = new DefaultItem(1, 100, 200, 1, 1);
        defaultItem.addVasItem(vasItem);

        DefaultItem defaultItem2 = new DefaultItem(2, 100, 200, 1, 1);
        defaultItem2.addVasItem(vasItem);

        Item defaultItem3 = new DefaultItem(3, 100, 200, 1, 1);

        Item defaultItem4 = new DefaultItem(4, 100, 200, 1, 1);

        Item defaultItem5 = new DefaultItem(5, 100, 200, 1, 1);

        Item defaultItem6 = new DefaultItem(6, 100, 200, 1, 1);

        Item defaultItem7 = new DefaultItem(7, 100, 200, 1, 1);

        Item defaultItem8 = new DefaultItem(8, 100, 200, 1, 1);

        Item defaultItem9 = new DefaultItem(9, 100, 200, 1, 1);

        Item defaultItem10 = new DefaultItem(10, 100, 200, 1, 1);

        Item defaultItem11 = new DefaultItem(11, 100, 200, 1, 1);

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

        DefaultItem defaultItem = new DefaultItem(1, 100, 200, 1, 1);
        defaultItem.addVasItem(vasItem);
        defaultItem.addVasItem(vasItem);
        defaultItem.addVasItem(vasItem);
        defaultItem.addVasItem(vasItem);

        assertThrows(VasItemLimitExceededException.class, () -> defaultItem.addVasItem(vasItem));
    }

    @Test
    @DisplayName("Price of VasItem cannot exceed default item price")
    public void add_vas_item_to_default_item_with_price_exceed_default_item_price() {
        VasItem vasItem = new VasItem(1, 1, 1, 500, 1);

        DefaultItem defaultItem = new DefaultItem(1, 100, 200, 200, 1);


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
        Item defaultItem = new DefaultItem(1, 100, 200, 1, 1);

        cart.addItem(defaultItem);
        boolean result = cart.removeItem(1);
        assertTrue(result);
        assertEquals(0, cart.getItems().size());
    }

    @Test
    @DisplayName("Reset Cart")
    public void reset_cart() throws ChartEmptyException {
        Item defaultItem = new DefaultItem(1, 100, 200, 1, 1);

        cart.addItem(defaultItem);
        cart.resetCart();
        assertEquals(0, cart.getItems().size());
        assertEquals(0, cart.getTotalAmount());
        assertEquals(0, cart.getTotalDiscount());
        assertEquals(0, cart.getAppliedPromotionId());
    }

    @Test
    @DisplayName("Display Cart without VasItem")
    public void display_cart_without_VasItem() throws ChartEmptyException {
        Item defaultItem = new DefaultItem(1, 100, 200, 500, 1);

        cart.addItem(defaultItem);
        cart.displayCart();

        assertEquals(1, cart.getItems().size());
        //content comparison
        assertEquals("total amount: 500.0\n" +
                "total discount: 0.0\n" +
                "applied promotion id: 0\n" +
                "items: 1 100 200 500.0 1 []\n", cart.displayCart());

    }

    @Test
    @DisplayName("Display Cart with VasItem")
    public void display_cart_with_VasItem() throws ChartEmptyException {
        DefaultItem defaultItem = new DefaultItem(1, 100, 200, 500, 1);

        cart.addItem(defaultItem);

        VasItem vasItem = new VasItem(1, 3242, 5003, 200, 1);
        defaultItem.addVasItem(vasItem);

        cart.displayCart();

        assertEquals(1, cart.getItems().size());
        //content comparison
        assertEquals("total amount: 700.0\n" +
                "total discount: 0.0\n" +
                "applied promotion id: 0\n" +
                "items: 1 100 200 500.0 1 [vasItemId: 1 vasCategoryId: 3242 vasSellerId: 5003 price: 200.0 quantity: 1]\n", cart.displayCart());
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
        DefaultItem defaultItem = new DefaultItem(1, 100, 1, 300, 1);

        cart.addItem(defaultItem);

        DefaultItem defaultItem2 = new DefaultItem(2, 3003, 2, 200, 1);

        cart.addItem(defaultItem2);

        DefaultItem defaultItem3 = new DefaultItem(3, 3003, 3, 500, 1);

        cart.addItem(defaultItem3);

        cart.applyPromotions();

        assertEquals(3, cart.getItems().size());
        assertEquals(1232, cart.getAppliedPromotionId());
        assertEquals(250.0, cart.getTotalDiscount());
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


