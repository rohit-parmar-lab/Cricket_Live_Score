import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Add_Match_History")
public class Add_Match_History extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminEmail") == null) {
            response.sendRedirect("Admin.html");
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Get form data
        String team1 = request.getParameter("team1");
        String team2 = request.getParameter("team2");
        String winner = request.getParameter("winner_team");
        String highestScore = request.getParameter("highest_score");
        String highestWicket = request.getParameter("highest_wicket");
        String highestScorePlayer = request.getParameter("highest_score_player");
        String highestWicketPlayer = request.getParameter("highest_wicket_player");
        String manOfTheMatch = request.getParameter("man_of_the_match");
        String dateStr = request.getParameter("matchDate");

        // Basic Validation
        if (team1 == null || team2 == null || team1.isEmpty() || team2.isEmpty()) {
            out.println("<h3 style='color:red;text-align:center;'>Please fill all required fields.</h3>");
            out.println("<div style='text-align:center;'><a href='add_match_history.html'>Go Back</a></div>");
            return;
        }

        try {
            // Convert empty numbers to 0
            if (highestScore == null || highestScore.isEmpty()) {
                highestScore = "0";
            }
            if (highestWicket == null || highestWicket.isEmpty()) {
                highestWicket = "0";
            }

            // Convert Date
            Date matchDate = Date.valueOf(dateStr);  // format must be yyyy-MM-dd

            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect Database
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/clg", "root", "root");

            // Correct Insert Query
            String sql = "INSERT INTO match_history "
                    + "(Team1, Team2, Winner_Team, Highest_Score, Highest_Wicket, "
                    + "Highest_Score_Player, Highest_Wicket_Player, Man_of_the_Match, Match_Date) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, team1);
            ps.setString(2, team2);
            ps.setString(3, winner);
            ps.setInt(4, Integer.parseInt(highestScore));
            ps.setInt(5, Integer.parseInt(highestWicket));
            ps.setString(6, highestScorePlayer);
            ps.setString(7, highestWicketPlayer);
            ps.setString(8, manOfTheMatch);
            ps.setDate(9, matchDate);

            int i = ps.executeUpdate();

            if (i > 0) {
                out.println("<h3 style='color:green;text-align:center;'>Match history added successfully!</h3>");
//                out.println("<div style='text-align:center;'><a href='ShowMatch_History'>View Match History</a></div>");
                
            } else {
                out.println("<h3 style='color:red;text-align:center;'>Error adding match history!</h3>");
            }

            con.close();

        } catch (Exception ex) {
            out.println("<h3 style='color:red;text-align:center;'>Error: " + ex.getMessage() + "</h3>");
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
        return "Servlet to add completed match history";
    }
}
