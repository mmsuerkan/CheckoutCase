package com.project.model.item;

public class DigitalItem extends Item {
    private double price;

    public DigitalItem(int itemId, int categoryId, int sellerId, double price, int quantity) {
        super(itemId, categoryId, sellerId, quantity);
        this.price = price;
    }

    @Override
    public double getTotalPrice() {
        return this.price * quantity;
    }

    @Override
    public int getItemId() {
        return super.getItemId();
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "ty.item ->" + ", \"ItemId\":" + this.getItemId() + ", \"vasCategoryId\": " + this.getCategoryId() + ", \"vasSellerId\": " + this.getSellerId() + ", \"price\": " + this.getPrice() + ", \"quantity\" " + this.getQuantity() + " ";
    }
}

