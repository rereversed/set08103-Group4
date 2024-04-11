package com.napier.sem;
import java.sql.*;

public class App {
    public static Connection con = null;
    /**
     * The entry point of the application.
     * <p>
     * This method initializes the application, connects to the database, retrieves
     * the top N populated countries, displays them, and then disconnects from the database.
     * </p>
     * @param args The command line arguments (not used).
     */
    public static void main(String[] args) {
        // Create new Application
        App a = new App();
        // Create new Country

        Country c = new Country();
        City d = new City();
        Countrylanguage e = new Countrylanguage();

        // Connect to database
        a.connect();
        ResultSet resultSet = c.getTopRegionDescending(a.con, "Southern and Central Asia", 5);
        //resultSet = c.topNPopulatedCountries(a.con, 5);
       // resultSet = c.getCountryDescending(a.con);
       // resultSet = c.getContinentDescending(a.con, "Asia");
       // resultSet = c.topNPopulatedCountriesContinent(a.con, "Asia", 4);
       // resultSet = c.countryRegionDescending(a.con, "Southern and Central Asia");
       // resultSet = c.topNPopulatedCountriesContinent(a.con, "Asia", 4);

        // resultSet = d.topNPopulatedCitiesDistrict(a.con, "Noord-Brabant", 3);
       //  resultSet = d.topNPopulatedCities(a.con, 5);
       // resultSet = d.getTopRegionCityDescending(a.con, "Western Europe", 6);
       // resultSet = d.getCitiesInContinentDesc(a.con, "Asia");
       //  resultSet = d.getCountryCityDescending(a.con, "France");
       // resultSet = d.topNPopulatedCitiesContinent(a.con, "Europe", 5);
        // resultSet = d.getRegionCityDescending(a.con, "Western Europe");
        // resultSet = d.getCityDescending(a.con);
       // resultSet = d.getDistrictCityDescending(a.con, "Buenos Aires");
       // resultSet = d.getTopCountryCityDescending(a.con, "France", 5);

        // ResultSet resultSet = e.topNPopulatedCapitals(a.con, 5);
        // ResultSet resultSet = e.getNRegionCapitalsDescending(a.con, "Western Europe", 6);
        //ResultSet resultSet = e.getContinentCapitalsDescending(a.con, "Asia");
        //ResultSet resultSet = e.topNPopulatedCapitalsByContinent(a.con, "Europe", 5);
        // ResultSet resultSet = e.getCapitalsPopulationDesc(a.con);
        //ResultSet resultSet = e.getRegionCapitalsDescending(a.con, "Western Europe");


        //ResultSet resultSet = .getWorldPopulation(a.con);
        // ResultSet resultSet = .getDistrictPopulation(a.con,  "Constantine");
        //ResultSet resultSet = .getRegionPopulations(a.con, "Western Europe");
        // ResultSet resultSet = .getCountryPopulation(a.con, "Germany");
        //ResultSet resultSet = .getCityPopulation(a.con, "Edinburgh");
        //ResultSet resultSet = .getCityPopulationAll(a.con);
        //ResultSet resultSet = .getContinentCityPopulations(a.con);
        //ResultSet resultSet = .getContinentPopulations(a.con, "Asia");
        //ResultSet resultSet = .getAllRegionPopulations(a.con);

        //c.displayCountries(resultSet);
        //d.displayCities(resultSet);
        //.displayCapitals(resultSet);
        //.displayPopulations(resultSet);



        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
                System.out.println(("Connection closed"));
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }
}