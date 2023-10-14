package com.project.model.promotion;

import com.project.model.cart.Cart;

public class TotalPricePromotion extends Promotion {
    public TotalPricePromotion(String promotionType) {
        super(1232, promotionType);
    }

    public static double apply(Cart cart) {
        double totalPrice = cart.getTotalAmount();
        double discount = 0;

        if (totalPrice >= 500 && totalPrice < 5000) {
            discount = 250;
        } else if (totalPrice >= 5000 && totalPrice < 10000) {
            discount = 500;
        } else if (totalPrice >= 10000 && totalPrice < 50000) {
            discount = 1000;
        } else if (totalPrice >= 50000) {
            discount = 2000;
        }

        return discount;
    }
}
