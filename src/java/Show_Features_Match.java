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
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ROHIT PARMAR
 */
@WebServlet(urlPatterns = {"/Show_Features_Match"})
public class Show_Features_Match extends HttpServlet {

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
//        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminEmail") == null) {
            response.sendRedirect("Admin.html");
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            //  Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clg", "root", "root");

            Statement stmt = con.createStatement();
            String sql = "SELECT id, Team1, Team2, Match_date, Match_time, venue " + "FROM Feature_Match "
                    + "WHERE Team1 IS NOT NULL AND Team2 IS NOT NULL AND Match_date IS NOT NULL "
                    + "ORDER BY Match_date ASC, Match_time ASC";
            ResultSet rs = stmt.executeQuery(sql);

            out.println("<html>");
            out.println("<body style='font-family:Arial;'>");
            out.println("<body style='font-family:Arial; "
            + "background-image: url(\"" + request.getContextPath() + "/Images/cricket.jpg\"); "
            + "background-size: cover; "
            + "background-repeat: no-repeat; "
            + "background-attachment: fixed;'>");

            
            out.println("<h2 style='text-align:center;'>Show_Features_Matches</h2>");
            out.println("<table border='1' cellpadding='10' style='margin:auto;'>");
            out.println("<tr><th>ID</th><th>Team 1</th><th>Team 2</th><th>Date</th><th>Time</th><th>Venue</th><th>Edit</th><th>Delete</th></tr>");

            while (rs.next()) {
                int id = rs.getInt("id");
                String team1 = rs.getString("Team1");
                String team2 = rs.getString("Team2");
                String date = rs.getString("Match_date");
                String time = rs.getString("Match_time");
                String venue = rs.getString("venue");

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + team1 + "</td>");
                out.println("<td>" + team2 + "</td>");
                out.println("<td>" + date + "</td>");
                out.println("<td>" + time + "</td>");
                out.println("<td>" + venue + "</td>");
                // Edit button
                out.println("<td style='text-align:center; width:80px;'>"
                        + "<a href='Edit_Match?id=" + id + "'>"
                        + "<button type='button' style='width:70px; height:30px;'>Edit</button>"
                        + "</a></td>");

                // Delete button
                out.println("<td style='text-align:center; width:90px;'>"
                        + "<form method='post' action='Delete_Match' onsubmit=\"return confirm('Are you sure you want to delete this match?');\">"
                        + "<input type='hidden' name='id' value='" + id + "'>"
                        + "<button type='submit' style='width:70px; height:30px;'>Delete</button>"
                        + "</form></td>");

//                out.println("<td></td>");
                out.println("</tr>");
            }
            out.println("</table>");
//            out.println("<button class='btn btn-danger'>Logout</button>");
            out.println("<br><div style='text-align:center;'><a href='Admin_Home'>Back</a></div>");
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
