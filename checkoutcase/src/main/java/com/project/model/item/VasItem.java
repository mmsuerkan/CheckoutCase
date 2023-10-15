package com.project.model.item;

public class VasItem extends Item {

    public VasItem(int itemId, int categoryId, int sellerId, double price, int quantity) {
        super(itemId, categoryId, sellerId, quantity);
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public double getTotalPrice() {
        return this.price * quantity;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "\"vasItemId\": " + this.getItemId() + ", \"vasCategoryId\": " + this.getCategoryId() + ", \"vasSellerId\": " + this.getSellerId() + ", \"price\": " + this.getPrice() + ", \"quantity\": " + this.getQuantity() + "}";
    }
}
