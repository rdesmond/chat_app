package com.example.model.internal;

public class ChatPost {
    int id;
    int sender_id;
    int recipient_id;
    int chat_id;
    String message;

    public int getSender_id() {
        return sender_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(int recipient_id) {
        this.recipient_id = recipient_id;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
