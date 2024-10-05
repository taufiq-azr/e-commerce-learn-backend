package com.azharstudios.e_commerce_learn_backend.exception;

public class NotFoundException extends RuntimeException  {
    public NotFoundException(String message) {
        super(message);
    }
}
