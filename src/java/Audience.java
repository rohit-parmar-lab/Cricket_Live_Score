import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet(urlPatterns = {"/Audience"})
public class Audience extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null) {
            String username = (String) session.getAttribute("username");

            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<title>Audience Page</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css' rel='stylesheet'>");
            out.println("<style>");
            out.println("body { background-color: #f0f2f5; }");
            out.println(".container { max-width: 900px; margin: 50px auto; }");
            out.println(".card { margin-bottom: 20px; border-radius: 15px; box-shadow: 0 6px 15px rgba(0,0,0,0.1); }");
            out.println(".card-header { background-color: #2196F3; color: white; font-weight: bold; }");
            out.println(".btn-audience { margin: 5px; }");
            out.println("</style>");
            out.println("</head><body>");

            // Navbar
            out.println("<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>");
            out.println("<div class='container-fluid'>");
            out.println("<a class='navbar-brand' href='Home'>Dashboard</a>");
            out.println("<ul class='navbar-nav me-auto mb-2 mb-lg-0'>");
            out.println("<li class='nav-item'><a class='nav-link active' href='Audience'>Audience</a></li>");
            out.println("<li class='nav-item'><a class='nav-link' href='Profile'>Profile</a></li>");
            out.println("</ul>");
            out.println("<form class='d-flex' method='post' action='Logout'>");
            out.println("<button class='btn btn-danger'>Logout</button>");
            out.println("</form>");
            out.println("</div></nav>");

            out.println("<div class='container text-center'>");
            out.println("<h2 class='my-4'>Welcome, " + username + "!</h2>");

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clg", "root", "root");

                // Upcoming Matches (future dates)
                PreparedStatement psUpcoming = con.prepareStatement(
                        "SELECT match_name, match_date, venue FROM matches WHERE match_date >= CURDATE() ORDER BY match_date ASC");
                ResultSet rsUpcoming = psUpcoming.executeQuery();

                out.println("<div class='card'>");
                out.println("<div class='card-header'><i class='fas fa-calendar-alt'></i> Upcoming Matches</div>");
                out.println("<div class='card-body'>");

                boolean hasUpcoming = false;
                while (rsUpcoming.next()) {
                    hasUpcoming = true;
                    String name = rsUpcoming.getString("match_name");
                    String date = rsUpcoming.getString("match_date");
                    String venue = rsUpcoming.getString("venue");

                    out.println("<p><strong>" + name + "</strong> - " + date + " at " + venue + "</p>");
                }
                if (!hasUpcoming) {
                    out.println("<p>No upcoming matches.</p>");
                }
                out.println("</div></div>");

                // Matches History (past dates)
                PreparedStatement psHistory = con.prepareStatement(
                        "SELECT match_name, match_date, venue, result FROM matches WHERE match_date < CURDATE() ORDER BY match_date DESC");
                ResultSet rsHistory = psHistory.executeQuery();

                out.println("<div class='card'>");
                out.println("<div class='card-header'><i class='fas fa-history'></i> Matches History</div>");
                out.println("<div class='card-body'>");

                boolean hasHistory = false;
                while (rsHistory.next()) {
                    hasHistory = true;
                    String name = rsHistory.getString("match_name");
                    String date = rsHistory.getString("match_date");
                    String venue = rsHistory.getString("venue");
                    String result = rsHistory.getString("result");

                    out.println("<p><strong>" + name + "</strong> - " + date + " at " + venue + " | Result: " + result + "</p>");
                }
                if (!hasHistory) {
                    out.println("<p>No match history available.</p>");
                }
                out.println("</div></div>");

                con.close();
            } catch (Exception ex) {
                out.println("<p style='color:red'>" + ex.toString() + "</p>");
            }

            out.println("</div>"); // container
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("</body></html>");

        } else {
            response.sendRedirect("index.jsp");
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
}
