import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Home")
public class Home extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null) {
            String username = session.getAttribute("username").toString();

            // HTML with Bootstrap
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<title>Home Page</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css' rel='stylesheet'>");
            out.println("<style>");
            out.println("body { background-color: #f0f2f5; }");
            out.println(".home-card { max-width: 600px; margin: 50px auto; padding: 30px; border-radius: 15px; background: #fff; box-shadow: 0 8px 20px rgba(0,0,0,0.2); text-align: center; }");
            out.println(".home-card h1 { margin-bottom: 20px; }");
            out.println(".home-card p { font-size: 18px; margin-bottom: 20px; }");
            out.println(".btn-home { margin: 5px; }");
            out.println("</style>");
            out.println("</head><body>");

            // Navbar
            out.println("<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>");
            out.println("<div class='container-fluid'>");
            out.println("<a class='navbar-brand' href='Home'>Dashboard</a>");
            out.println("<ul class='navbar-nav me-auto mb-2 mb-lg-0'>");
            out.println("<li class='nav-item'><a class='nav-link active' href='Home'>Home</a></li>");
            out.println("<li class='nav-item'><a class='nav-link' href='Upcoming_Match'>Upcoming_Match</a></li>");
            out.println("<li class='nav-item'><a class='nav-link' href='Match_History'>Match_history</a></li>");

            out.println("</ul>");
            // Logout button right corner
            out.println("<form class='d-flex' method='post' action='Logout'>");
            out.println("<button class='btn btn-danger'>Logout</button>");
            out.println("</form>");
            out.println("</div></nav>");

            // Home content card
            out.println("<div class='home-card'>");
            out.println("<i class='fas fa-home fa-5x' style='color:#2196F3;'></i>");
            out.println("<h1>Welcome, " + username + "!</h1>");
            out.println("<p>This is your Home Page. Use the buttons below to navigate.</p>");
            out.println("<a href='Profile' class='btn btn-primary btn-home'>Profile</a>");
//            out.println("<a href='Show_Register' class='btn btn-success btn-home'>View Users</a>");
            out.println("<a href='Upcoming_Match' class='btn btn-success btn-home'>Upcoming Matches</a>");
            out.println("<a href='Match_History' class='btn btn-secondary btn-home'>Match_history </a>");
                
            out.println("</div>");

           RequestDispatcher rd=request.getRequestDispatcher("Video.jsp");
            rd.include(request, response);
            
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
