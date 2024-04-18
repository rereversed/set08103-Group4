package com.napier.sem;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LanguageTest {
    static App a; //initialising app class
    static Language f; //initialising Language class
    Connection con = null;

    @BeforeAll
    public static void init() {
        //instantiating before test methods
        a = new App();
        f = new Language();
    }

    @Test

    //Tests that an exception is thrown when using getLanguageSpeakersPopulation function
    //with a NULL connection
    void testGetLanguageSpeakersPopulation()
        throws SQLException{ f.getLanguageSpeakersPopulation(con);
    }
}
