package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Country {
    public String code;
    public String name;
    public String continent;
    public String region;
    public int population;
    public Integer capital;

    /**
     * Find specified number of Regions in a Country sorted by the highest Population
     * @param con the database connection
     * @param region the region to search for
     * @param N the limit of database entries to retrieve
     * @return ResultSet
     */
    public ResultSet getTopRegionDescending(Connection con, String region, int N) {
        try {
            if (region == null) {
                System.out.println("region name is null.");
                return null;
            }
            if (N < 0)
            {
                System.out.println("N Cannot be negative");
                N = 1;
            }
            if (con == null) {
                System.out.println("Connection is null.");
                return null;
            }
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.continent, country.region, country.population, country.capital " +
                            "FROM country " +
                            "WHERE country.region = '" + region + "' " +
                            "ORDER BY country.population DESC " +
                            "LIMIT " + N;


            // Execute SQL statement and return ResultSet
            return stmt.executeQuery(strSelect);
        } catch (Exception e) {
            System.out.println("Failed to get country details");
            return null;
        }
    }


    /**
     * Retrieves the top 'N' countries, on the current
     * connection to the database, and organizes them by size descending.
     *
     * @param con the database connection
     * @param N the number of top countries to retrieve
     * @return a ResultSet containing the top 'N' countries
     */

   // public ResultSet topNPopulatedCountries(Connection con, int N) {}

    /**
     * Retrieves all countries organised by size descending on the current connection to the database.
     * @param con the database connection
     * @return a resultSet containing all countries organised by size descending
     */
/**
    public ResultSet getCountryDescending(Connection con) {

    }

    /**
     * Find Countries in Continent Sorted by the highest population
     * @param con the database connection
     * @param continent_name the continent to search through
     * @return ResultSet
     */
    //public ResultSet getContinentDescending(Connection con, String continent_name) {}

    /**
     *
     * @param con the connection to the database
     * @param continentName the continent of which the function searches through
     * @param N the number of top populated countries to display
     * @return the resultset containing the top N countries in the selected continent  */
    //public ResultSet topNPopulatedCountriesContinent(Connection con, String continentName, int N){}

   // public ResultSet countryRegionDescending(Connection con, String region) {}

    /**
     * Displays the contents of ResultSet for country functions
     * @param resultSet containing country details from other method calls
     */
    //public void displayCountries(ResultSet resultSet) {}

}

