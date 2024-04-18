package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.napier.sem.App.scanner;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegionTest {
    private Connection con;
    private Region region;

    @BeforeAll
    public void init() {
        region = new Region();
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

    public String getUserRegion (){
        System.out.println(("Please Input the Region "));
        String input = scanner.nextLine();
        try {
            String region = input;
            if (region != null){
                return region;
            } else {
                System.out.println("Error: Please enter a Region");
            }
        }   catch (Exception E){
            System.out.println(" Error: Invalid Input");
        }
        return null;
    }

    @Test
    void testgetCountriesByRegionPopulation() {
        assertDoesNotThrow(() -> region.getCountriesByRegionPopulation(con, "Polynesia"),
                "Method getCountriesByRegionPopulation should not throw an exception");
    }

    @Test
    void testgetTopNCountriesByRegionPopulation() {
        assertDoesNotThrow(() -> region.getTopNCountriesByRegionPopulation(con, 5, "Polynesia"),
                "Method getTopNCountriesByRegionPopulation should not throw an exception");
    }

    @Test
    void testgetCitiesByRegionPopulation() {
        assertDoesNotThrow(() -> region.getCitiesByRegionPopulation(con, "Western Europe"),
                "Method getCitiesByRegionPopulation should not throw an exception");
    }

    @Test
    void testgetTopNCitiesByRegionPopulation() {
        assertDoesNotThrow(() -> region.getTopNCitiesByRegionPopulation(con, 3, "Middle East"),
                "Method getTopNCitiesByRegionPopulation should not throw an exception");
    }

    @Test
    void testgetCapitalCitiesByRegionPopulation() {
        assertDoesNotThrow(() -> region.getCapitalCitiesByRegionPopulation(con, "Middle East"),
                "Method getCapitalCitiesByRegionPopulation should not throw an exception");
    }

    @Test
    void testgetTopNCapitalCitiesByRegionPopulation() {
        assertDoesNotThrow(() -> region.getTopNCapitalCitiesByRegionPopulation(con, 2, "Eastern Asia"),
                "Method getTopNCapitalCitiesByRegionPopulation should not throw an exception");
    }

    @Test
    void testgetPopulationDistributionByRegion() {
        assertDoesNotThrow(() -> region.getPopulationDistributionByRegion(con, "Middle East"),
                "Method getPopulationDistributionByRegion should not throw an exception");
    } @Test
    void testgetRegionPopulation() {
        assertDoesNotThrow(() -> region.getRegionPopulation(con, "Middle East"),
                "Method getRegionPopulation should not throw an exception");
    }

}
