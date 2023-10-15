package com.project.util;

import com.project.commands.*;
import com.project.dto.Payload;
import com.project.dto.Request;
import com.project.dto.Response;
import com.project.interfaces.CommandStrategy;
import com.project.model.cart.Cart;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonNode;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {


    private static final Map<String, CommandStrategy> commandStrategies = new HashMap<>();

    static {
        // Her komut için ilgili strateji sınıfını eşleştirin
        commandStrategies.put("addItem", new AddItemCommand());
        commandStrategies.put("addVasItemToItem", new AddVasItemToItemCommand());
        commandStrategies.put("removeItem", new RemoveItemCommand());
        commandStrategies.put("resetCart", new ResetCartCommand());
        commandStrategies.put("displayCart", new DisplayCartCommand());
    }

    private CommandProcessor() {
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
        Command command = null;
        Payload payload = new Payload();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonStr);
            String commandStr = jsonNode.get("command").asText();
            command = new Command(commandStr);

            if (jsonNode.has("payload")) {
                JsonNode payloadNode = jsonNode.get("payload");

                payload.setItemId(getIntValue(payloadNode, "itemId"));
                payload.setCategoryId(getIntValue(payloadNode, "categoryId"));
                payload.setSellerId(getIntValue(payloadNode, "sellerId"));
                payload.setPrice(getDoubleValue(payloadNode, "price"));
                payload.setQuantity(getIntValue(payloadNode, "quantity"));
                payload.setVasItemId(getIntValue(payloadNode, "vasItemId"));
                payload.setVasCategoryId(getIntValue(payloadNode, "vasCategoryId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Request(command, payload);
    }

    private static int getIntValue(JsonNode node, String fieldName) {
        return node.has(fieldName) ? node.get(fieldName).asInt() : 0;
    }

    private static double getDoubleValue(JsonNode node, String fieldName) {
        return node.has(fieldName) ? node.get(fieldName).asDouble() : 0.0;
    }
}
