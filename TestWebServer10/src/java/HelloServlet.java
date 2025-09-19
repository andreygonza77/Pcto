/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fisitronpassdynamics
 */
public class HelloServlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HelloServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HelloServlet at " + request.getContextPath() + "</h1>");
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
        /*
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html><body>");
    out.println("<h1>Ciao dal Servlet!</h1>");
    out.println("</body></html>");*/


    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String content = request.getParameter("link");
    
    out.println("<html><head>"
            + "<title>WebServer con Java</title>\n" +
"    <meta charset=\"UTF-8\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <link rel=\"icon\" type=\"image/x-icon\" href=\"https://upload.wikimedia.org/wikipedia/it/2/2e/Java_Logo.svg\">\n" +
"    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB\" crossorigin=\"anonymous\">"
            + "</head><body>");
    out.println(" <div class=\"container mt-5 text-center\">\n" +
"    <h1>Benvenuto nel WebServer realizzato con Java e Apache Tomcat</h1>\n" +
"\n" +
"    <!--GET-->\n" +
"    <h2>Get: </h2>\n" +
"    <form action=\"./HelloServlet\" method=\"get\">\n" +
"         <input type=\"text\" name=\"link\" placeholder=\"Inserisci testo\" required>\n" +
"         <input type=\"submit\" value=\"Manda\">\n" +
"    </form>\n" +
"\n" +
"    <div class=\"row\">\n" +
"        <div class=\"col-12 col-md-2 mb-3\">\n" +
"            <button class=\"btn btn-lg w-100\">CH6</button>\n" +
"        </div>\n" +
"        <div class=\"col-12 col-md-2 mb-3\">\n" +
"            <button class=\"btn btn-lg w-100\">CH5</button>\n" +
"        </div>\n" +
"        <div class=\"col-12 col-md-2 mb-3\">\n" +
"            <button class=\"btn btn-lg w-100\">CH4</button>\n" +
"        </div>\n" +
"        <div class=\"col-12 col-md-2 mb-3\">\n" +
"            <button class=\"btn btn-lg w-100\">CH3</button>\n" +
"        </div>\n" +
"        <div class=\"col-12 col-md-2 mb-3\">\n" +
"            <button class=\"btn btn-lg w-100\">CH2</button>\n" +
"        </div>\n" +
"        <div class=\"col-12 col-md-2 mb-3\">\n" +
"            <button class=\"btn btn-lg w-100\">CH1</button>\n" +
"        </div>\n" +
"    </div>\n" +
"    <p> Il contenuto che hai scritto è: " + content + "</p>" +            
"    </body></html>" +
"    </div>");
    
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
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */}

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
