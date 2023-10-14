package com.project.model.item;

import java.util.ArrayList;
import java.util.List;

public class DefaultItem extends Item {
    private List<VasItem> vasItems;

    public DefaultItem(int itemId, int categoryId, int sellerId, int quantity) {
        super(itemId, categoryId, sellerId,quantity);
        this.vasItems = new ArrayList<>(3);
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = 0;
        for (VasItem vasItem : vasItems) {
            totalPrice += vasItem.getTotalPrice();
        }
        return totalPrice;
    }

    public boolean addVasItem(VasItem vasItem) {
        if (vasItem != null) {
            vasItems.add(vasItem);
            return true;
        }
        return false;
    }

    public int getItemId() {
        return super.getItemId();
    }


}
