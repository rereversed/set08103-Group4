package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Country {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String setCode) {
        this.code = setCode;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String setName) {
        this.name = setName;
    }
    public int population;




    public static void getCountry(String query, Connection con) {
        ArrayList<Country> countries = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Country country = new Country();

                country.setCode(rs.getString("CountryCode"));
                country.setName(rs.getString("name"));

//                System.out.println(name);
//                System.out.println("Country Code: " + code);

                countries.add(country);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get population details: " + e.getMessage());
        }
        displayCountries(countries);

    }

    public static void displayCountries(ArrayList<Country> countries) {
        for (int i = 0; i < countries.size(); i++) {
            Country country = countries.get(i);

            System.out.println("Name " + country.getName());
            System.out.println("Country Code " + country.getCode());
            System.out.println("____________");
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

    public void getCountryPopulation(Connection con, String name) {
        System.out.println(name);
        if(con == null) {
            System.out.println("Connection is null.");
            return;
        }

        String query = "SELECT * " +
                        "FROM country " +
                        "WHERE name = '" +  name + "'";

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

