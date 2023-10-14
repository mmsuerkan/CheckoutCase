package com.project.model.promotion;

public class Promotion {
    private int promotionId;
    private String promotionName;
    private double discountAmount;

    public Promotion(int promotionId, String promotionName) {
        this.promotionId = promotionId;
        this.promotionName = promotionName;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }
}
