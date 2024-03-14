package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class appTesting {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
    }

    @Test
    void displayCityTestNull() {
        app.displayCity(null);
    }

    //Unit test to check an invalid city ID returns a null City.
    @Test
    void getNullCityTest() {
        assertNull(app.getCity(4141));
    }

    //This unit test will get check the name of an acquired city is correct using the city ID.
    @Test
    void getCityNameTest() {
        assertEquals("LakeWood", (app.getCity(3938)).getName());
    }

    //Unit test to check the country code matches the city ID retrieved.
    @Test
    void getCityCountryTest() {
        assertEquals("USA", (app.getCity(93357)).getCountryCode());
    }


}
