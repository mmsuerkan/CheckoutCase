package com.project.model.promotion;

import com.project.model.cart.Cart;
import com.project.model.item.Item;

public class CategoryPromotion extends Promotion {

    protected int discountPercentage;

    public CategoryPromotion(String promotionName) {
        super(5676, promotionName);
        this.discountPercentage = 5;
    }

    public static double apply(Cart cart) {
        double discount = 0.0;
        for (Item item : cart.getItems()) {
            if (!Cart.isVasItem(item) && item.getCategoryId() == 3003) {
                discount = item.getTotalPrice() * 0.05;
            }
        }
        return discount;
    }

}
