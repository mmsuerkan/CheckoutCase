package com.project.interfaces;

import com.project.model.item.Item;
import com.project.model.promotion.Promotion;

public interface CartInterface {
    boolean addItem(Item item);
    boolean removeItem(int itemId);
    void resetCart();
    void applyPromotion(Promotion promotion);
    void displayCart();
}
