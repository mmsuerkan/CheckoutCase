package com.project.model.item;

public class DigitalItem extends Item {
    private double price;

    public DigitalItem(int itemId, int sellerId, double price, int quantity) {
        super(itemId, 7889, sellerId, quantity);
        this.price = price;
    }

    @Override
    public double getTotalPrice() {
        return price * quantity;
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
}

