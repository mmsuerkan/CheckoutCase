package com.project.model.item;

import com.project.interfaces.ItemInterface;

public abstract class Item implements ItemInterface {

    protected int itemId;
    protected int categoryId;
    protected int sellerId;
    protected int quantity;

    protected double price;

    public Item(int itemId, int categoryId, int sellerId,int quantity) {
        this.itemId = itemId;
        this.categoryId = categoryId;
        this.sellerId = sellerId;
        this.quantity = quantity;
    }
    @Override
    public int getItemId() {
        return itemId;
    }
    @Override
    public int getCategoryId() {
        return categoryId;
    }
    @Override
    public int getSellerId() {
        return sellerId;
    }
    @Override
    public int getQuantity(){
        return quantity;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public abstract void setPrice(double price);
}
