package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Region {
    public void getCountriesByRegionPopulation(Connection con, String name) {
        if(con ==null){
            System.out.println("Connection is Null");
            return;
        }

        String query =  "SELECT Name AS country_name, Region, Population " +
                        "FROM country " +
                        "WHERE Region =  '" + name + "'" +
                        "ORDER BY Population DESC;";

        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query)){

            while (rs.next()){
                String countryName = rs.getString("country_name");
                String region = rs.getString("Region");
                int population = rs.getInt("Population");

                System.out.println("Country: " + countryName + ", Region: " + region + ", Population: " + population);
            }
            }catch (SQLException e){
            System.out.println("Failed to get Details " + e.getMessage());
        }

    }

    public void getTopNCountriesByRegionPopulation(Connection con, int n, String name) {
        if (con == null){
            System.out.println("Connection is Null");
            return;
        }
        if(n<1){
            System.out.println("N must be at least 1 ");
            n=1;
        }

        String query = "SELECT Name AS country_name, Population " +
                        "FROM country " +
                        "WHERE Region = '" + name + "'" +
                        "ORDER BY Population DESC " +
                        "LIMIT " + n;

        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query)){
            System.out.println("Top " + n + " Populated Countries:");

            while ((rs.next())){
                String countryName = rs.getString("country_name");
                int population = rs.getInt("Population");

                System.out.println(countryName + "   " + population);
            }
        }catch (SQLException e){
            System.out.println("Failed to get details " + e.getMessage());
        }
    }

    public void getCitiesByRegionPopulation(Connection con, String name) {
        if (con == null){
            System.out.println("Connection is Null ");
            return;
        }

        String query =  "SELECT city.Name AS city_name, city.Population " +
                        "FROM city " +
                        "JOIN country ON city.CountryCode = country.Code " +
                        "WHERE country.Region = '" + name + "'" +
                        "ORDER BY city.Population DESC;";

        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query)){

            while (rs.next()){

                String cityName = rs.getString("city_name");
                int population = rs.getInt("Population");
                System.out.println(cityName + "   " + population);
            }
        }catch (SQLException e ){
            System.out.println("Failed to get details " + e.getMessage());
        }

    }

    public void getTopNCitiesByRegionPopulation(Connection con, int n, String name) {
        if(con == null){
            System.out.println("Connection is Null");
            return;
        }

        if(n<1){
            System.out.println("N must be ast least 1 ");
            n=1;
        }

        String query =  "SELECT city.Name AS city_name, city.Population " +
                        "FROM city " +
                        "JOIN country ON city.CountryCode = country.Code " +
                        "WHERE country.Region = '" + name + "'" +
                        "ORDER BY city.Population DESC " +
                        "LIMIT " +n;

        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query)){

            while (rs.next()){
                String cityName = rs.getString("city_name");
                int population = rs.getInt("Population");
                System.out.println(cityName + "    " + population);

            }
        }catch (SQLException e){
            System.out.println("Failed to get Deatils " + e.getMessage());
        }

    }

    public void getCapitalCitiesByRegionPopulation(Connection con, String name) {
        if(con == null){
            System.out.println("Connection is Null");
            return;
        }

        String query =  "SELECT city.Name AS city_name, country.Name AS country_name, city.Population " +
                        "FROM city " +
                        "JOIN country ON city.CountryCode = country.Code " +
                        "WHERE country.Region = '" + name + "'" + " AND city.ID = country.Capital " +
                        "ORDER BY city.Population DESC;";

        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query)){

            while (rs.next()){

                String cityName = rs.getString("city_name");
                String countryName = rs.getString("country_name");
                int population = rs.getInt("Population");

                System.out.printf("%-30s %-30s %-15d\n", cityName, countryName, population);

            }
        }catch (SQLException e){
            System.out.println("Failed to get details " + e.getMessage());
        }

    }

    public void getTopNCapitalCitiesByRegionPopulation(Connection con, int n, String name) {
        if(con == null){
            System.out.println("Connection is Null");
            return;
        }

        if(n<1){
            System.out.println("N must be greater tha 1");
            n=1;
        }

        String query =  "SELECT * " +
                        "FROM city INNER JOIN country ON country.capital = city.ID " +
                        " WHERE country.Region = '" + name + "'" +
                        " ORDER BY city.population DESC LIMIT " + n;

//

        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query)){

            while(rs.next()){

                String cityName = rs.getString("name");
                int population = rs.getInt("population");
                System.out.println(cityName + "  " + population);
            }
        }catch (SQLException e){
            System.out.println("Failed to get Details " + e.getMessage());
        }


    }

    public void getPopulationDistributionByRegion(Connection con) {
    }

    public void getRegionPopulation(Connection con) {
    }
}
