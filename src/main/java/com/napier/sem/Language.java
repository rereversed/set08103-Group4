package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Language {
    public void getLanguageSpeakersPopulation(Connection con) {

        //If statement to verify connection to database is successful and in-use.
        if (con == null) {
            System.out.println("Connection is null.");
            return;  // Exit the method if there is no connection
        }

        //MYSQL Query to find the Total Population of Languages + World Percentage.
        String query =
                "SELECT countrylanguage.Language AS Language, SUM((countrylanguage.Percentage/100) * country.Population) AS languageTotal, " +
                "ROUND(SUM((countrylanguage.Percentage/100) * country.Population) / (SELECT SUM(country.Population) FROM country)* 100,1) AS worldPercentage  " +
                "FROM countrylanguage " +
                "JOIN country ON countrylanguage.CountryCode = country.Code " +
                "WHERE countrylanguage.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                "GROUP BY Language " +
                "ORDER BY SUM((countrylanguage.Percentage/100) * country.Population) DESC;";


        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            //While loop retrieves requested data until it reaches the end of the database.
            while (rs.next()) {
                String countryLanguage = rs.getString("Language");
                int total = rs.getInt("languageTotal");
                double percentage = rs.getDouble("worldPercentage");
                System.out.println(countryLanguage + " Language Population: " + total + " World Percentage: " + percentage);
            }

            //Catch statement for any errors in database or sql query.
        } catch (SQLException e) {
            System.out.println("Failed to get language population details: " + e.getMessage());
        }
    }
}
