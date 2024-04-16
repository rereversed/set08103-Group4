package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.stream.StreamSupport;

public class Country {
    public String code;
    public String name;
    public int population;


    public void getCitiesByCountryPopulation(Connection con) {
        if (con == null) {
            System.out.println("Connectioxn is null.");
            return;
        }

        String query = "SELECT * FROM city ORDER BY population DESC";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                String code = rs.getString("CountryCode");
                String name = rs.getString("name");

                System.out.println(name);
                System.out.println("Country Code: " + code);
                System.out.println("____________");

            }
        } catch (SQLException e) {
            System.out.println("Failed to get population details: " + e.getMessage());
        }


    }

    public void getCitiesByDistrictPopulation(Connection con) {
    }

    public void getTopNCitiesByCountryPopulation(Connection con, int n) {
        if (con == null) {
            System.out.println("Connectioxn is null.");
            return;
        }
        if (n < 1) {
            System.out.println("N must be at least 1.");  // Ensuring N is positive
            n = 1;
        }

        String query = "SELECT * FROM country ORDER BY population DESC LIMIT " + n;


        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Top " + n + " Populated Countries:");
            while (rs.next()) {
                String name = rs.getString("name");
                int population = rs.getInt("population");
                System.out.printf("- %s, Population: %d \n", name, population);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get country details: " + e.getMessage());
        }

    }

    public void getTopNCitiesByDistrictPopulation(Connection con, int n) {
    }

    public void getPopulationDistributionByCountry(Connection con) {
    }

    public void getCountryPopulation(Connection con, String name) {
        System.out.println(name);
        if (con == null) {
            System.out.println("Connection is null.");
            return;
        }

        String query = "SELECT * " +
                "FROM country " +
                "WHERE name = '" + name + "'";

        System.out.println(query);

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                String countryName = rs.getString("Name");
                int population = rs.getInt("Population");

                System.out.println(countryName);
                System.out.println("Total Population: " + population);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get population details: " + e.getMessage());
        }

    }

    public void getDistrictPopulation(Connection con, String name) {
        if(con == null){
            System.out.println("Connection is null");
            return;
        }

        String query = "SELECT SUM(Population) AS total_population " +
                        "FROM city " +
                        "WHERE District = '" + name + "'";



        try ( Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query)){
            if (rs.next()){
                int population = rs.getInt("total_population");

                System.out.println(name);
                System.out.println(population);
            }
        }catch (SQLException e){
            System.out.println("Failed to get Population Details:" +  e.getMessage());
        }
    }

    public void getCityPopulation(Connection con, String name) {
           if(con == null) {
               System.out.println("Connection is null.");
               return;
           }
           String query = "SELECT * " +
                            "FROM city " +
                            "WHERE Name ='" + name +"'";

           try(Statement stmt = con.createStatement();
               ResultSet rs = stmt.executeQuery(query)){

               if(rs.next()) {
                   String cityName = rs.getString("Name");
                   int population = rs.getInt("Population");

                   System.out.println(cityName);
                   System.out.println("Total Population " + population );
               }
        }catch (SQLException e){
               System.out.println("Failed to get population details " + e.getMessage());
           }
    }
}

