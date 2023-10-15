package com.project.model.cart;

import com.project.exception.*;
import com.project.interfaces.CartInterface;
import com.project.model.item.DefaultItem;
import com.project.model.item.Item;
import com.project.model.item.VasItem;
import com.project.service.PromotionService;

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

        if (item.getQuantity() > 10) {
            throw new ItemQuantityExceededException("Item quantity cannot exceed 10.");
        }

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

    public static boolean isVasItem(Item item) {
        return item instanceof VasItem;
    }

    @Override
    public boolean removeItem(int itemId) throws ChartEmptyException {

        if (items.isEmpty()) {
            throw new ChartEmptyException("Chart is empty. You cannot remove any item.");
        }

        for (Item item : items) {
            if (item.getItemId() == itemId) {
                if (item instanceof DefaultItem) {
                    ((DefaultItem) item).getVasItems().clear();
                }
                items.remove(item);
                return true;
            }
        }
        return false;
    }

    @Override
    public String resetCart() {
        try {
            if (items.isEmpty()) {
                throw new ChartEmptyException("Chart is empty. You cannot reset the cart.");
            }
            if (items.stream().anyMatch(DefaultItem.class::isInstance)) {
                items.stream().filter(DefaultItem.class::isInstance).forEach(item -> ((DefaultItem) item).getVasItems().clear());
            }
            this.items.clear();
            this.totalAmount = 0;
            this.totalDiscount = 0;
            this.appliedPromotionId = 0;
            return "Cart reset successfully";
        } catch (ChartEmptyException e) {
            return e.getMessage();
        }
    }

    @Override
    public String applyPromotions() {
        PromotionService.applyBestPromotion(this);
        return "Promotion applied successfully";
    }

    @Override
    public String displayCart() {
        StringBuilder result = new StringBuilder();

        result.append("items\": ").append(this.getItems().size()).append(" ")
                .append(", \"totalAmount\": ").append(getTotalAmount()).append(" ")
                .append(", \"appliedPromotionId\": ").append(getAppliedPromotionId()).append(" ")
                .append(", \"totalDiscount\": ").append(getTotalDiscount()).append("}}");

        //Print both item and if item is default item print vas items
        for (Item item : items) {
            result.append(item.toString());
        }

        return result.toString();
    }

    private int getTotalQuantity() {
        return items.stream().mapToInt(Item::getQuantity).sum();
    }

    public double getTotalAmount() {
        totalAmount = items.stream().mapToDouble(Item::getTotalPrice).sum();
        return totalAmount;
    }

    public Item getItemById(int itemId) {
        for (Item item : items) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }
        return null;
    }


    public List<Item> getItems() {
        return items;
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
