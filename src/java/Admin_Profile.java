import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Admin_Profile")
public class Admin_Profile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        //Session Check
        if(session == null || session.getAttribute("adminEmail") == null){
            response.sendRedirect("Admin.html");
            return;
        }

        String email = (String) session.getAttribute("adminEmail");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Admin Profile</title>");
        out.println("<style>");
        out.println("body{font-family:Arial; background:#f4f6f9; text-align:center;}");
        out.println(".card{margin:100px auto; width:350px; padding:20px;");
        out.println("background:white; box-shadow:0 0 10px gray; border-radius:8px;}");
        out.println("h2{color:#2c3e50;}");
        out.println("a{display:inline-block; margin-top:15px; padding:10px 15px;");
        out.println("background:#2980b9; color:white; text-decoration:none; border-radius:5px;}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<div class='card'>");
        out.println("<h2>Admin Profile</h2>");
        out.println("<p><b>Email:</b> " + email + "</p>");
        out.println("<p><b>Password:</b> ********</p>");
        out.println("<a href='Admin_Home'>Back to Home</a>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}
