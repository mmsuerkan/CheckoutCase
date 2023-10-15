package com.project.dto;

public class Response {

    private boolean result;
    private String message;

    public Response() {
    }

    public Response(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public boolean getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }


    @Override
    public String toString() {
        return "{\"result\":"+result+", \"message\": \""+message+"}";
    }
}
