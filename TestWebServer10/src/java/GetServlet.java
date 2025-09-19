/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fisitronpassdynamics
 */
public class GetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*"); 
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        String urlString = request.getParameter("link");
        PrintWriter out = response.getWriter();
        out.println("<html><head>"
            + "<title>WebServer con Java</title>\n" +
"    <meta charset=\"UTF-8\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <link rel=\"icon\" type=\"image/x-icon\" href=\"https://upload.wikimedia.org/wikipedia/it/2/2e/Java_Logo.svg\">\n" +
"    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB\" crossorigin=\"anonymous\">"
            + "</head><body class=\"body\">");
    out.println("<div class=\"container mt-5 text-center\">\n" +
"    <h1>Benvenuto nel WebServer</h1>\n" +
"      <h3>Realizzato con Java e Apache Tomcat</h3>" +            
"\n" +
"    <!--GET-->\n" +
"    <h2>Get: </h2>\n" +
"    <form action=\"./GetServlet\" method=\"get\">\n" +
"         <input type=\"text\" name=\"link\" placeholder=\"Inserisci link\" required>\n" +
"         <input type=\"submit\" value=\"Manda\">\n" +
"    </form>\n" +
"\n" + "<h2>Post: </h2>\n" +
"        <form action=\"./PostServlet\" method=\"post\">\n" +
"        <input type=\"text\" name=\"linkPost\" placeholder=\"Inserisci link\" required>\n" +
"        <select name=\"contentPost\" required>\n" +
"        <option value=\"\" disabled selected>Seleziona contenuto</option>\n" +
"        <option value=\"RELE[1][0]\">RELE[1][0]</option>\n" +
"        <option value=\"RELE[1][1]\">RELE[1][1]</option>\n" +
"        <option value=\"RELE[2][0]\">RELE[2][0]</option>\n" +
"        <option value=\"RELE[2][1]\">RELE[2][1]</option>\n" +
"        <option value=\"RELE[3][0]\">RELE[3][0]</option>\n" +
"        <option value=\"RELE[3][1]\">RELE[3][1]</option>\n" +
"        <option value=\"RELE[4][0]\">RELE[4][0]</option>\n" +
"        <option value=\"RELE[4][1]\">RELE[4][1]</option>\n" +
"        <option value=\"RELE[5][0]\">RELE[5][0]</option>\n" +
"        <option value=\"RELE[5][1]\">RELE[5][1]</option>\n" +
"        <option value=\"RELE[6][0]\">RELE[6][0]</option>\n" +
"        <option value=\"RELE[6][1]\">RELE[6][1]</option>\n" +
"    </select>\n" +
"        <input type=\"submit\" value=\"Manda\" class=\"mandaPost\">\n" +
"    </form>\n" +
"    <div class=\"row\">\n" +
"    </div>\n");
    
        if (!isValidUrl(urlString)) {
            out.println("Inserire un link valido");
            return;
        }
        HttpClient clientHttp = HttpClient.newHttpClient();
        HttpRequest requestHttp = HttpRequest.newBuilder()
                            .uri(URI.create(urlString))
                            .GET()
                            .version(HttpClient.Version.HTTP_1_1)
                            .build();
        try{
            HttpResponse<String> responseHttp = clientHttp.send(requestHttp, HttpResponse.BodyHandlers.ofString());
       
            out.println("Stato: " + responseHttp.body());
        }
        catch(Exception e){
            e.printStackTrace();
            out.println("Errore: " + e.getMessage());
        }
        out.println("<form action=\"./PostServlet\" method=\"post\">\n" +
"    <div class=\"text-center mb-3 d-flex justify-content-between\">\n" +
"        <button class=\"ch6 btn btn-lg\">CH6</button>\n" +
"        <button class=\"ch5 btn btn-lg\">CH5</button>\n" +
"        <button class=\"ch4 btn btn-lg\">CH4</button>\n" +
"        <button class=\"ch3 btn btn-lg\">CH3</button>\n" +
"        <button class=\"ch2 btn btn-lg\">CH2</button>\n" +
"        <button class=\"ch1 btn btn-lg\">CH1</button>\n" +
"    </div>\n" +
"</form>"
                + " <script src=\"./script/script.js\"></script>"
                + "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI\" crossorigin=\"anonymous\"></script>" + 
        "</body></html>");
    }
    
    private boolean isValidUrl(String urlString) {
        try {
            URI uri = new URI(urlString);
            return uri.isAbsolute() && (uri.getScheme().equals("http") || uri.getScheme().equals("https"));
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
