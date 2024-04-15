package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Country {
    public String code;
    public String name;

    public static void getCountry(String query, Connection con) {
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                String code = rs.getString("CountryCode");
                String name = rs.getString("name");

                System.out.println(name);
                System.out.println("Country Code: " + code);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get population details: " + e.getMessage());
        }

    }

    public void getCitiesByCountryPopulation(Connection con) {
        if (con == null) {
            System.out.println("Connection is null.");
            return;
        }

        String query = "SELECT * FROM city ORDER BY population DESC";
        getCountry(query, con);


    }

    public void getCitiesByDistrictPopulation(Connection con) {
    }

    public void getTopNCitiesByCountryPopulation(Connection con, int n) {
    }

    public void getTopNCitiesByDistrictPopulation(Connection con, int n) {
    }

    public void getPopulationDistributionByCountry(Connection con) {
    }

    public void getCountryPopulation(Connection con) {
    }

    public void getDistrictPopulation(Connection con) {
    }

    public void getCityPopulation(Connection con) {
    }
}

