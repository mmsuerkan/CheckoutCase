package com.project.model.item;

import com.project.exception.VasItemLimitExceededException;

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
        vasItems.add(vasItem);
        return true;
    }
}
