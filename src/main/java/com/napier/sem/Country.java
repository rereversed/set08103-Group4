package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.stream.StreamSupport;

public class Country {
    public String code;
    public String name;
    public int population;


    public void getCitiesByCountryPopulation(Connection con, String country) {
        if (con == null) {
            System.out.println("Connection is null.");
            return;
        }

        String query = "SELECT * FROM city JOIN country ON city.CountryCode = country.Code WHERE country.name = '" + country + "' ORDER BY city.population DESC";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                String name = rs.getString("name");
                int population = rs.getInt("population");

                System.out.printf("- %s, Population: %d \n", name, population);

            }
        } catch (SQLException e) {
            System.out.println("Failed to get population details: " + e.getMessage());
        }


    }

    public void getCitiesByDistrictPopulation(Connection con, String name) {
        if (con == null) {
            System.out.println("Connection is null");
            return;
        }

        String query = "SELECT Name, Population " +
                "FROM city " +
                "WHERE District = '" + name + "'" +
                "ORDER BY Population DESC";


        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String cityName = rs.getString("Name");
                int population = rs.getInt("Population");

                System.out.println(cityName + "  " + population);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get Population details: " + e.getMessage());
        }

    }

    public void getTopNCitiesByCountryPopulation(Connection con, int n, String country) {
        if (con == null) {
            System.out.println("Connection is null.");
            return;
        }
        if (n < 1) {
            System.out.println("N must be at least 1.");  // Ensuring N is positive
            n = 1;
        }

        String query = "SELECT * " +
                "FROM city INNER JOIN country ON country.Code = city.CountryCode " +
                " WHERE country.name = '" + country
                + "' ORDER BY city.population DESC LIMIT " + n;


        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Top " + n + " Populated Cities in " + country + ":");
            while (rs.next()) {
                String name = rs.getString("name");
                int population = rs.getInt("population");
                System.out.printf("- %s, Population: %d \n", name, population);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get country details: " + e.getMessage());
        }

    }

    public void getTopNCitiesByDistrictPopulation(Connection con, int n, String name) {
        if (con == null) {
            System.out.println("Connection is Null");
            return;
        }
        if (n < 1) {
            System.out.println("N Must be at least 1");
            n = 1;
        }

        String query = "SELECT Name, Population " +
                "FROM city " +
                "WHERE district = '" + name + "'" +
                "ORDER By Population DESC " +
                "LIMIT " + n;

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println(("Top " + n + " " + "Populated Countries:"));

            while (rs.next()) {
                String cityName = rs.getString("name");
                int population = rs.getInt("population");
                System.out.printf("- %s, Population: %d \n", cityName, population);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get Required Details: " + e.getMessage());
        }
    }

    public void getPopulationDistributionByCountry(Connection con, String name) {

        String countryCode = null;
        int totalPopulation = 0;

        if (con == null) {
            System.out.println("Connection is null");
            return;
        }

        String query = "SELECT * " +
                "FROM country " +
                "WHERE name = '" + name + "'";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                totalPopulation = rs.getInt("Population");
                countryCode = rs.getString("code");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get Population Details: " + e.getMessage());
        }


        String query2 = "SELECT SUM(population) AS total_population_in_cities " +
                "FROM city " +
                "WHERE countrycode = '" + countryCode + "'";


        try (Statement stmt2 = con.createStatement();
             ResultSet rs = stmt2.executeQuery(query2)) {

            if (rs.next()) {
                int populationInCities = rs.getInt("total_population_in_cities");
                int populationNotInCities = (totalPopulation - populationInCities);

                System.out.println("Total Population: " + totalPopulation);
                System.out.println("Population in Cities: " + populationInCities);
                System.out.println("Population Not in Cities: " + populationNotInCities);
            }

        } catch (SQLException e) {
            System.out.println("Failed to get Population Deatils: " + e.getMessage());
        }
    }

    public void getCountryPopulation(Connection con, String name) {
        if (con == null) {
            System.out.println("Connection is null.");
            return;
        }

        //SQL Query to get Country's Population.
        String query = "SELECT * " +
                "FROM country " +
                "WHERE name = '" + name + "'";


        //
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                String countryName = rs.getString("Name");
                int population = rs.getInt("Population");

                System.out.println(countryName + "'s Total Population: " + population);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get population details: " + e.getMessage());
        }

    }

    public void getDistrictPopulation(Connection con, String name) {
        if (con == null) {
            System.out.println("Connection is null");
            return;
        }

        String query = "SELECT SUM(Population) AS total_population " +
                "FROM city " +
                "WHERE District = '" + name + "'";


        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                int population = rs.getInt("total_population");

                System.out.println(name);
                System.out.println(population);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get Population Details:" + e.getMessage());
        }
    }

    public void getCityPopulation(Connection con, String name) {
        if (con == null) {
            System.out.println("Connection is null.");
            return;
        }
        String query = "SELECT * " +
                "FROM city " +
                "WHERE Name ='" + name + "'";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                String cityName = rs.getString("Name");
                int population = rs.getInt("Population");

                System.out.println(cityName);
                System.out.println("Total Population " + population);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get population details " + e.getMessage());
        }
    }
}

