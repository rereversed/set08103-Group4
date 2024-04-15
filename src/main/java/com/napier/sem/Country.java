package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
                Country country = new Country();

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

    public void getDistrictPopulation(Connection con) {
    }

    public void getCityPopulation(Connection con) {
//        if(con == null) {
//            System.out.println("Connection is null.");
//            return;
//        }
//
//        String query =
//                "SELECT * FROM city ORDER BY city.population DESC";
//        getCountry(query, con);

    }
}

