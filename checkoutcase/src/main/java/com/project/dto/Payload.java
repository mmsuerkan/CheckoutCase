package com.project.dto;

public class Payload {
    private int itemId;

    private int vasItemId;
    private int categoryId;

    private int vasCategoryId;
    private int sellerId;
    private double price;
    private int quantity;


    public Payload() {
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getVasItemId() {
        return vasItemId;
    }

    public void setVasItemId(int vasItemId) {
        this.vasItemId = vasItemId;
    }

    public int getVasCategoryId() {
        return vasCategoryId;
    }

    public void setVasCategoryId(int vasCategoryId) {
        this.vasCategoryId = vasCategoryId;
    }

    @Override
    public String toString() {
        return "Payload{" + "itemId=" + itemId + ", categoryId=" + categoryId + ", sellerId=" + sellerId + ", price=" + price + ", quantity=" + quantity + '}';
    }

}
