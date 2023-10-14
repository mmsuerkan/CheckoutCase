package com.project.interfaces;

import com.project.exception.ChartEmptyException;
import com.project.model.item.Item;
import com.project.model.promotion.Promotion;

public interface CartInterface {
    boolean addItem(Item item);
    boolean removeItem(int itemId) throws ChartEmptyException;
    void resetCart();
    void applyPromotion(Promotion promotion);
    void displayCart();
}
