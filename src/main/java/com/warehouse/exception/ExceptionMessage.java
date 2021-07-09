package com.warehouse.exception;

public class ExceptionMessage {
    private int productId;
    private String message;

    public ExceptionMessage() { }

    public ExceptionMessage(int productId, String message) {
        this.productId = productId;
        this.message = message;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
