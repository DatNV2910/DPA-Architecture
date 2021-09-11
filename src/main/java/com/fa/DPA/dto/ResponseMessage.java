package com.fa.DPA.dto;

public class ResponseMessage {
    private Boolean isError;
    private String message;

    public ResponseMessage(Boolean isError, String message) {
        this.isError = isError;
        this.message = message;
    }

    public Boolean getError() {
        return isError;
    }

    public void setError(Boolean error) {
        isError = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
