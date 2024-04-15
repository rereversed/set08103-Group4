package com.napier.sem;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CountryTest {
    static App a; //initialising app class
    static Country c; //initialising country class

    @BeforeAll
    public static void init() {
        //instantiating before test methods
        a = new App();
        c = new Country();
    }
}
