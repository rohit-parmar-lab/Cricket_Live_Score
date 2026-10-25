/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ROHIT PARMAR
 */
@WebServlet(urlPatterns = {"/Add_Feature_Match"})
public class Add_Feature_Match extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminEmail") == null) {
            response.sendRedirect("Admin.html");
            return;
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String team1 = request.getParameter("team1");
        String team2 = request.getParameter("team2");
        String date = request.getParameter("matchDate");
        String time = request.getParameter("matchTime");
        String venue = request.getParameter("venue");

        try {
            // 1. Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connect to DB
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/clg", // database name
                    "root", // DB username
                    "root" // DB password
            );

            // 3. Prepare statement
            String sql = "INSERT INTO Feature_Match(Team1, Team2, Match_date, Match_time, venue) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, team1);
            ps.setString(2, team2);
            ps.setString(3, date);
            ps.setString(4, time);
            ps.setString(5, venue);

            int i = ps.executeUpdate();
            if (i > 0) {
                // Abhi sirf confirmation show kar rahe hain
                out.println("<html><body style='font-family:Arial;text-align:center;'>");
                out.println("<h2 style='color:green;'>Match Added Successfully!</h2>");
                out.println("<p><b>Team 1:</b> " + team1 + "</p>");
                out.println("<p><b>Team 2:</b> " + team2 + "</p>");
                out.println("<p><b>Date:</b> " + date + "</p>");
                out.println("<p><b>Date:</b> " + time + "</p>");
                out.println("<p><b>Venue:</b> " + venue + "</p>");
                out.println("<br><a href='Admin_Home'>Back</a>");
//                out.println("<button class='btn btn-danger'>Logout</button>");

                out.println("</body></html>");
            } else {
                out.println("<h3 style='color:red;text-align:center;'>Error Adding Match!</h3>");
            }

            con.close();

        } catch (Exception ex) {
            out.println("<h3 style='color:red;text-align:center;'>Error: " + ex.getMessage() + "</h3>");
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Session check
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminEmail") == null) {
            response.sendRedirect("Admin.html");
            return;
        }
        response.sendRedirect("add_feature_match.html");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
