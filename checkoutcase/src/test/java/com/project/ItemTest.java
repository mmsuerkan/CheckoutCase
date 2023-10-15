package com.project;

import com.project.exception.ItemQuantityExceededException;
import com.project.exception.VasItemLimitExceededException;
import com.project.exception.VasItemPriceExceededException;
import com.project.model.cart.Cart;
import com.project.model.item.DefaultItem;
import com.project.model.item.Item;
import com.project.model.item.VasItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemTest {

    private Cart cart;

    @Before
    public void setUp() {
        cart = new Cart();
    }

    @Test
    @DisplayName("Maximum quantity of a Item cannot exceed 10")
    public void add_item_with_quantity_11() {
        Item item = new DefaultItem(1, 100, 200, 11, 11);

        assertThrows(ItemQuantityExceededException.class, () -> cart.addItem(item));
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
    public void add_vas_item_to_default_item() {
        VasItem vasItem = new VasItem(1, 1, 1, 500, 1);

        DefaultItem defaultItem = new DefaultItem(1, 100, 200, 200, 1);


        assertThrows(VasItemPriceExceededException.class, () -> defaultItem.addVasItem(vasItem));
    }
}
