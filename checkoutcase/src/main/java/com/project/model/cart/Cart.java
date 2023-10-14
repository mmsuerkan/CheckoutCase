package com.project.model.cart;

import com.project.interfaces.CartInterface;
import com.project.model.item.Item;
import com.project.model.promotion.Promotion;


import java.util.ArrayList;
import java.util.List;

public class Cart implements CartInterface {
    private int cartId;
    private List<Item> items;
    private double totalAmount;
    private double totalDiscount;
    private int appliedPromotionId;

    public Cart() {
        items = new ArrayList<>();
    }

    @Override
    public boolean addItem(Item item) {
        return false;
    }

    @Override
    public boolean removeItem(int itemId) {

        return false;
    }

    @Override
    public void resetCart() {

    }

    @Override
    public void applyPromotion(Promotion promotion) {

    }

    @Override
    public void displayCart() {

    }

    private int getTotalQuantity() {
        return items.stream().mapToInt(Item::getQuantity).sum();
    }

    private double getTotalAmount() {
        return items.stream().mapToDouble(Item::getTotalPrice).sum();
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public int getAppliedPromotionId() {
        return appliedPromotionId;
    }

    public void setAppliedPromotionId(int appliedPromotionId) {
        this.appliedPromotionId = appliedPromotionId;
    }
}
