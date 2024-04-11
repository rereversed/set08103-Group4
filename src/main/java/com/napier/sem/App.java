package com.napier.sem;
import java.sql.*;
import java.sql.Connection;
import java.util.Scanner;


public class App {
    static Scanner scanner = new Scanner(System.in);
    private static boolean askContinue() {
        System.out.print("Do you want to view another report? (yes/no): ");
        String response = scanner.next();
        return response.equalsIgnoreCase("yes");
    }
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

        // Create new domains
        World b = new World();
        Continent c = new Continent();
        Region d = new Region();
        Country e = new Country();
        Language f = new Language();

        // Connect to database
        a.connect();

        //CLI with options for reports.
        boolean exit = false;
        while (!exit) {
            System.out.println("\nWorld Information System:");
            System.out.println("\nPlease choose which report topic you would like to generate:\n");

            System.out.println("1. All the countries in the world organised by largest population to smallest.");
            System.out.println("2. All the countries in a continent organised by largest population to smallest.");
            System.out.println("3. All the countries in a region organised by largest population to smallest.\n");
            System.out.println("4. The top N populated countries in the world where N is provided by the user.\n");
            System.out.println("5. The top N populated countries in a continent where N is provided by the user.\n");
            System.out.println("6. The top N populated countries in a region where N is provided by the user.\n");
            System.out.println("7. All the cities in the world organised by largest population to smallest.\n");
            System.out.println("8. All the cities in a continent organised by largest population to smallest.\n");
            System.out.println("9. All the cities in a region organised by largest population to smallest.\n");
            System.out.println("10. All the cities in a country organised by largest population to smallest.\n");
            System.out.println("11. All the cities in a district organised by largest population to smallest.\n");
            System.out.println("12. The top N populated cities in the world where N is provided by the user.\n");
            System.out.println("13. The top N populated cities in a continent where N is provided by the user.\n");
            System.out.println("14. The top N populated cities in a region where N is provided by the user.\n");
            System.out.println("15. The top N populated cities in a country where N is provided by the user.\n");
            System.out.println("16. The top N populated cities in a district where N is provided by the user.\n");
            System.out.println("17. All the capital cities in the world organised by largest population to smallest.\n");
            System.out.println("18. All the capital cities in a continent organised by largest population to smallest.\n");
            System.out.println("19. All the capital cities in a region organised by largest to smallest.\n");
            System.out.println("20. The top N populated capital cities in the world where N is provided by the user.\n");
            System.out.println("21. The top N populated capital cities in a continent where N is provided by the user.\n");
            System.out.println("22. The top N populated capital cities in a region where N is provided by the user.\n");
            System.out.println("23. The population of people, people living in cities, and people not living in cities in each continent.\n");
            System.out.println("24. The population of people, people living in cities, and people not living in cities in each region.\n");
            System.out.println("25. The population of people, people living in cities, and people not living in cities in each country.\n");
            System.out.println("26. The population of the world.\n");
            System.out.println("27. The population of a continent.\n");
            System.out.println("28. The population of a region.\n");
            System.out.println("29. The population of a country.\n");
            System.out.println("30. The population of a district.\n");
            System.out.println("31. The population of a city.\n");
            System.out.println("32. The number of people who speak the top 5 languages, inc %\n\n");
            System.out.println("0. Exit\n\n");

            System.out.print("Enter choice using the provided number: \n");
            //remove lines below and comments that block the code [github workflow issue, needs fix asap].
            a.disconnect();
            break;
            /**
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    b.getWorldPopulation(con);
                   // b.displayPopulations();
                    break;
                case 2:
                    break;
                // Add more cases here
                case 0:
                    exit = true;
                    a.disconnect();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            if (!askContinue()) {
                System.out.println("Thank you for using the application. Goodbye!");
                break;  // Exit the loop and thus the program
            }
             */
        }


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
            } catch (SQLException sql) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sql.getMessage());
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