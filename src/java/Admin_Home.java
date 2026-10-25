/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

//import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ROHIT PARMAR
 */
@WebServlet(urlPatterns = {"/Admin_Home"})
public class Admin_Home extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Admin Home</title>");
            out.println("<style>");
            out.println("body {");
            out.println("    font-family: Arial, sans-serif;");
            out.println("    text-align: center;");
            out.println("    /* Background Image Example */");
//            out.println("    background-image: url('Images/cricket.jpg');"); // aapki image path
            out.println("    background: linear-gradient(rgba(0,0,0,0.6), rgba(0,0,0,0.6)),");
            out.println("                url('" + request.getContextPath() + "/Images/cricket.jpg');");
            out.println("    background-size: cover;");
            out.println("    background-size: cover;");
            out.println("    background-position: center;");
            out.println("    background-attachment: fixed;");
            out.println("    color: #fff;");
            out.println("}");
            out.println(".container {");
            out.println("    margin-top: 50px;");
            out.println("    background-color: rgba(0,0,0,0.6);"); // semi-transparent overlay
            out.println("    padding: 20px;");
            out.println("    border-radius: 10px;");
            out.println("    display: inline-block;");
            out.println("}");
//            out.println(".container {");
//            out.println("    margin-top: 50px;");
//            out.println("    padding: 30px;");
//            out.println("    border-radius: 15px;");
//            out.println("    display: inline-block;");
//            out.println("    color: white;");
//
////            out.println("    background: linear-gradient(rgba(0,0,0,0.6), rgba(0,0,0,0.6)),");
////            out.println("                url('" + request.getContextPath() + "/cricket_1.jpg');");
//            out.println("    background-size: cover;");
//            out.println("    background-position: center;");
//            out.println("    background-repeat: no-repeat;");
//            out.println("}");

            out.println("h1 { color: #ffd700; }"); // gold-ish color
            out.println(".menu a {");
            out.println("    display: block;");
            out.println("    width: 250px;");
            out.println("    margin: 10px auto;");
            out.println("    padding: 12px;");
            out.println("    background: rgba(41, 128, 185,0.8);"); // slightly transparent blue
            out.println("    color: white;");
            out.println("    text-decoration: none;");
            out.println("    border-radius: 5px;");
            out.println("    font-weight: bold;");
            out.println("}");
            out.println(".menu a:hover { background: rgba(31, 102, 144,0.9); }");
            out.println("</style>");

            out.println("</head>");

            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h1>Admin Home page</h1>");
            out.println("<div class='menu'>");
            out.println("<a href='Admin_Profile'>Profile</a>");
            out.println("<a href='Add_Feature_Match'>Add Feature Match</a>");
            out.println("<a href='Show_Features_Match'>Show Features Match</a>");
            out.println("<a href='add_match_history.html'>Add Match History</a>");
            out.println("<a href='Show_Match_History'>Show Match History</a>");
            out.println("<a href='add_advertisement.jsp'>Add_Advertisement </a>");
            out.println("<a href='Logout' style='background:#e74c3c;'>Logout</a>");
            out.println("</div>");

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
