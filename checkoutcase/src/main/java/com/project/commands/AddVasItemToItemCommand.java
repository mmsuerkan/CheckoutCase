package com.project.commands;

import com.project.exception.ItemAlreadyExistsException;
import com.project.exception.ItemNotUniqueException;
import com.project.model.cart.Cart;
import com.project.model.item.DefaultItem;
import com.project.model.item.Item;
import com.project.interfaces.CommandStrategy;
import com.project.dto.Payload;
import com.project.dto.Response;
import com.project.model.item.VasItem;

import java.util.List;

public class AddVasItemToItemCommand implements CommandStrategy {
    @Override
    public Response handleCommand(Payload payload, Cart cart) {
        VasItem item = (VasItem) ItemFactory.getItem(payload);

        if (item instanceof VasItem) {
            VasItem vasItem = (VasItem) item;

            try {
                DefaultItem parentItem = (DefaultItem) cart.getItemById(payload.getItemId());

                if(parentItem.getVasItems().stream().filter(vasItem1 -> vasItem1.getItemId() == vasItem.getItemId()).count() > 0)
                       throw new ItemAlreadyExistsException("Vas Item already added");
                if (parentItem == null)
                    throw new ItemNotUniqueException("Item not found");
                parentItem.addVasItem(vasItem);
                cart.addItem(vasItem);
                return new Response(true, "Vas Item added successfully");
            } catch (Exception e) {
                return new Response(false, e.getMessage());
            }
        } else {
            return new Response(false, "Item is not a VasItem");
        }
    }
}
