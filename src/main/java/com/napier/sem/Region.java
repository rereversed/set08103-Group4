package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Region {
    int population;

    public void getCountriesByRegionPopulation(Connection con, String name) {
        if (con == null) {
            System.out.println("Connection is Null");
            return;
        }

        String query = "SELECT Name AS country_name, Region, Population " +
                "FROM country " +
                "WHERE Region =  '" + name + "'" +
                "ORDER BY Population DESC;";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String countryName = rs.getString("country_name");
                String region = rs.getString("Region");
                int population = rs.getInt("Population");

                System.out.println("Country: " + countryName + ", Region: " + region + ", Population: " + population);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get Details " + e.getMessage());
        }

    }

    public void getTopNCountriesByRegionPopulation(Connection con, int n, String name) {
        if (con == null) {
            System.out.println("Connection is Null");
            return;
        }
        if (n < 1) {
            System.out.println("N must be at least 1 ");
            n = 1;
        }

        String query = "SELECT Name AS country_name, Population " +
                "FROM country " +
                "WHERE Region = '" + name + "'" +
                "ORDER BY Population DESC " +
                "LIMIT " + n;

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Top " + n + " Populated Countries:");

            while ((rs.next())) {
                String countryName = rs.getString("country_name");
                int population = rs.getInt("Population");

                System.out.println(countryName + "   " + population);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get details " + e.getMessage());
        }
    }

    public void getCitiesByRegionPopulation(Connection con, String name) {
        if (con == null) {
            System.out.println("Connection is Null ");
            return;
        }

        String query = "SELECT city.Name AS city_name, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = '" + name + "'" +
                "ORDER BY city.Population DESC;";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {

                String cityName = rs.getString("city_name");
                int population = rs.getInt("Population");
                System.out.println(cityName + "   " + population);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get details " + e.getMessage());
        }

    }

    public void getTopNCitiesByRegionPopulation(Connection con, int n, String name) {
        if (con == null) {
            System.out.println("Connection is Null");
            return;
        }

        if (n < 1) {
            System.out.println("N must be ast least 1 ");
            n = 1;
        }

        String query = "SELECT city.Name AS city_name, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = '" + name + "'" +
                "ORDER BY city.Population DESC " +
                "LIMIT " + n;

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String cityName = rs.getString("city_name");
                int population = rs.getInt("Population");
                System.out.println(cityName + "    " + population);

            }
        } catch (SQLException e) {
            System.out.println("Failed to get Deatils " + e.getMessage());
        }

    }

    public void getCapitalCitiesByRegionPopulation(Connection con, String name) {
        if (con == null) {
            System.out.println("Connection is Null");
            return;
        }

        String query = "SELECT city.Name AS city_name, country.Name AS country_name, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = '" + name + "'" + " AND city.ID = country.Capital " +
                "ORDER BY city.Population DESC;";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {

                String cityName = rs.getString("city_name");
                String countryName = rs.getString("country_name");
                int population = rs.getInt("Population");

                System.out.printf("%-30s %-30s %-15d\n", cityName, countryName, population);

            }
        } catch (SQLException e) {
            System.out.println("Failed to get details " + e.getMessage());
        }

    }

    public void getTopNCapitalCitiesByRegionPopulation(Connection con, int n, String name) {
        if (con == null) {
            System.out.println("Connection is Null");
            return;
        }

        if (n < 1) {
            System.out.println("N must be greater tha 1");
            n = 1;
        }

        String query = "SELECT * " +
                "FROM city INNER JOIN country ON country.capital = city.ID " +
                " WHERE country.Region = '" + name + "'" +
                " ORDER BY city.population DESC LIMIT " + n;

//

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {

                String cityName = rs.getString("name");
                int population = rs.getInt("population");
                System.out.println(cityName + "  " + population);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get Details " + e.getMessage());
        }


    }

    public void getPopulationDistributionByRegion(Connection con, String region) {
        String countryCode = null;
        int totalPopulation = 0;

        if (con == null) {
            System.out.println("Connection is null");
            return;
        }

        String query = "SELECT * " +
                "FROM country " +
                "WHERE region = '" + region + "'";

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

    public void getRegionPopulation(Connection con, String region) {
        if (con == null) {
            System.out.println("Connection is Null");
            return;
        }


        String query = "SELECT SUM(country.Population) AS totalPopulation FROM country WHERE country.Region = '" + region + "'";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("The region population is: ");

            while ((rs.next())) {

                int population = rs.getInt("totalPopulation");

                System.out.println(region + "   " + population);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get details " + e.getMessage());
        }
    }
}
