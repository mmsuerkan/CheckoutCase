package com.project.commands;

import com.project.model.cart.Cart;
import com.project.model.item.Item;
import com.project.interfaces.CommandStrategy;
import com.project.dto.Payload;
import com.project.dto.Response;

import java.util.List;

public class RemoveItemCommand implements CommandStrategy {
    @Override
    public Response handleCommand(Payload payload, Cart cart) {
        return new Response(true, "Item removed from cart");
    }
}
