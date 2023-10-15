package com.project.dto;

import com.project.commands.Command;

public class Request {
    private Command command;
    private Payload payload;

    public Request(Command command, Payload payload) {
        this.command = command;
        this.payload = payload;
    }

    public Command getCommand() {
        return command;
    }


    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Request{" +
                "command=" + command +
                ", payload=" + payload +
                '}';
    }

}
