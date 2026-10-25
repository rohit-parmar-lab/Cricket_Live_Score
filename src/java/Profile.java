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

@WebServlet("/Profile")
public class Profile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            String username = session.getAttribute("username").toString();
            String email = "";

            // Fetch user email from database
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/clg", "root", "root");
                PreparedStatement ps = con.prepareStatement(
                        "SELECT email FROM user WHERE name=?");
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    email = rs.getString("email");
                }
                con.close();
            } catch (Exception ex) {
                out.println("<p style='color:red'>" + ex.toString() + "</p>");
            }

            // HTML with Bootstrap
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<title>Profile Page</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css' rel='stylesheet'>");
            out.println("<style>");
            out.println("body { background-color: #f0f2f5; }");
            out.println(".profile-card { max-width: 600px; margin: 50px auto; padding: 30px; border-radius: 15px; background: #fff; box-shadow: 0 8px 20px rgba(0,0,0,0.2); }");
            out.println(".profile-card h2 { margin-bottom: 20px; }");
            out.println(".profile-item { margin-bottom: 15px; font-size: 18px; }");
            out.println(".edit-btn { margin-top: 20px; }");
            out.println("</style>");
            out.println("</head><body>");

            // Navbar
            out.println("<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>");
            out.println("<div class='container-fluid'>");
            out.println("<a class='navbar-brand' href='Home'>MyDashboard</a>");
            out.println("<ul class='navbar-nav me-auto mb-2 mb-lg-0'>");
            out.println("<li class='nav-item'><a class='nav-link' href='Home'>Home</a></li>");
            out.println("<li class='nav-item'><a class='nav-link active' href='Profile'>Profile</a></li>");
            out.println("</ul>");
            out.println("<form class='d-flex' method='post' action='Logout'>");
            out.println("<button class='btn btn-danger'>Logout</button>");
            out.println("</form>");
            out.println("</div></nav>");

            // Profile card
            out.println("<div class='profile-card text-center'>");
            out.println("<i class='fas fa-user-circle fa-5x' style='color:#007bff;'></i>");
            out.println("<h2>" + username + "</h2>");
            out.println("<div class='profile-item'><strong>Email:</strong> " + email + "</div>");
//            out.println("<div class='profile-item'><strong>Member since:</strong> 2023</div>");
            out.println("<a href='Edit' class='btn btn-primary edit-btn'>Edit Profile</a>");
            out.println("<a href='Show' class='btn btn-primary edit-btn'>show database</a>");
            out.println("<a href='Home' class='btn btn-primary mt-3'>Back </a>");

            out.println("</div>");

            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("</body></html>");

        } else {
            // If session is null or expired
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
