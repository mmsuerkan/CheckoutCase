package com.project.model.item;

public class DigitalItem extends Item {
    private double price;

    public DigitalItem(int itemId, int sellerId,int quantity) {
        super(itemId, 7889, sellerId,quantity);
        this.price = price;

    }

    @Override
    public double getTotalPrice() {
        return price * quantity;
    }



    @Override
    public int getItemId() {
        return this.getItemId();
    }
}

