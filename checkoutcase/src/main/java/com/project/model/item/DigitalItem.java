package com.project.model.item;

public class DigitalItem extends Item {

    public DigitalItem(int itemId, int categoryId, int sellerId, double price, int quantity) {
        super(itemId, categoryId, sellerId, quantity);
        this.price = price;
    }

    @Override
    public double getTotalPrice() {
        return this.price * quantity;
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
        return super.toString();
    }
}

