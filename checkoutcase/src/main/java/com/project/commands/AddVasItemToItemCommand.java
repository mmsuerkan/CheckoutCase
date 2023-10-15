package com.project.commands;

import com.project.exception.ItemAlreadyExistsException;
import com.project.exception.ItemNotUniqueException;
import com.project.model.cart.Cart;
import com.project.model.item.DefaultItem;
import com.project.interfaces.CommandStrategy;
import com.project.dto.Payload;
import com.project.dto.Response;
import com.project.model.item.VasItem;
import com.project.util.ItemFactory;

public class AddVasItemToItemCommand implements CommandStrategy {
    @Override
    public Response handleCommand(Payload payload, Cart cart) {
        VasItem item = (VasItem) ItemFactory.getItem(payload);

        VasItem vasItem = item;

        try {
            DefaultItem parentItem = (DefaultItem) cart.getItemById(payload.getItemId());

            if(parentItem.getVasItems().stream().anyMatch(vasItem1 -> vasItem1.getItemId() == vasItem.getItemId()))
                   throw new ItemAlreadyExistsException("Vas Item already added");
            parentItem.addVasItem(vasItem);
            return new Response(true, "Vas Item added successfully");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }
}
