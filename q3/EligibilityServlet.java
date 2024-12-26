package com.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class EligibilityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Get form data
        String username = request.getParameter("username");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));

        // Determine eligibility
        String eligibility = (age >= 18) ? "eligible" : "not eligible";

        // Print the response
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Eligibility Check</h2>");
        out.println("<p>Name: " + username + "</p>");
        out.println("<p>Gender: " + gender + "</p>");
        out.println("<p>Age: " + age + "</p>");
        out.println("<p>You are " + eligibility + " to vote.</p>");
        out.println("</body></html>");
    }
}
