package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class appTesting {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
    }

    @Test
    void printSalariesTestNull() {
        app.displayCity(null);
    }
}