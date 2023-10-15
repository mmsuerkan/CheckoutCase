package com.project.commands;

import com.project.model.cart.Cart;
import com.project.model.item.Item;
import com.project.interfaces.CommandStrategy;
import com.project.dto.Payload;
import com.project.dto.Response;

import java.util.List;

public class AddItemCommand implements CommandStrategy {
    @Override
    public Response handleCommand(Payload payload, Cart cart) {
        Item item = ItemFactory.getItem(payload);
       try{
           cart.addItem(item);
           return new Response(true, "Item added successfully");
       }catch (Exception e){
           return new Response(false, "Item added failed");
       }
    }
}
