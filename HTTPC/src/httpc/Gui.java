/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package httpc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import javax.swing.*;
import java.net.http.*;

/**
 *
 * @author fisitronpassdynamics
 */
public class Gui extends JFrame {
    private JTextField link;
    private JButton get;
    private JButton post;
    private JScrollPane areaResponse;
    private JTextArea response;
    private String responseBody;
    
    
    //POST
    private String value1, value2, value3, value4, value5, value6; //futuro input dell'utente
    private JRadioButton button1, button2, button3, button4, button5, button6; // 
    
    //costanti
    //final String v1 = "1";
    //String fi
    String urlGet = "http:/10.100.0.77/get_rele_status";
    String urlPost = "http:/10.100.0.77/set_rele";
    
    public Gui() {
        super("Test HttpClient");
        Container frmContentPane = this.getContentPane();
        this.setSize(700, 400);
        link = new JTextField("Inserisci il link");
        get = new JButton("GET");
        post = new JButton("POST");
        response = new JTextArea("""
                                 Stato codice: x
                                 Risposta body: x
                                 Versione: x""");
        areaResponse = new JScrollPane(response);
        JPanel input = new JPanel();
        input.setLayout(new GridLayout(3, 1));
        JPanel output = new JPanel();
        output.setLayout(new BorderLayout());
        
        input.add(link); input.add(get); input.add(post);
        output.add(areaResponse, BorderLayout.CENTER);
        
        get.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(ae.getSource() instanceof JButton){
                    JButton b = (JButton)ae.getSource();
                    if(b.getText().equals("GET")){
                        getData();
                    }
                }
            }
        });
        
        post.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(ae.getSource() instanceof JButton){
                    JButton b = (JButton)ae.getSource();
                    if(b.getText().equals("POST")){
                        setData();
                    }
                }
            }
        
        
        });
        
        frmContentPane.setLayout(new BorderLayout());
        frmContentPane.add(input, BorderLayout.NORTH);
        frmContentPane.add(output, BorderLayout.CENTER);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public void getData(){
        String urlString = link.getText().trim();
        if (urlString.isEmpty() || !isValidUrl(urlString)) {
            setResponse("Per favore, inserisci un URL valido");
            return;
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(urlString))
                            .GET()
                            .version(HttpClient.Version.HTTP_1_1)
                            .build();
        try{ 
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Stato codice: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            System.out.println("Version: " + response.version());
        
            setResponse("Stato codice: " + response.statusCode() + "\nRisposta body: " + response.body() + "\nVersione: " + response.version());
        }
        catch(Exception e){
            e.printStackTrace();
            setResponse("Errore durante la richiesta: " + e.getMessage());
        }
    }
    
    public void setData(){
        String urlString = link.getText().trim();
        if (urlString.isEmpty() || !isValidUrl(urlString)) {
            setResponse("Per favore, inserisci un URL valido");
            return;
        }
        
        String v1 = "1";
        String v2 = "1";
        String v3 = "1";
        String v4 = "1";
        String v5 = "1";
        String v6 = "0";
        
        String values = v1 + "," + v2 + "," + v3 + "," + v4 + "," + v5 + "," + v6;
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(urlString))
                            .POST(HttpRequest.BodyPublishers.ofString(values))
                            .version(HttpClient.Version.HTTP_1_1)
                            .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Stato codice: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            System.out.println("Version: " + response.version());
            
            setResponse("Stato codice: " + response.statusCode() + "\nRisposta body: " + response.body() + "\nVersione: " + response.version());
        }
        catch(Exception e){
            e.printStackTrace();
            setResponse("Errore durante la richiesta: " + e.getMessage());
        }
    }

    public void setResponse(String response) {
        this.response.setText(response);
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

    
    
    
            
           
    
