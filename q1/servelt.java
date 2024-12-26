package com.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class servelt extends HttpServlet {
    // Handles the GET request to display the form (index.html)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Employee Payroll Form</h2>");
        out.println("<form action='payroll' method='POST'>");
        out.println("Employee Name: <input type='text' name='name'><br><br>");
        out.println("Hours Worked: <input type='number' name='hoursWorked'><br><br>");
        out.println("Hourly Pay Rate: <input type='number' name='payRate' step='0.01'><br><br>");
        out.println("Tax Percentage (e.g., 20): <input type='number' name='tax' value='20'><br><br>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    // Handles the POST request to process the form data
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Reading data from the form
        String name = request.getParameter("name");
        double hoursWorked = Double.parseDouble(request.getParameter("hoursWorked"));
        double payRate = Double.parseDouble(request.getParameter("payRate"));
        double taxPercentage = Double.parseDouble(request.getParameter("tax"));

        // Calculate payroll details
        double grossPay = hoursWorked * payRate;
        double taxAmount = (grossPay * taxPercentage) / 100;
        double netPay = grossPay - taxAmount;

        // Displaying the payroll statement
        out.println("<html><body>");
        out.println("<h2>Payroll Statement</h2>");
        out.println("<p>Employee Name: " + name + "</p>");
        out.println("<p>Hours Worked: " + hoursWorked + "</p>");
        out.println("<p>Hourly Pay Rate: ₹" + payRate + "</p>");
        out.println("<p>Gross Pay: ₹" + grossPay + "</p>");
        out.println("<p>Tax (" + taxPercentage + "%): ₹" + taxAmount + "</p>");
        out.println("<p>Net Pay: ₹" + netPay + "</p>");
        out.println("</body></html>");
    }
}
