import java.sql.*;

public class MoviesDatabase {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/your_database_name"; // Replace with your database name
        String username = "root"; // Default MySQL username
        String password = ""; // Default MySQL password for root user
        
        // SQL query to retrieve all movies
        String query = "SELECT * FROM Movies";

        try {
            // Load MySQL JDBC driver (for MySQL 8 or later)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create a Statement object
            Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,  // Makes the ResultSet scrollable
                ResultSet.CONCUR_UPDATABLE          // Allows updates to the ResultSet
            );

            // Execute the query to get all movies
            ResultSet resultSet = statement.executeQuery(query);

            // Display all the movie details
            System.out.println("Details of All Movies:");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String movieName = resultSet.getString("Movie_Name");
                String genre = resultSet.getString("Genre");
                double imdbRating = resultSet.getDouble("IMDB_Rating");
                int year = resultSet.getInt("Year");
                
                System.out.println("ID: " + id + ", Movie Name: " + movieName + ", Genre: " + genre +
                                   ", IMDB Rating: " + imdbRating + ", Year: " + year);
            }
            
            // Now, display details of the 5th movie
            if (resultSet.absolute(5)) { // Move the cursor to the 5th row
                System.out.println("\nDetails of the 5th Movie:");
                int id = resultSet.getInt("ID");
                String movieName = resultSet.getString("Movie_Name");
                String genre = resultSet.getString("Genre");
                double imdbRating = resultSet.getDouble("IMDB_Rating");
                int year = resultSet.getInt("Year");

                System.out.println("ID: " + id + ", Movie Name: " + movieName + ", Genre: " + genre +
                                   ", IMDB Rating: " + imdbRating + ", Year: " + year);
            } else {
                System.out.println("\nThere are fewer than 5 movies in the database.");
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
