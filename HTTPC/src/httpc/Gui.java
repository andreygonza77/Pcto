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

public class Gui extends JFrame {
    private JTextField link, infoPost;
    private final JButton get, post, relay1, relay2, relay3, relay4, relay5, relay6;
    private final JScrollPane areaResponse;
    private final JTextArea response;
    private JToggleButton[] releButtons = new JToggleButton[6];
   
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
       
        
        JPanel relays = new JPanel(new GridLayout(1, 6, 5, 5));
        relays.add(relay1); relays.add(relay2); relays.add(relay3);
        relays.add(relay4); relays.add(relay5); relays.add(relay6);
        relays.setBackground(Color.BLACK);
        
        JPanel input = new JPanel();
        input.setLayout(new GridLayout(4, 1, 5, 5));
        JPanel output = new JPanel();
        output.setLayout(new BorderLayout());
       
        
        infoPost = new JTextField("Inserire contenuto da inviare");
        JPanel inputPanel = new JPanel(new BorderLayout());
        input.add(link); input.add(get); input.add(post);
        input.add(infoPost);
        
       
        output.add(areaResponse, BorderLayout.CENTER);
        output.add(relays, BorderLayout.SOUTH);
       
        inputPanel.add(input, BorderLayout.NORTH);
        // inputPanel.add(inputRele, BorderLayout.CENTER);
       
        // FisitronHUB
        JPanel relePanel = new JPanel(new GridLayout(1, 6, 5, 5));
        relePanel.setBackground(Color.BLACK);
        for (int i = 0; i < 6; i++) {
            int releNum = 6 - i;
            releButtons[i] = new JToggleButton("CH" + releNum);
            releButtons[i].setFocusPainted(false);
            final int index = i;
            final int releIndex = releNum;
            releButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean isOn = releButtons[index].isSelected();
                    String command = String.format("RELE[%d][%d]", releIndex, isOn ? 1 : 0);
                    link.setText(urlPost);
                    setData(command);
                    setColor(getStatus());
            }
    });

    relePanel.add(releButtons[i]);
}      
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
                        setData(infoPost.getText());
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
        getData();
        setColor(getStatus());
    }
   
    public void getData(){
        String urlString = link.getText();
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

    public void setData(String s){
        String urlString = link.getText();
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
           
            setResponse("Stato codice: " + response.statusCode() + "\nUltimo azione POST: " + response.body() + "\nVersione: " + response.version());
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
        JButton[] btns = {relay6, relay5, relay4, relay3, relay2, relay1};
        for(int i = 0; i < btns.length; i++){
            if(status[i])
                btns[i].setBackground(Color.red);
            else btns[i].setBackground(Color.BLACK);
        }
    }
}