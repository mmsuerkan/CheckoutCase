package com.project.model.item;

public class VasItem extends Item {
    private double price;
    private int quantity;

    public VasItem(int itemId, int categoryId, int sellerId, double price, int quantity) {
        super(itemId, 3242, 5003, quantity);
        this.price = price;
        this.quantity = quantity;
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
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
