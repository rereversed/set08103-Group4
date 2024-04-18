package com.napier.sem;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContinentTest {
    static App app;
    static Continent c;
    public static Connection con = null;

    @BeforeAll
    public static void init() {
        //instantiating before test methods
        app = new App();
        c = new Continent();
        app.connect();
    }


    @Test
    void getCountriesByContinentPopulation()
            throws SQLException { c.getCountriesByContinentPopulation(con, "invalid-continent");}

    @Test
    void getTopNCountriesByContinentPopulation()
        throws SQLException { c.getTopNCountriesByContinentPopulation(con, -1,"Europe");}

    @Test
    void getCitiesByContinentPopulation()
        throws SQLException { c.getCitiesByContinentPopulation(con, "invalid-continent");}

    @Test
    void getTopNCitiesByContinentPopulation()
        throws SQLException { c.getTopNCitiesByContinentPopulation(con, -1, "Asia");}

    @Test
    void getCapitalCitiesByContinentPopulation()
        throws SQLException { c.getCapitalCitiesByContinentPopulation(con, "invalid-country");}

    @Test
    void getTopNCapitalCitiesByContinentPopulation()
        throws SQLException { c.getTopNCapitalCitiesByContinentPopulation(con, -1, "Europe");}

    @Test
    void getPopulationDistributionByContinent()
            throws SQLException { c.getPopulationDistributionByContinent(con);}

    @Test
    void getContinentPopulation()
            throws SQLException { c.getContinentPopulation(con, "invalid-country");}




}
