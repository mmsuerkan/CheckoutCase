package com.project.util;

import com.project.dto.Payload;
import com.project.model.item.DefaultItem;
import com.project.model.item.DigitalItem;
import com.project.model.item.Item;
import com.project.model.item.VasItem;

public class ItemFactory {

    private ItemFactory() {
    }


    public static Item getItem(Payload payload) {
        if (payload.getVasCategoryId() == 3242) {
            return new VasItem(payload.getItemId(), payload.getCategoryId(), payload.getSellerId(),payload.getPrice(), payload.getQuantity());
        } else if (payload.getCategoryId() == 7889) {
            return new DigitalItem(payload.getItemId(), payload.getCategoryId(), payload.getSellerId(), payload.getPrice(), payload.getQuantity());
        }else{
            return new DefaultItem(payload.getItemId(), payload.getCategoryId(), payload.getSellerId(), payload.getPrice(), payload.getQuantity());
        }
    }
}
