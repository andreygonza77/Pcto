/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package httpc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import javax.swing.*;
import java.net.http.*;


// RELE[n][0/1]
/**
 *
 * @author fisitronpassdynamics
 */
public class Gui extends JFrame {
    private JTextField link;
    private JButton get, post, relay1, relay2, relay3, relay4, relay5, relay6;
    private JScrollPane areaResponse;
    private JTextArea response;
    private String responseBody;
    private JToggleButton[] releButtons = new JToggleButton[6];
    
    //POST
    private JTextField accensioneRele, numRele;
    
    public String getAccensioneRele() {
        return accensioneRele.getText();
    }

    public void setAccensioneRele(String accensioneRele) {
        this.accensioneRele.setText(accensioneRele);
    }
    
    public String getNumRele() {
        return numRele.getText();
    }
    
    public void setNumRele(String numRele) {
        this.numRele.setText(numRele);
    }
    
    String urlGet = "http://10.100.0.77/get_rele_status";
    String urlPost = "http://10.100.0.77/set_rele";
    
    
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
        relay1 = new JButton();
        relay2 = new JButton();
        relay3 = new JButton();
        relay4 = new JButton();
        relay5 = new JButton();
        relay6 = new JButton();
        
        JPanel relays = new JPanel();
        relays.add(relay1); relays.add(relay2); relays.add(relay3); 
        relays.add(relay4); relays.add(relay5); relays.add(relay6); 
        
        JPanel input = new JPanel();
        input.setLayout(new GridLayout(3, 1));
        JPanel output = new JPanel();
        output.setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new BorderLayout());
        JPanel inputRele = new JPanel();
        numRele = new JTextField("Inserisci numero del Rele [1-6]");
        accensioneRele = new JTextField("Inserisci 1 per accenderlo, 0 spengerlo");
          
        inputRele.add(numRele); inputRele.add(accensioneRele);
        
        
        input.add(link); input.add(get); input.add(post);
        
        output.add(areaResponse, BorderLayout.CENTER);
        output.add(relays, BorderLayout.SOUTH);
        
        inputPanel.add(input, BorderLayout.NORTH);
        inputPanel.add(inputRele, BorderLayout.CENTER);
        
        // FisitronHUB 
        JPanel relePanel = new JPanel(new GridLayout(1, 6, 5, 5));
        for (int i = 0; i < 6; i++) {
            int releNum = i + 1;
            releButtons[i] = new JToggleButton("R" + releNum);
            releButtons[i].setFocusPainted(false);
            releButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean isOn = releButtons[releNum - 1].isSelected();
                    String command = String.format("RELE[%d][%d]", releNum, isOn ? 1 : 0);
                    link.setText(urlPost);
                    setData(command);
                    setColor(getStatus());
                }
            });
            relePanel.add(releButtons[i]);
        }
        //
        
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
                        setData(getCommand());
                    }
                }
            }
        });
        
        
        
        frmContentPane.setLayout(new BorderLayout());
        frmContentPane.add(inputPanel, BorderLayout.NORTH);
        frmContentPane.add(output, BorderLayout.CENTER);
        frmContentPane.add(relePanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public void getData(){
        String urlString = link.getText().trim();
        if (urlString.isEmpty() || !isValidUrl(urlString)) {
            setResponse("Errore: inserire un URL valido");
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
            System.out.println("Risposta: " + response.body());
            System.out.println("Versione: " + response.version());
            System.out.println("Header: " + response.headers());
        
            setResponse("Stato codice: " + response.statusCode() + "\nRisposta: " + response.body() + "\nVersione: " + response.version());
        }
        catch(Exception e){
            e.printStackTrace();
            setResponse("Errore: " + e.getMessage());
        }
    }    
   
    public String getCommand(){
        String numRele = getNumRele().trim();
        String accensioneRele = getAccensioneRele().trim();
        
        if (!numRele.matches("[1-6]")) {
            setResponse("Errore: il numero del relè deve essere tra 1 e 6.");
            return "";
        }
        
        if (!accensioneRele.equals("0") && !accensioneRele.equals("1")) {
            setResponse("Errore: inserisci 0 per spegnere o 1 per accendere.");
            return "";
        }
        
        return String.format("RELE[%s][%s]", numRele, accensioneRele);
    }
    
    public void setData(String s){
        String urlString = link.getText().trim();
        if (urlString.isEmpty() || !isValidUrl(urlString)) {
            setResponse("Errore: inserire un URL valido");
            return;
        }
        
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(urlString))
                            .header("content-type", "text/html")
                            .POST(HttpRequest.BodyPublishers.ofString(s))
                            .version(HttpClient.Version.HTTP_1_1)
                            .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Stato codice: " + response.statusCode());
            System.out.println("Risposta: " + response.body()); 
            System.out.println("Versione: " + response.version());
            
            setResponse("Stato codice: " + response.statusCode() + "\nUltimo rele acceso: " + response.body() + "\nVersione: " + response.version());
        }
        catch(Exception e){
            e.printStackTrace();
            setResponse("Errore: " + e.getMessage());
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
    
    public boolean[] getStatus(){
        boolean[] status = {false, false, false, false, false, false};
        String r[] = {"0,0,0,0,0,0"};
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(urlGet))
                            .GET()
                            .version(HttpClient.Version.HTTP_1_1)
                            .build();
        try{ 
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            r = response.body().split(",");
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Errore");
        }   
        for(int i = 0; i < r.length; i++){
            status[i] = r[i].trim().equals("1");
        }
        
        return status;
    }
    public void setColor(boolean[] status){
        JButton[] btns = {relay1, relay2, relay3, relay4, relay5, relay6};
        for(int i = 0; i < btns.length; i++){
            if(status[i])
                btns[i].setBackground(Color.GREEN);
            else btns[i].setBackground(Color.red);
        }
    }
}