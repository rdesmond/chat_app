package com.example.model.internal;

import java.util.Date;

public class Chat {

    int id;
    String chat_title;
    Date created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChat_title() {
        return chat_title;
    }

    public void setChat_title(String chat_title) {
        this.chat_title = chat_title;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
