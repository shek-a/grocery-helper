package com.store.groceryhelper.exception;

public class GroceryNotFoundException extends RuntimeException {

    public GroceryNotFoundException(String id) {
        super(String.format("id of %s is not found", id));
    }
}
