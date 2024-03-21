package com.napier.sem;

import java.util.ArrayList;
import java.sql.*;

public class City {
    //Initialising variables for City
    private int ID;

    //Get and Set methods for City ID.
    public int getID() {
        return ID;
    }

    public void setID(int setID) {
        this.ID = setID;
    }

    private String Name;

    //Get and Set methods for City
    public String getName() {
        return Name;
    }

    public void setName(String setName) {
        this.Name = setName;
    }

    private String CountryCode;

    //Get and Set methods for City Country Code
    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String setCountryCode) {
        this.CountryCode = setCountryCode;
    }

    private String District;

    //Get and Set methods for City District
    public String getDistrict() {
        return District;
    }

    public void setDistrict(String setDistrict) {
        this.District = setDistrict;
    }

    private int Population;

    //Get and Set methods for City Population
    public int getPopulation() {
        return Population;
    }

    public void setPopulation(int setPopulation) {
        this.Population = setPopulation;
    }


    public static ArrayList<City> worldCities() {
        String Select = "SELECT * FROM city ORDER BY population DESC";
        return getCities(Select);
    }

    public static ArrayList<City> getCities(String strSelect) {
        ArrayList<City> Cities = new ArrayList<>();
        try {
            //Create SQL Statement
            Statement stmt = App.con.createStatement();
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                City city = new City();
                city.setName(rset.getString("city.Name"));
                city.setCountryCode(rset.getString("city.CountryCode"));
                city.setDistrict(rset.getString("city.District"));
                city.setPopulation(rset.getInt("city.Population"));

                Cities.add(city);
            }

            //Return city data if valid
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;

        }
        displayCities(Cities);
        return Cities;
    }


    public static void displayCities(ArrayList<City> cities) {
        for (int i = 0; i < cities.size(); i++) {
            City city = cities.get(i);
            System.out.println("Name " + city.getName());
            System.out.println("Country Code " + city.getCountryCode());
            System.out.println("District " + city.getDistrict());
            System.out.println("Population " + city.getPopulation());
            System.out.println("_______________________");

        }

    }
}

