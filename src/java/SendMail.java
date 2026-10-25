import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SendMail")
public class SendMail extends HttpServlet {

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
        try{
            String to=request.getParameter("mail");
            String sub="Password Reset Link For Your Project";
            String msg="reset_password\n\n"
                    +"http://localhost:8080/Cricket_Live_Score/UpdatePassword?mail="+to;
       
            Mailer.send(to, sub, msg);
            System.out.println("valueeeee==="+to);
            out.println("<h3 style='color:red; text-align:center'>Mail Send Successfully</h3>");
           
            RequestDispatcher rd = request.getRequestDispatcher("forget.html");
            rd.include(request, response);
        }catch(Exception e){
            out.println(e.toString());
        }
    }
}