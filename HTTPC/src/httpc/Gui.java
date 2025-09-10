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
import java.net.URL;
import javax.swing.*;
import java.net.http.*;

/**
 *
 * @author fisitronpassdynamics
 */
public class Gui extends JFrame {
    private JTextField link;
    private JButton button;
    private JScrollPane areaResponse;
    private JTextArea response;

    public Gui() {
        super("Test HttpClient");
        Container frmContentPane = this.getContentPane();
        this.setSize(700, 400);
        link = new JTextField("Inserisci il link");
        button = new JButton("Invia");
        response = new JTextArea();
        areaResponse = new JScrollPane(response);
        JPanel input = new JPanel();
        input.setLayout(new GridLayout(1, 2));
        JPanel output = new JPanel();
        output.setLayout(new BorderLayout());
        
        input.add(link); input.add(button);
        output.add(areaResponse, BorderLayout.CENTER);
        
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(ae.getSource() instanceof JButton){
                    JButton b = (JButton)ae.getSource();
                    if(b.getText().equals("Invia")){
                        getData();
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
                            .build();
        
        
        try{ // da sistemare
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Stato codice: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            setResponse(response.body());
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

    
    
    
            
           
    
