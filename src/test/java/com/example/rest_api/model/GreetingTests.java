package com.example.rest_api.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingTests {
    private static Greeting greeting;

    @Test
    public void returnsCorrectObject() {
        greeting = new Greeting(1, "Aye");

        assertEquals(greeting.id(), 1);
        assertEquals(greeting.content(), "Aye");
    }
}
