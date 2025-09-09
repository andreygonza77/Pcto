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
    private static final String titolofinestra = "Esempio";
    private static final int larghezza = 700, altezza = 500;
    
    private JTextField nome, cognome, codiceFiscale, dataNascita;
    private JButton invia;
    private Candidati listaCandidati;
    
    public Gui(){
        super(titolofinestra);
        Container frmContentPane = this.getContentPane();
        this.setSize(larghezza, altezza);
        listaCandidati = new Candidati();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    
}
