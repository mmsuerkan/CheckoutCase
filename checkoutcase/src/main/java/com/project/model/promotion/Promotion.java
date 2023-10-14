package com.project.model.promotion;

public class Promotion {
    private int promotionId;
    private String promotionType;
    private double discountAmount;

    public Promotion(int promotionId, String promotionType, double discountAmount) {
        this.promotionId = promotionId;
        this.promotionType = promotionType;
        this.discountAmount = discountAmount;
    }

}
