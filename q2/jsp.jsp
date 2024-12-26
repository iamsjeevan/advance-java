<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%! 
// Java code for the average calculation logic
public class AverageCalculator {
    public static String processNumbers(int[] numbers) {
        // Calculate the average
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        double average = sum / 11.0;
        
        // Find numbers below average
        int belowAverageCount = 0;
        for (int num : numbers) {
            if (num < average) {
                belowAverageCount++;
            }
        }
        
        // Find duplicate numbers
        StringBuilder duplicates = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] == numbers[j] && duplicates.indexOf(String.valueOf(numbers[i])) == -1) {
                    duplicates.append(numbers[i]).append(" ");
                    break;
                }
            }
        }

        return "Average: " + average + "<br>Below average count: " + belowAverageCount + 
               "<br>Duplicate numbers: " + (duplicates.length() > 0 ? duplicates.toString() : "None");
    }
}
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Average and Duplicate Numbers</title>
</head>
<body>
    <h2>Enter 11 numbers:</h2>
    <form action="average.jsp" method="POST">
        <%
        String result = "";
        if (request.getMethod().equals("POST")) {
            // Get input values from user
            int[] numbers = new int[11];
            for (int i = 0; i < 11; i++) {
                numbers[i] = Integer.parseInt(request.getParameter("num" + (i + 1)));
            }
            
            // Call the AverageCalculator class to compute the result
            result = AverageCalculator.processNumbers(numbers);
        }
        %>
        <table>
            <tr><td>Number 1:</td><td><input type="text" name="num1" required></td></tr>
            <tr><td>Number 2:</td><td><input type="text" name="num2" required></td></tr>
            <tr><td>Number 3:</td><td><input type="text" name="num3" required></td></tr>
            <tr><td>Number 4:</td><td><input type="text" name="num4" required></td></tr>
            <tr><td>Number 5:</td><td><input type="text" name="num5" required></td></tr>
            <tr><td>Number 6:</td><td><input type="text" name="num6" required></td></tr>
            <tr><td>Number 7:</td><td><input type="text" name="num7" required></td></tr>
            <tr><td>Number 8:</td><td><input type="text" name="num8" required></td></tr>
            <tr><td>Number 9:</td><td><input type="text" name="num9" required></td></tr>
            <tr><td>Number 10:</td><td><input type="text" name="num10" required></td></tr>
            <tr><td>Number 11:</td><td><input type="text" name="num11" required></td></tr>
            <tr><td><input type="submit" value="Submit"></td></tr>
        </table>
    </form>

    <%
    if (!result.isEmpty()) {
        out.println("<h3>Results:</h3>");
        out.println(result);
    }
    %>
</body>
</html>
