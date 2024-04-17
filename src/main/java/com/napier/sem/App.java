package com.napier.sem;
import java.sql.*;
import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static InputHandler inputHandler = new InputHandler(scanner);

    public static Connection con = null;
    private static boolean askContinue() {
        System.out.print("Do you want to view another report? (yes/no): ");
        String response = scanner.nextLine();  // Change from `scanner.next()` to `scanner.nextLine()`
        return response.equalsIgnoreCase("yes");
    }


    static class InputHandler {
        private final Scanner scanner;

        public InputHandler(Scanner scanner) {
            this.scanner = scanner;
        }

        public int getPositiveInt() {
            while (true) {
                System.out.println("Please input a number for how many results you want:");
                String input = scanner.nextLine();
                try {
                    int value = Integer.parseInt(input);
                    if (value > 0) {
                        return value;
                    } else {
                        System.out.println("Error: The number must be positive. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid input. Please enter a valid integer.");
                }
            }
        }

        public String getUserCountry() {

            System.out.println(("Please Input the country "));
            String input = scanner.nextLine();
            try {
                String country = input;
                if (country != null) {
                    return country;
                } else {
                    System.out.println("Error: Please enter a country");
                }
            } catch (Exception E) {
                System.out.println(" Error: Invalid Input");
            }
        return null;
        }



        public String getUserContinent(){
                System.out.println(("Please Input the continent "));
                String input = scanner.nextLine();
                try {
                    String continent = input;
                    if (continent != null){
                        return continent;
                    } else {
                        System.out.println("Error: Please enter a continent");
                    }
                }   catch (Exception E){
                    System.out.println(" Error: Invalid Input");
                }

            return null;
        }

        public String getUserRegion (){
                System.out.println(("Please Input the Region "));
                String input = scanner.nextLine();
                try {
                    String region = input;
                    if (region != null){
                        return region;
                    } else {
                        System.out.println("Error: Please enter a Region");
                    }
                }   catch (Exception E){
                    System.out.println(" Error: Invalid Input");
                }
            return null;
        }

        public String getUserCity (){
                System.out.println(("Please Input the City "));
                String input = scanner.nextLine();
                try {
                    String city = input;
                    if (city != null){
                        return city;
                    } else {
                        System.out.println("Error: Please enter a City");
                    }
                }   catch (Exception E){
                    System.out.println(" Error: Invalid Input");
                }

            return null;

        }

        public String getUserDistrict (){
            System.out.println(("Please Input the District "));
            String input = scanner.nextLine();
            try {
                String district = input;
                if (district != null){
                    return district;
                } else {
                    System.out.println("Error: Please enter a District");
                }
            }   catch (Exception E){
                System.out.println(" Error: Invalid Input");
            }

            return null;

        }



    }

    public static void main(String[] args) {
        App a = new App();
        World b = new World();
        Continent c = new Continent();
        Region d = new Region();
        Country e = new Country();
        Language f = new Language();
        a.connect();

        boolean isCI = "true".equals(System.getenv("CI"));
        System.out.println("CI Environment Variable: " + isCI);  // Debug output        // When it's not in GitHub it'll go in this
        if (!isCI) {
            boolean exit = false;
            while (!exit) {
                displayMenu();  // This function will display the menu options
                System.out.print("Enter choice using the provided number: \n");
                int choice = Integer.parseInt(scanner.nextLine());  // Parse the integer after reading the line
                processChoice(choice, a, b, c, d, e, f);  // Process the user's choice
                if (!askContinue()) {
                    System.out.println("Thank you for using the application. Goodbye!");
                    exit = true;
                }
            }
        } else {
            // Example of running in non-interactive mode for CI
           a.disconnect();
            // Add other non-interactive tests or operations as needed
        }

        a.disconnect();
    }

    // Function to list options
    public static void displayMenu() {
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
    }
    //Function for List of choices
    public static void processChoice(int choice, App a, World b, Continent c, Region d, Country e, Language f) {
        int n;
        String name;
        switch (choice) {
            case 1: //WORLD
                b.getCountriesByPopulation(con);
                break;
            case 2: //CONTINENT
                name = inputHandler.getUserContinent();
                c.getCountriesByContinentPopulation(con, name);
                break;
            case 3: /*  Region  */
                name = inputHandler.getUserRegion();
                d.getCountriesByRegionPopulation(con, name);
                break;
            case 4: /*  World  */
                n = inputHandler.getPositiveInt();
                b.getTopNCountriesByPopulation(con, n);
                break;
            case 5: /*  Continent  */
                n = inputHandler.getPositiveInt();
                name = inputHandler.getUserContinent();
                c.getTopNCountriesByContinentPopulation(con, n, name);
                break;
            case 6: /*  Region  */
                name = inputHandler.getUserRegion();
                n = inputHandler.getPositiveInt();
                d.getTopNCountriesByRegionPopulation(con, n, name);
                break;
            case 7: /*  World  */
                b.getCitiesByPopulation(con);
                break;
            case 8: /*  Continent  */
                name = inputHandler.getUserContinent();
                c.getCitiesByContinentPopulation(con,name);
                break;
            case 9: /*  Region  */
                name = inputHandler.getUserRegion();
                d.getCitiesByRegionPopulation(con,name);
                break;
            case 10: /*  Country  */
                e.getCitiesByCountryPopulation(con);
                break;
            case 11: /*  Country  */
                name = inputHandler.getUserDistrict();
                System.out.println("City Name"+ "  "+  "Population");
                e.getCitiesByDistrictPopulation(con, name);
                break;
            case 12: /*  World  */
                n = inputHandler.getPositiveInt();
                b.getTopNCitiesByPopulation(con, n);
                break;
            case 13: /*  Continent  */
                name = inputHandler.getUserContinent();
                n = inputHandler.getPositiveInt();
                c.getTopNCitiesByContinentPopulation(con, n, name);
                break;
            case 14: /*  Region  */
                name = inputHandler.getUserRegion();
                n = inputHandler.getPositiveInt();
                d.getTopNCitiesByRegionPopulation(con, n, name);
                break;
            case 15: /*  Country  */
                n = inputHandler.getPositiveInt();
                e.getTopNCitiesByCountryPopulation(con, n);
                break;
            case 16: /*  Country  */
                n = inputHandler.getPositiveInt();
                name = inputHandler.getUserDistrict();
                e.getTopNCitiesByDistrictPopulation(con, n, name);
                break;
            case 17: /*  World  */
                b.getCapitalCitiesByPopulation(con);
                break;
            case 18: /*  Continent  */
                name = inputHandler.getUserContinent();
                c.getCapitalCitiesByContinentPopulation(con,name);
                break;
            case 19: /*  Region  */
                name = inputHandler.getUserRegion();
                d.getCapitalCitiesByRegionPopulation(con, name);
                break;
            case 20: /*  World  */
                n = inputHandler.getPositiveInt();
                b.getTopNCapitalCitiesByPopulation(con, n);
                break;
            case 21: /*  Continent  */
                name = inputHandler.getUserContinent();
                n = inputHandler.getPositiveInt();
                c.getTopNCapitalCitiesByContinentPopulation(con, n, name);
                break;
            case 22: /*  Region  */
                n = inputHandler.getPositiveInt();
                d.getTopNCapitalCitiesByRegionPopulation(con, n);
                break;
            case 23: /*  Continent  */
                c.getPopulationDistributionByContinent(con);
                break;
            case 24: /*  Region  */
                d.getPopulationDistributionByRegion(con);
                break;
            case 25: /*  Country  */
                name = inputHandler.getUserCountry();
                e.getPopulationDistributionByCountry(con, name);
                break;
            case 26: /*  World  */
                b.getWorldPopulation(con);
                break;
            case 27: /*  Continent  */
                name = inputHandler.getUserContinent();
                c.getContinentPopulation(con, name);
                break;
            case 28: /*  Region  */
                d.getRegionPopulation(con);
                break;
            case 29: /*  Country  */
                name = inputHandler.getUserCountry();
                e.getCountryPopulation(con, name);
                break;
            case 30: /*  Country  */
                name = inputHandler.getUserDistrict();
                e.getDistrictPopulation(con,name);
                break;
            case 31: /*  Country  */
                name = inputHandler.getUserCity();
                e.getCityPopulation(con, name);
                break;
            case 32: /* Language */
                f.getLanguageSpeakersPopulation(con);
                break;
            case 0:
                System.out.println("Exiting...");
                a.disconnect();
                break;
            // Handle other cases
        }
    }
    //function to connect to database
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully");
            int retries = 10;
            for (int i = 0; i < retries; ++i) {
                System.out.println("Connecting to database...");
                try {
                    Thread.sleep(1000);  // Reduced sleep for quicker connection attempts
                    con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false&user=root&password=example");
                    System.out.println("Successfully connected");
                    break;
                } catch (SQLException sql) {
                    System.out.println("Failed to connect to database attempt " + i);
                    System.out.println(sql.getMessage());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }
    }

    // Function to Disconnect from database
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Connection closed");
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }
}
