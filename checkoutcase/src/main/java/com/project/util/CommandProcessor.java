package com.project.util;

import com.project.commands.*;
import com.project.dto.Payload;
import com.project.dto.Request;
import com.project.dto.Response;
import com.project.interfaces.CommandStrategy;
import com.project.model.cart.Cart;
import com.project.model.item.Item;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonNode;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandProcessor {

    private static Command command;
    private static Payload payload;


    private static Map<String, CommandStrategy> commandStrategies = new HashMap<>();

    static {
        // Her komut için ilgili strateji sınıfını eşleştirin
        commandStrategies.put("addItem", new AddItemCommand());
        commandStrategies.put("addVasItemToItem", new AddVasItemToItemCommand());
        commandStrategies.put("removeItem", new RemoveItemCommand());
        commandStrategies.put("resetCart", new ResetCartCommand());
        commandStrategies.put("displayCart", new DisplayCartCommand());
    }


    public static Response handleCommand(Request request, Cart cart) {

        CommandStrategy strategy = commandStrategies.get(request.getCommand().getName());
        if (strategy != null) {
            return strategy.handleCommand(request.getPayload(), cart);
        } else {
            return new Response(false, "Invalid command");
        }
    }

    public static Request processCommand(String jsonStr) {


        double price = 0.0;
        int itemId = 0;
        int categoryId = 0;
        int sellerId = 0;
        int quantity = 0;
        int vasItemId = 0;
        int vasCategoryId = 0;

        try {

            // Jackson ObjectMapper oluştur
            ObjectMapper objectMapper = new ObjectMapper();

            // JSON stringini JsonNode'a parse et
            JsonNode jsonNode = objectMapper.readTree(jsonStr);

            // İstenilen verilere erişim
            String commandStr = jsonNode.get("command").asText();


            if (jsonNode.has("payload")) {
                JsonNode payloadNode = jsonNode.get("payload");
                if (payloadNode.has("itemId")) {
                    itemId = payloadNode.get("itemId").asInt();
                }
                if (payloadNode.has("categoryId")) {
                    categoryId = payloadNode.get("categoryId").asInt();
                }

                if (payloadNode.has("sellerId")) {
                    sellerId = payloadNode.get("sellerId").asInt();
                }

                if (payloadNode.has("price")) {
                    price = payloadNode.get("price").asDouble();
                }

                if (payloadNode.has("quantity")) {
                    quantity = payloadNode.get("quantity").asInt();
                }
                if (payloadNode.has("vasItemId")) {
                    vasItemId = payloadNode.get("vasItemId").asInt();
                }
                if (payloadNode.has("vasCategoryId")) {
                    vasCategoryId = payloadNode.get("vasCategoryId").asInt();
                }
            }

            command = new Command(commandStr);
            payload = new Payload();
            payload.setItemId(itemId);
            payload.setCategoryId(categoryId);
            payload.setSellerId(sellerId);
            payload.setPrice(price);
            payload.setQuantity(quantity);
            payload.setVasItemId(vasItemId);
            payload.setVasCategoryId(vasCategoryId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Request(command, payload);
    }
}
