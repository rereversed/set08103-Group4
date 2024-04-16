package com.napier.sem;

import java.sql.*;

public class World {

    public String name;

    public void getWorldPopulation(Connection con) { //26

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

    }

    public void getCountriesByPopulation(Connection con) {
        if (con == null) {
            System.out.println("Connection is null.");
            return;  // Exit the method if there is no connection
        }

        String query = "SELECT country.code, country.name, country.continent, country.region, country.population, country.capital " +
                "FROM country " +
                "ORDER BY country.population DESC;";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Countries by Population:");
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String continent = rs.getString("continent");
                String region = rs.getString("region");
                int population = rs.getInt("population");
                String capital = rs.getString("capital");
                System.out.printf("%s - %s, %s, %s, Population: %d, Capital: %s\n", code, name, continent, region, population, capital);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get country details: " + e.getMessage());
        }
    }

    public void getTopNCountriesByPopulation(Connection con, int n) {
        if (n < 1) {
            System.out.println("N must be at least 1.");  // Ensuring N is positive
            n = 1;
        }
        if (con == null) {
            System.out.println("Database connection is null.");
            return;  // Exit the method if there is no connection
        }

        String query = "SELECT country.code, country.name, country.continent, country.region, country.population, country.capital\n" +
                "FROM country\n" +
                "ORDER BY country.population DESC\n" +
                "LIMIT " + n + ";";


        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
                System.out.println("Top " + n + " Populated Countries:");
                while (rs.next()) {
                    String code = rs.getString("code");
                    String name = rs.getString("name");
                    String continent = rs.getString("continent");
                    String region = rs.getString("region");
                    int population = rs.getInt("population");
                    String capital = rs.getString("capital");
                    System.out.printf("%s - %s, %s, %s, Population: %d, Capital: %s\n", code, name, continent, region, population, capital);
                }
            } catch (SQLException e) {
                System.out.println("Failed to get country details: " + e.getMessage());
        }
    }

    public void getCitiesByPopulation(Connection con) {
        if (con == null) {
            System.out.println("Connection is null.");
            return;  // Exit the method if there is no connection
        }

        String query = "SELECT country.name AS country_name, city.name AS capital, city.population " +
                "FROM country " +
                "JOIN city ON city.countryCode = country.code " +
                "WHERE country.capital = city.ID " +
                "ORDER BY city.population DESC;";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Capitals by Descending Population:");
            while (rs.next()) {
                String countryName = rs.getString("country_name");
                String capital = rs.getString("capital");
                int population = rs.getInt("population");
                System.out.printf("%s - %s has a population of %d\n", countryName, capital, population);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get capital details: " + e.getMessage());
        }
    }

    public void getTopNCitiesByPopulation(Connection con, int n) {
        if (n < 1) {
            System.out.println("N must be at least 1.");  // Ensuring N is positive
            n = 1;
        }
        if (con == null) {
            System.out.println("Database connection is null.");
            return;  // Exit the method if there is no connection
        }

        String query = "SELECT city.name, city.countryCode, city.district, city.population " +
                "FROM country " +
                "JOIN city ON city.countryCode = country.code " +
                "ORDER BY city.population DESC " +
                "LIMIT ?;";  // Use placeholders for parameters

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, n);  // Set the LIMIT value safely
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("Top " + n + " Populated Cities:");
                while (rs.next()) {
                    String name = rs.getString("name");
                    String countryCode = rs.getString("countryCode");
                    String district = rs.getString("district");
                    int population = rs.getInt("population");
                    System.out.printf("%s (%s, %s) - Population: %d\n", name, district, countryCode, population);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to get city details: " + e.getMessage());
        }
    }

    public void getCapitalCitiesByPopulation(Connection con) {
    }

    public void getTopNCapitalCitiesByPopulation(Connection con, int n) {
        if (n < 1) {
            System.out.println("N must be at least 1.");
            n = 1;  // Set to a default value of 1 if the input is invalid
        }
        if (con == null) {
            System.out.println("Database connection is null.");
            return;  // Exit the method if there is no connection
        }

        String query = "SELECT city.name AS capital, country.name AS country_name, city.population " +
                "FROM country " +
                "JOIN city ON city.id = country.capital " +
                "ORDER BY city.population DESC " +
                "LIMIT ?;";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, n);  // Set the LIMIT parameter in the query
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("Top " + n + " Capital Cities by Population:");
                while (rs.next()) {
                    String capital = rs.getString("capital");
                    String countryName = rs.getString("country_name");
                    int population = rs.getInt("population");
                    System.out.printf("%s in %s has a population of %d\n", capital, countryName, population);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to get capital cities by population: " + e.getMessage());
        }
    }

    //next report

}

