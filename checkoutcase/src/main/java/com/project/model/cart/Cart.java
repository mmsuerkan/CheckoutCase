package com.project.model.cart;

import com.project.exception.CartFullException;
import com.project.exception.ItemNotUniqueException;
import com.project.exception.TotalAmountExceededException;
import com.project.exception.TotalQuantityExceededException;
import com.project.interfaces.CartInterface;
import com.project.model.item.Item;
import com.project.model.item.VasItem;
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
        if (items.size() >= 10) {
            throw new CartFullException("Cart is full. Maximum of 10 unique items allowed.");
        }

        if (getTotalQuantity() + item.getQuantity() > 30) {
            throw new TotalQuantityExceededException("Maximum total quantity of items cannot exceed 30.");
        }

        if (getTotalAmount() + item.getTotalPrice() > 500000) {
            throw new TotalAmountExceededException("Maximum total amount of the Cart cannot exceed 50000 TL.");
        }

        if (!isUniqueItem(item)) {
            throw new ItemNotUniqueException("Item is not unique. Maximum 10 identical items allowed.");
        }

        items.add(item);
        return true;
    }

    private boolean isUniqueItem(Item item) {
        // Benzersizlik kontrolü
        int itemCount = 0;
        for (Item existingItem : items) {
            if (!isVasItem(existingItem) && existingItem != item && existingItem.getItemId() == item.getItemId() && existingItem.getClass() == item.getClass()) {
                itemCount++;
            }
            if (itemCount >= 10) {
                return false;
            }
        }
        return true;
    }

    private boolean isVasItem(Item item) {
        return item instanceof VasItem;
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

    public double getTotalAmount() {
        totalAmount = items.stream().mapToDouble(Item::getTotalPrice).sum();
        return totalAmount;
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
