
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        try {
            String email = req.getParameter("email");
            String pwd = req.getParameter("pwd");

//        load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //make the connection object
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clg", "root", "root");

//            make preparedstatement object
            PreparedStatement ps = con.prepareStatement("select * from user where email=? and password=?");
            ps.setString(1, email);
            ps.setString(2, pwd);
//        execute the query
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HttpSession hs = req.getSession(true);
                hs.setAttribute("username", rs.getString("name"));
                RequestDispatcher rd = req.getRequestDispatcher("Home");
                rd.forward(req, resp);

            } else {
                req.setAttribute("errorMessage", "Email or password not match");
                RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
                rd.forward(req, resp);
            }

            con.close();
        } catch (Exception ex) {
            out.println(ex.toString());
        }
    }
}
