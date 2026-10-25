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

@WebServlet("/Show_Match_History")
public class Show_Match_History extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminEmail") == null) {
            response.sendRedirect("Admin.html");
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect Database
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/clg", "root", "root");

            Statement stmt = con.createStatement();

            // Fetch Data from match_history table
            String sql = "SELECT * FROM match_history ORDER BY Match_Date DESC";
            ResultSet rs = stmt.executeQuery(sql);

            // HTML Start
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Match History</title>");
            out.println("</head>");
            out.println("<body style='font-family:Arial;'>");

            out.println("<h2 style='text-align:center;'>Match History</h2>");

            out.println("<table border='1' cellpadding='10' cellspacing='0' style='margin:auto;'>");

            out.println("<tr style='background-color:lightgray;'>");
            out.println("<th>ID</th>");
            out.println("<th>Team 1</th>");
            out.println("<th>Team 2</th>");
            out.println("<th>Winner</th>");
            out.println("<th>Highest Score</th>");
            out.println("<th>Highest Wicket</th>");
            out.println("<th>Highest Score Player</th>");
            out.println("<th>Highest Wicket Player</th>");
            out.println("<th>Man of the Match</th>");
            out.println("<th>Date</th>");
            out.println("</tr>");

            boolean hasData = false;

            while (rs.next()) {
                hasData = true;

                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("Team1") + "</td>");
                out.println("<td>" + rs.getString("Team2") + "</td>");
                out.println("<td>" + rs.getString("Winner_Team") + "</td>");
                out.println("<td>" + rs.getInt("Highest_Score") + "</td>");
                out.println("<td>" + rs.getInt("Highest_Wicket") + "</td>");
                out.println("<td>" + rs.getString("Highest_Score_Player") + "</td>");
                out.println("<td>" + rs.getString("Highest_Wicket_Player") + "</td>");
                out.println("<td>" + rs.getString("Man_of_the_Match") + "</td>");
                out.println("<td>" + rs.getDate("Match_Date") + "</td>");
                out.println("</tr>");
            }

            if (!hasData) {
                out.println("<tr>");
                out.println("<td colspan='10' style='text-align:center;'>No Match History Available</td>");
                out.println("</tr>");
            }

            out.println("</table>");

            out.println("<br><div style='text-align:center;'>");
            out.println("<a href='Admin_Home'>Back</a>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");

            // Close Connection
            con.close();

        } catch (Exception e) {
            out.println("<h3 style='color:red;text-align:center;'>Error: " + e.getMessage() + "</h3>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Shows Match History";
    }
}
