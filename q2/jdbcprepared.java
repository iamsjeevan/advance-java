package com.example.jdbc;

import java.sql.*;
import java.util.Scanner;

public class jdbcprepared {
    public static void main(String[] args) {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/your_database_name"; // Replace with your database name
        String user = "root";  // XAMPP MySQL default user is 'root'
        String password = "";  // Default XAMPP MySQL password is empty

        // Database connection and SQL statement
        Connection conn = null;
        PreparedStatement stmt = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection(url, user, password);

            // Get department details from user input
            System.out.print("Enter Dept_ID: ");
            int deptID = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.print("Enter Department Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Year Established: ");
            int yearEstablished = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.print("Enter Head Name: ");
            String headName = scanner.nextLine();

            System.out.print("Enter Number of Employees: ");
            int noOfEmployees = scanner.nextInt();

            // Insert a new department record using PreparedStatement
            String insertSQL = "INSERT INTO Department (Dept_ID, Name, Year_Established, Head_Name, No_of_Employees) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(insertSQL);
            stmt.setInt(1, deptID); // Dept_ID
            stmt.setString(2, name); // Name
            stmt.setInt(3, yearEstablished); // Year_Established
            stmt.setString(4, headName); // Head_Name
            stmt.setInt(5, noOfEmployees); // No_of_Employees
            stmt.executeUpdate();

            // Display the details from the Department table
            String query = "SELECT * FROM Department";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            // Print the department details
            System.out.println("\nDept_ID | Name | Year_Established | Head_Name | No_of_Employees");
            while (rs.next()) {
                System.out.println(rs.getInt("Dept_ID") + " | " +
                        rs.getString("Name") + " | " +
                        rs.getInt("Year_Established") + " | " +
                        rs.getString("Head_Name") + " | " +
                        rs.getInt("No_of_Employees"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close resources
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                if (scanner != null) scanner.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
