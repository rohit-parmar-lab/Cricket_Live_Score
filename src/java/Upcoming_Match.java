/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ROHIT PARMAR
 */
@WebServlet(urlPatterns = {"/Upcoming_Match"})
public class Upcoming_Match extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clg", "root", "root");
//            past matches
//            String sql = "SELECT Team1, Team2, Match_date, Match_time, venue FROM Feature_Match WHERE Match_date < CURDATE() ORDER BY Match_date DESC";
//            Feature match 
            String sql = "SELECT Team1, Team2, Match_date, Match_time, venue " + "FROM Feature_Match " + "WHERE Match_date >= CURDATE() " + "ORDER BY Match_date ASC";
//            All match
//            String sql = "SELECT Team1, Team2, Match_date, Match_time, venue FROM Feature_Match ORDER BY Match_date ASC";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            out.println("<html><body style='font-family:Arial;'>");
            out.println("<h2 style='text-align:center;'>Upcoming Matches</h2>");
            out.println("<table border='1' cellpadding='10' style='margin:auto;'>");
            out.println("<tr><th>Team 1</th><th>Team 2</th><th>Date</th><th>Time</th><th>Venue</th></tr>");

            boolean hasData = false;

            while (rs.next()) {
                hasData = true;
                out.println("<tr>");
                out.println("<td>" + rs.getString("Team1") + "</td>");
                out.println("<td>" + rs.getString("Team2") + "</td>");
                out.println("<td>" + rs.getString("Match_date") + "</td>");
                out.println("<td>" + rs.getString("Match_time") + "</td>");
                out.println("<td>" + rs.getString("venue") + "</td>");
                out.println("</tr>");
            }
            if (!hasData) {
                out.println("<tr><td colspan='4' style='text-align:center;'>No Upcoming Matches</td></tr>");
            }
            out.println("</table>");
            out.println("<br><div style='text-align:center;'><a href='Home'>Back</a></div>");
            out.println("<form class='d-flex' method='post' action='Logout'>");
            out.println("<button class='btn btn-danger'>Logout</button>");
            out.println("</body></html>");

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
        processRequest(request, response);
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
