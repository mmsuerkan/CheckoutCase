package com.project.service;

import com.project.model.cart.Cart;
import com.project.model.promotion.Promotion;
import com.project.repository.PromotionRepository;
import com.project.model.promotion.CategoryPromotion;
import com.project.model.promotion.SameSellerPromotion;
import com.project.model.promotion.TotalPricePromotion;

public class PromotionService {

    PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public static void applyBestPromotion(Cart cart) {
        double maxDiscount = 0.0;
        Promotion bestPromotion = null;

        for (Promotion promotion : PromotionRepository.getPromotions()) {
            double discount = 0.0;

            if (promotion.getPromotionId() == 9909) {
                discount = SameSellerPromotion.apply(cart);
            }
            if (promotion.getPromotionId() == 5676) {
                discount = CategoryPromotion.apply(cart);
            }
            if (promotion.getPromotionId() == 1232) {
                discount = TotalPricePromotion.apply(cart);
            }

            if (discount > maxDiscount) {
                maxDiscount = discount;
                bestPromotion = promotion;
            }
        }

        if (bestPromotion != null) {
            cart.setAppliedPromotionId(bestPromotion.getPromotionId());
            cart.setTotalDiscount(maxDiscount);
        } else {
            cart.setAppliedPromotionId(0);
            cart.setTotalDiscount(0.0);
        }
    }
}
