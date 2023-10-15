package com.project;

import com.project.dto.Request;
import com.project.dto.Response;
import com.project.model.cart.Cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.project.util.CommandProcessor.handleCommand;
import static com.project.util.CommandProcessor.processCommand;
import static com.project.util.FileProcessor.readRequestLinesFromFile;
import static com.project.util.FileProcessor.writeResponsesToFile;

public class ShoppingCartApp {

    private static List<String> requestLines;
    public static final List<Response> responses = new ArrayList<>();

    static Cart cart = new Cart();
    public static void main(String[] args){
        try {
            requestLines = readRequestLinesFromFile("checkoutcase/src/main/java/com/project/files/input.txt");

            for (String requestLine : requestLines) {
                Request request = processCommand(requestLine);
                Response response = handleCommand(request,cart);

                if (response != null) responses.add(response);
            }

            writeResponsesToFile(responses, "checkoutcase/src/main/java/com/project/files/output.txt");
            System.out.println("Checkout completed successfully.");
        }catch (Exception e){
            System.out.println("Checkout failed.");
        }
    }
}