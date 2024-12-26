<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Armstrong Number Check</title>
</head>
<body>
    <h2>Armstrong Number Check</h2>

    <!-- Form to get user input -->
    <form method="post">
        Enter a number: 
        <input type="number" name="number" required>
        <input type="submit" value="Check">
    </form>

    <%
        // Get the input number from the user
        String numStr = request.getParameter("number");
        if (numStr != null) {
            int num = Integer.parseInt(numStr);
            int originalNum = num;
            int sum = 0;
            int numOfDigits = String.valueOf(num).length();

            // Check if the number is an Armstrong number
            while (num != 0) {
                int digit = num % 10;
                sum += Math.pow(digit, numOfDigits);
                num /= 10;
            }

            // Display the result
            if (sum == originalNum) {
                out.println("<p>" + originalNum + " is an Armstrong number.</p>");
            } else {
                out.println("<p>" + originalNum + " is not an Armstrong number.</p>");
            }
        }
    %>
</body>
</html>
