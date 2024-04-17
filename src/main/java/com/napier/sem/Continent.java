package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Continent {
    public void getCountriesByContinentPopulation(Connection con) {
    }

    public void getTopNCountriesByContinentPopulation(Connection con, int n) {
    }

    public void getCitiesByContinentPopulation(Connection con) {
    }

    public void getTopNCitiesByContinentPopulation(Connection con, int n) {
    }

    public void getCapitalCitiesByContinentPopulation(Connection con) {
    }

    public void getTopNCapitalCitiesByContinentPopulation(Connection con, int n) {
    }

    public void getPopulationDistributionByContinent(Connection con) {

        //If statement to verify connection to database is successful and in-use.
        if (con == null) {
            System.out.println("Connection is null.");
            return;  // Exit the method if there is no connection
        }
        // SQL Query in order to get population distribution for each continent.
        String query =
                "SELECT country.Continent AS continent_name, " +
                "SUM(country.population) AS continent_population, " +
                "SUM(city.population) AS urban_population, " +
                "(SUM(country.population) - SUM(city.population)) AS rural_population " +
                "FROM country " +
                "INNER JOIN city ON city.Countrycode = country.Code " +
                "GROUP BY continent_name";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            //While loop retrieves requested data until it reaches the end of the database.
            while (rs.next()) {
                String countryName = rs.getString("continent_name");
                long continentTotal = rs.getLong("continent_population");
                long urbanTotal = rs.getLong("urban_population");
                long ruralTotal = rs.getLong("rural_population");
                System.out.println("CONTINENT: " + countryName + "\nCONTINENT POPULATION: "+ continentTotal
                        + "\nURBAN POPULATION: " + urbanTotal + "\nRURAL POPULATION: " + ruralTotal + "\n");
            }

            //Catch statement for any errors in database or sql query.
        } catch (SQLException e) {
            System.out.println("Failed to get Continent Population details: " + e.getMessage());
        }
    }

    public void getContinentPopulation(Connection con) {
    }
}
