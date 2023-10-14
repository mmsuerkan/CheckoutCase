package com.project.model.promotion;

import com.project.model.cart.Cart;
import com.project.model.item.Item;

import static com.project.model.cart.Cart.isVasItem;

public class SameSellerPromotion extends Promotion {

    protected int discountPercentage;

    public SameSellerPromotion(String promotionName) {
        super(9909, promotionName);
        this.discountPercentage = 10;
    }

    public static double apply(Cart cart) {

        int sellerId = -1;
        double discount = 0.0;
        for (Item item : cart.getItems()) {
            if (!isVasItem(item)) {
                if (sellerId == -1) {
                    sellerId = item.getSellerId();
                } else if (sellerId != item.getSellerId()) {
                    return -1;
                }
            }
        }

        // Eğer tüm ürünler aynı satıcıya aitse, indirimi uygulayın
        if (sellerId != -1) {
             discount = cart.getTotalAmount() * 0.10;
        }
        return discount;
    }
}
