package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class World {

    public String name;
    public long population;
    public long urbanPopulation;
    public long ruralPopulation;


    public void getWorldPopulation(Connection con) { //26
        /**
        if (con == null) {
            System.out.println("Connection is null.");
            return;  // Exit the method if there is no connection
        }

        String query =
                "SELECT " +
                        "    'Here is your Report:' as name, " +
                        "    SUM(country.population) AS total_population, " +
                        "    SUM(city.population) AS urban_population, " +
                        "    ROUND(SUM(city.population) / SUM(country.population) * 100, 1) AS urban_population_percentage, " +
                        "    SUM(country.population - city.population) AS rural_population, " +
                        "    ROUND(SUM((country.population - city.population)) / SUM(country.population) * 100, 1) AS rural_population_percentage " +
                        "FROM country " +
                        "INNER JOIN city ON city.countryCode = country.code;";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                long totalPopulation = rs.getLong("total_population");
                long urbanPopulation = rs.getLong("urban_population");
                double urbanPopulationPercentage = rs.getDouble("urban_population_percentage");
                long ruralPopulation = rs.getLong("rural_population");
                double ruralPopulationPercentage = rs.getDouble("rural_population_percentage");

                System.out.println(name);
                System.out.println("Total Population: " + totalPopulation);
                System.out.println("Urban Population: " + urbanPopulation + " (" + urbanPopulationPercentage + "%)");
                System.out.println("Rural Population: " + ruralPopulation + " (" + ruralPopulationPercentage + "%)");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get population details: " + e.getMessage());
        }
         */
    }

    public void getCountriesByPopulation(Connection con) {
    }

    public void getTopNCountriesByPopulation(Connection con, int n) {
    }

    public void getCitiesByPopulation(Connection con) {
    }

    public void getTopNCitiesByPopulation(Connection con, int n) {
    }

    public void getCapitalCitiesByPopulation(Connection con) {
    }

    public void getTopNCapitalCitiesByPopulation(Connection con, int n) { //20
        /**
        if (n < 1) {
            System.out.println("N must be at least 1.");
            n = 1;  // Setting to a default value of 1 if the input is invalid
        }
        if (con == null) {
            System.out.println("Database connection is null.");
            return;  // Since it's void, we simply return
        }

        // Use try-with-resources to handle the resources automatically
        try (Statement stmt = con.createStatement()) {
            // Construct SQL query
            String strSelect =
                    "SELECT city.name AS capital, country.name, city.population " +
                            "FROM country " +
                            "INNER JOIN city ON city.countryCode = country.code " +
                            "WHERE country.capital = city.ID " +
                            "ORDER BY city.population DESC " +
                            "LIMIT " + n + ";";

            // Execute SQL statement and process the ResultSet
            ResultSet rs = stmt.executeQuery(strSelect);

            // Example of processing the ResultSet
            while (rs.next()) {
                String capital = rs.getString("capital");
                String countryName = rs.getString("country.name");
                int population = rs.getInt("city.population");
                System.out.println(capital + " in " + countryName + " has a population of " + population);
            }
        } catch (Exception e) {
            System.out.println("Failed to get capital cities by population: " + e.getMessage());
        }
         */
    }

    //next report

}

