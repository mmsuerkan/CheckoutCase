package com.project.commands;

import com.project.dto.Payload;
import com.project.dto.Response;
import com.project.exception.ChartEmptyException;
import com.project.interfaces.CommandStrategy;
import com.project.model.cart.Cart;

public class RemoveItemCommand implements CommandStrategy {
    @Override
    public Response handleCommand(Payload payload, Cart cart) {
        try{
            return new Response(cart.removeItem(payload.getItemId()),"Item removed successfully.");
        } catch (ChartEmptyException e) {
            return new Response(false, e.getMessage());
        }
    }
}
