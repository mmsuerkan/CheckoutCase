package com.project.commands;

import com.project.model.cart.Cart;
import com.project.model.item.Item;
import com.project.interfaces.CommandStrategy;
import com.project.dto.Payload;
import com.project.dto.Response;

import java.util.List;

public class ResetCartCommand implements CommandStrategy {
    @Override
    public Response handleCommand(Payload payload, Cart cart) {
        try {
            return new Response(true, cart.resetCart());
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }
}
