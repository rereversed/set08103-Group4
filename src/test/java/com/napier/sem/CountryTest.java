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

    public static Connection con = null;

    @BeforeAll
    public static void init() {
        //instantiating before test methods
        a = new App();
        c = new Country();
    }

    @Test
    void testGetCountryPopulation_invalidName()
            throws SQLException { c.getCountryPopulation(con, "invalid-country");
    }

    @Test
    void testGetCitiesByCountryPopulation_nullConnection()
            throws SQLException { c.getCitiesByCountryPopulation(con, "China");
    }

    @Test
    void testGetTopNCitiesByCountryPopulation_invalidN()
            throws SQLException { c.getTopNCitiesByCountryPopulation(con, -1, "Germany");
    }

    @Test
    void testGetTopNCitiesByDistrictPopulation_invalidN()
            throws SQLException { c.getTopNCitiesByDistrictPopulation(con, -1, "Belgium");
    }

    @Test
    void testGetPopulationDistributionByCountry_invalidName()
            throws SQLException { c.getPopulationDistributionByCountry(con, "invalid-country");
    }

    @Test
    void testGetDistrictPopulation_invalidName()
            throws SQLException { c.getDistrictPopulation(con, "invalid-country");
    }

    @Test
    void testGetCityPopulation_invalidName()
            throws SQLException { c.getCityPopulation(con, "invalid-country");
    }

    @Test
    void testGetCitiesByDistrictPopulation_invalidName()
            throws SQLException { c.getCitiesByDistrictPopulation(con, "invalid-country");
    }
}
