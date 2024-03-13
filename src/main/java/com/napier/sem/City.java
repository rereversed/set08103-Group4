package com.napier.sem;


public class City {
    public int ID;
    public String Name;
    public String CountryCode;
    public String District;
    public int Population;

    public int getID() {
        return ID;
    }
    private void setID(int id) {
        id = ID;
    }

    public String getName() {
        return Name;
    }
    private void setName(String name) {
        name = Name;
    }

    public String getCountryCode() {
        return CountryCode;
    }
    private void setCountryCode(String countryCode) {
        countryCode = CountryCode;
    }

    public String getDistrict() {
        return District;
    }
    private void setDistrict(String district) {
        district = District;
    }

    public int getPopulation() {
        return Population;
    }
    private void setPopulation(int population) {
        population = Population;
    }
}

