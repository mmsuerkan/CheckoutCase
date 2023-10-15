package com.project.model.item;

import com.project.exception.VasItemLimitExceededException;
import com.project.exception.VasItemPriceExceededException;

import java.util.ArrayList;
import java.util.List;

public class DefaultItem extends Item {
    private List<VasItem> vasItems;

    public DefaultItem(int itemId, int categoryId, int sellerId, double price, int quantity) {
        super(itemId, categoryId, sellerId, quantity);
        this.vasItems = new ArrayList<>();
        this.price = price;
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
    public double getTotalPrice() {
        double vasItemsTotalPrice = 0;
        for (VasItem vasItem : vasItems) {
            vasItemsTotalPrice += vasItem.getTotalPrice();
        }
        return (this.price * this.getQuantity()) + vasItemsTotalPrice;
    }

    public boolean addVasItem(VasItem vasItem) {
        if (vasItems.size() > 3) {
          throw new VasItemLimitExceededException("Vas Item limit exceeded");
        }
        if(vasItem.getPrice() > this.getPrice()){
            throw new VasItemPriceExceededException("Vas Item price exceeded");
        }
        vasItems.add(vasItem);
        return true;
    }


    public List<VasItem> getVasItems() {
        return this.vasItems;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        if (!vasItems.isEmpty()) {
            result.append(", \"vasItems\": " + vasItems.size() + "}");
            for (VasItem vasItem : vasItems) {
                result.append(" \nty.vasItem -> {").append(vasItem.toString());
            }
        }
        return result.toString() ;
    }
}
