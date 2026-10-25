/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ROHIT PARMAR
 */
@WebServlet(urlPatterns = {"/Edit_Match"})
public class Edit_Match extends HttpServlet {

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
            String idStr = request.getParameter("id");
            if (idStr == null) {
                out.println("<h3>Invalid Match ID</h3>");
                return;
            }
            int id = Integer.parseInt(idStr);

            // Connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clg", "root", "root");
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM Feature_Match WHERE id=" + id;
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String team1 = rs.getString("Team1");
                String team2 = rs.getString("Team2");
                String date = rs.getString("Match_date");
                String time = rs.getString("Match_time");
                String venue = rs.getString("venue");

                // Show edit form
                out.println("<html><body style='font-family:Arial;'>");
                out.println("<h2>Edit Match</h2>");
                out.println("<form method='post' action='Update_Match'>");
                out.println("<input type='hidden' name='id' value='" + id + "'>");
                out.println("Team 1: <input type='text' name='team1' value='" + team1 + "'><br><br>");
                out.println("Team 2: <input type='text' name='team2' value='" + team2 + "'><br><br>");
                out.println("Date: <input type='date' name='match_date' value='" + date + "'><br><br>");
                out.println("Date: <input type='time' name='match_time' value='" + time + "'><br><br>");
                out.println("Venue: <input type='text' name='venue' value='" + venue + "'><br><br>");
                out.println("<input type='submit' value='Update Match'>");
                out.println("</form>");
                out.println("</body></html>");
            } else {
                out.println("<h3>No match found with ID " + id + "</h3>");
            }

            con.close();
        } catch (Exception ex) {
            out.println(ex.toString());
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
