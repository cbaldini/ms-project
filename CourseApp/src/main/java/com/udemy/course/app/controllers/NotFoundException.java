package com.udemy.course.app.controllers;

import java.math.BigInteger;

public class NotFoundException extends RuntimeException {

    public NotFoundException(BigInteger id) {
        super("Id not found: " + id);
    }

}