/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guiesempio1;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author fisitronpassdynamics
 */
public class Gui extends JFrame {
    private static final String titoloFinestra = "Esempio";
    private static final int larghezza = 700, altezza = 400;
    
    private JTextField nome, cognome, codiceFiscale, dataNascita;
    private JButton invia, cancella;
    private Candidati listaCandidati;
    
    private int righe = 4, colonne = 1;
    public Gui(){
        super(titoloFinestra);
        Container frmContentPane = this.getContentPane();
        this.setSize(larghezza, altezza);
        listaCandidati = new Candidati();


        JPanel scritte = new JPanel();
        scritte.setLayout(new GridLayout(righe, colonne, 5, 20));
        JLabel scrittaNome = new JLabel("Nome: ");
        JLabel scrittaCognome = new JLabel("Cognome: ");
        JLabel scrittaCodiceFiscale = new JLabel("Codice Fiscale: ");
        JLabel scrittaDataNascita = new JLabel("Data di nascita: ");
        
        scritte.add(scrittaNome); scritte.add(scrittaCognome); 
        scritte.add(scrittaCodiceFiscale); scritte.add(scrittaDataNascita);
        
        
        JPanel input = new JPanel();
        input.setLayout(new GridLayout(righe, colonne, 5, 20));
        nome = new JTextField();
        cognome = new JTextField();
        codiceFiscale = new JTextField();
        dataNascita = new JTextField();

        
        input.add(nome); input.add(cognome); 
        input.add(codiceFiscale); input.add(dataNascita);
        
        JPanel bottoni = new JPanel();
        bottoni.setLayout(new GridLayout(1, 2, 10, 50));
        invia = new JButton("Invia");
        cancella = new JButton("Cancella");
        
        bottoni.add(invia); bottoni.add(cancella);
        
        
        JPanel titolo = new JPanel();
        JLabel scrittaTitolo = new JLabel("Candida persona"); 
        titolo.add(scrittaTitolo, BorderLayout.CENTER);
        frmContentPane.add(titolo, BorderLayout.NORTH);
        frmContentPane.add(scritte, BorderLayout.WEST);
        frmContentPane.add(input, BorderLayout.CENTER);
        frmContentPane.add(bottoni, BorderLayout.SOUTH);
        frmContentPane.add(new JPanel(), BorderLayout.EAST);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
