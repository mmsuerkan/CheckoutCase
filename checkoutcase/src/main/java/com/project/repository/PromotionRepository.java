package com.project.repository;

import com.project.model.promotion.Promotion;
import com.project.model.promotion.CategoryPromotion;
import com.project.model.promotion.SameSellerPromotion;
import com.project.model.promotion.TotalPricePromotion;

import java.util.List;

public class PromotionRepository {

    public static List<Promotion> getPromotions() {
        return List.of(
            new SameSellerPromotion("SameSellerPromotion"),
            new CategoryPromotion("CategoryPromotion"),
            new TotalPricePromotion( "TotalPricePromotion")
        );
    }
}
