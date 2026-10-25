
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/Admin_Login"})
public class Admin_Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        

        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");

        // Hardcoded admin credentials
        String adminEmail = "rohitp4460@gmail.com";
        String adminPwd = "aaru";

        if (email.equals(adminEmail) && pwd.equals(adminPwd)) {

            HttpSession session = request.getSession();
            session.setAttribute("adminEmail", email);

            response.sendRedirect("Admin_Home");

        } else {  
            response.sendRedirect("Admin.html?error=1");
        }

    }
}
