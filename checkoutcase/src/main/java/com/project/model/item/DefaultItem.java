package com.project.model.item;

import com.project.exception.VasItemLimitExceededException;
import com.project.exception.VasItemPriceExceededException;

import java.util.ArrayList;
import java.util.List;

public class DefaultItem extends Item {
    private List<VasItem> vasItems;

    public DefaultItem(int itemId, int categoryId, int sellerId, int quantity) {
        super(itemId, categoryId, sellerId, quantity);
        this.vasItems = new ArrayList<>();
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
        return this.price + vasItemsTotalPrice;
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
}
