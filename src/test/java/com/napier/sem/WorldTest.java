package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WorldTest {
    private Connection con;
    private World world;

    @BeforeAll
    public void init() {
        world = new World();
        con = establishConnection();
        assertNotNull(con, "DB connection should not be null");  // Ensure the connection is not null
    }

    @AfterAll
    public void close() {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection establishConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Ensure the connection URL uses the service name `db` and the correct password
            return DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
        } catch (ClassNotFoundException | SQLException e) {
            fail("Database connection failed: " + e.getMessage());
            return null;
        }
    }


    @Test
    void testGetCountriesByPopulation() {
        assertDoesNotThrow(() -> world.getCountriesByPopulation(con),
                "Method getCountriesByPopulation should not throw an exception");
    }

    @Test
    void testGetTopNCountriesByPopulation() {
        assertDoesNotThrow(() -> world.getTopNCountriesByPopulation(con, 5),
                "Method getTopNCountriesByPopulation should not throw an exception");
    }

    @Test
    void testGetCitiesByPopulation() {
        assertDoesNotThrow(() -> world.getCitiesByPopulation(con),
                "Method getCitiesByPopulation should not throw an exception");
    }

    @Test
    void testGetTopNCitiesByPopulation() {
        assertDoesNotThrow(() -> world.getTopNCitiesByPopulation(con, 3),
                "Method getTopNCitiesByPopulation should not throw an exception");
    }

    @Test
    void testGetCapitalCitiesByPopulation() {
        assertDoesNotThrow(() -> world.getCapitalCitiesByPopulation(con),
                "Method getCapitalCitiesByPopulation should not throw an exception");
    }

    @Test
    void testGetTopNCapitalCitiesByPopulation() {
        assertDoesNotThrow(() -> world.getTopNCapitalCitiesByPopulation(con, 2),
                "Method getTopNCapitalCitiesByPopulation should not throw an exception");
    }

    @Test
    void testGetWorldPopulation() {
        assertDoesNotThrow(() -> world.getWorldPopulation(con),
                "Method getWorldPopulation should not throw an exception");
    }
}
