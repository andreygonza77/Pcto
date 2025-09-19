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
public class PostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlString = request.getParameter("linkPost");
        String info = request.getParameter("contentPost");
        PrintWriter out = response.getWriter();
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
"    <form action=\"./GetServlet\" method=\"get\">\n" +
"         <input type=\"text\" name=\"link\" placeholder=\"Inserisci testo\" required>\n" +
"         <input type=\"submit\" value=\"Manda\">\n" +
"    </form>\n" +
"\n" + "<h2>Post: </h2>\n" +
"    <form action=\"./PostServlet\" method=\"post\">\n" +
"        <input type=\"text\" name=\"linkPost\" placeholder=\"Inserisci link\" required>\n" +
"        <input type=\"text\" name=\"contentPost\" placeholder=\"Inserisci contenuto\" required>\n" +
"        <input type=\"submit\" value=\"Manda\">\n" +
"    </form>" +
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
"    </div>\n");
    
        if (!isValidUrl(urlString)) {
            out.println("Inserire un link valido");
            return;
        }
        HttpClient clientHttp = HttpClient.newHttpClient();
        HttpRequest requestHttp = HttpRequest.newBuilder()
                            .uri(URI.create(urlString))
                            .header("content-type", "text/html")
                            .POST(HttpRequest.BodyPublishers.ofString(info))
                            .version(HttpClient.Version.HTTP_1_1)
                            .build();
        try{
            HttpResponse<String> responseHttp = clientHttp.send(requestHttp, HttpResponse.BodyHandlers.ofString());
            System.out.println("Stato codice: " + responseHttp.statusCode());
            System.out.println("Ultima azione post: " + responseHttp.body());
            System.out.println("Versione: " + responseHttp.version());
            System.out.println("Header: " + responseHttp.headers());
       
            out.println("Stato codice: " + responseHttp.statusCode() + "\n" + "\nRisposta: " + responseHttp.body() + "\n" + "\nVersione: " + responseHttp.version());
        }
        catch(Exception e){
            e.printStackTrace();
            out.println("Errore: " + e.getMessage());
        }
        out.println("</body></html>");
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
