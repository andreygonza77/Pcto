/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guiesempio1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author fisitronpassdynamics
 */
public class Listener implements ActionListener {
    private Gui g;
    
    public Listener(Gui g){
        this.g = g;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Persona p;
        if(e.getSource() instanceof JButton){
            JButton b = (JButton) e.getSource();
            if(b.getText().equals("Invia")){
                String nome = g.getNome();
                String cognome = g.getCognome();
                String dataNascita = g.getDataNascita();
                String codiceFiscale = g.getCodiceFiscale();
                
                if (nome.isEmpty() || cognome.isEmpty() || dataNascita.isEmpty() || codiceFiscale.isEmpty()) {
                 g.setInfo("Status aggiunta di persona: DATI MANCANTI");
                return;
                }

                p = new Persona(nome, cognome, codiceFiscale, dataNascita);
                
                if(g.getCandidati().aggiungi(p)){
                    g.setNome(""); g.setCognome(""); g.setCodiceFiscale(""); g.setDataNascita("");
                    g.setInfo("Status aggiunta di persona: PERSONA AGGIUNTA");
                }
                else g.setInfo("Status aggiunta di persona: PERSONA NON AGGIUNTA");
            }
            if(b.getText().equals("Cancella")){
                g.setNome(""); g.setCognome(""); g.setCodiceFiscale(""); g.setDataNascita("");
            }
        }
    }
    
    
}
