package com.example.th.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EmployeeIdGenerator {

    private static final int ID_LENGTH = 4;

    public String generate() {
        return String.format("%08d", new Random().nextInt(9999) + 1);
    }
}