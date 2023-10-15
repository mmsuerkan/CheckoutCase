package com.project.interfaces;

import com.project.model.cart.Cart;
import com.project.model.item.Item;
import com.project.dto.Payload;
import com.project.dto.Response;

import java.util.List;

public interface CommandStrategy {
    Response handleCommand(Payload payload, Cart cart);
}
