package com.example.model.responses;

public class ResponseObject <T>{

    T data;
    String message = "CodingNomads";
    String error;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}