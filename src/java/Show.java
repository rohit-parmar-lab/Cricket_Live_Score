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

@WebServlet("/Show")
public class Show extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("username") == null) {
                response.sendRedirect("index.html"); // Login page
                return;
            }

            String username = session.getAttribute("username").toString(); // Logged-in user

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Your Data</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container mt-5'>");
            out.println("<h2 class='mb-4'>Your Details</h2>");

            out.println("<table class='table table-bordered table-striped'>");
            out.println("<thead class='table-dark'><tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Email</th>");
            out.println("<th>Mobile</th>");
            out.println("<th>Password</th>");
            out.println("</tr></thead>");
            out.println("<tbody>");

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/clg", "root", "root");

                // ✅ Only logged-in user
                PreparedStatement ps = con.prepareStatement("SELECT * FROM user WHERE Name = ?");
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) { // Only one user
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("Id") + "</td>");
                    out.println("<td>" + rs.getString("Name") + "</td>");
                    out.println("<td>" + rs.getString("Email") + "</td>");
                    out.println("<td>" + rs.getString("Mobile") + "</td>");
                    out.println("<td>" + rs.getString("Password") + "</td>");
                    out.println("</tr>");
                } else {
                    out.println("<tr><td colspan='5' class='text-danger'>No data found!</td></tr>");
                }

                con.close();

            } catch (Exception ex) {
                out.println("<tr><td colspan='5' class='text-danger'>Error: " + ex + "</td></tr>");
            }

            out.println("</tbody></table>");
            out.println("<a href='Profile' class='btn btn-primary mt-3'>Back to Profile</a>");
            out.println("</div>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("</body>");
            out.println("</html>");
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
