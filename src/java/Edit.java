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

@WebServlet("/Edit")
public class Edit extends HttpServlet {

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/clg", "root", "root");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null) {
            String username = session.getAttribute("username").toString();

            int userId = 0;
            String name = "", email = "", mobile = "", password = "";

            // Fetch current user data
            try (Connection conn = getConnection()) {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT Id, Name, Email, Mobile, Password FROM user WHERE Name=?");
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    userId = rs.getInt("Id");
                    name = rs.getString("Name");
                    email = rs.getString("Email");
                    mobile = rs.getString("Mobile");
                    password = rs.getString("Password");
                }
            } catch (Exception e) {
                out.println("<p style='color:red;'>" + e.toString() + "</p>");
            }

            // If POST, update the profile
            if (request.getMethod().equalsIgnoreCase("POST")) {
                String newName = request.getParameter("name");
                String newEmail = request.getParameter("email");
                String newMobile = request.getParameter("mobile");
                String newPassword = request.getParameter("password");

                try (Connection conn = getConnection()) {
                    PreparedStatement ps = conn.prepareStatement(
                            "UPDATE user SET Name=?, Email=?, Mobile=?, Password=? WHERE Id=?");
                    ps.setString(1, newName);
                    ps.setString(2, newEmail);
                    ps.setString(3, newMobile);
                    ps.setString(4, newPassword);
                    ps.setInt(5, userId);
                    ps.executeUpdate();

                    // Update session username if name changed
                    session.setAttribute("username", newName);

                    out.println("<p style='color:green; text-align:center;'>Profile updated successfully!</p>");

                    // Update local variables to show updated values in form
                    name = newName;
                    email = newEmail;
                    mobile = newMobile;
                    password = newPassword;

                } catch (Exception ex) {
                    out.println("<p style='color:red; text-align:center;'>" + ex.toString() + "</p>");
                }
            }

            // HTML Form
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<title>Edit Profile</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<style>");
            out.println("body { background-color: #f0f2f5; }");
            out.println(".profile-card { max-width: 600px; margin: 50px auto; padding: 30px; border-radius: 15px; background: #fff; box-shadow: 0 8px 20px rgba(0,0,0,0.2); }");
            out.println(".profile-card h2 { margin-bottom: 20px; }");
            out.println(".form-control { margin-bottom: 15px; }");
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

            // Edit Form Card
            out.println("<div class='profile-card'>");
            out.println("<h2>Edit Profile</h2>");
            out.println("<form method='post' action='Edit'>");

            // ID field (read-only)
            out.println("<label>ID:</label>");
            out.println("<input type='text' class='form-control' value='" + userId + "' readonly>");

            out.println("<label>Name:</label>");
            out.println("<input type='text' name='name' class='form-control' value='" + name + "' required>");

            out.println("<label>Email:</label>");
            out.println("<input type='email' name='email' class='form-control' value='" + email + "' required>");

            out.println("<label>Mobile:</label>");
            out.println("<input type='text' name='mobile' class='form-control' value='" + mobile + "' required>");

            out.println("<label>Password:</label>");
            out.println("<input type='text' name='password' class='form-control' value='" + password + "' required>");

            out.println("<button type='submit' class='btn btn-primary'>Update Profile</button>");
            out.println("<a href='Profile' class='btn btn-secondary ms-2'>Back</a>");

            out.println("</form>");
            out.println("</div>");

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
