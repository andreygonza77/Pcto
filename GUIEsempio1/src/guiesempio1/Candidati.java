/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guiesempio1;

import java.util.ArrayList;

/**
 *
 * @author fisitronpassdynamics
 */
public class Candidati {
    ArrayList<Persona> listaCandidati;
    
    public Candidati() {
        listaCandidati = new ArrayList();
    }
    
    public boolean aggiungi(Persona p) {
        boolean check = true;
        if(listaCandidati.isEmpty()){
            listaCandidati.add(p);
        }
        else{
            for(int i = 0; i < listaCandidati.size(); i++){
                Persona persona = listaCandidati.get(i);
                if(persona.getCodiceFiscale().equals(p.getCodiceFiscale())){
                    check = false;
                    break;
                }
            }
        }
        if(check) listaCandidati.add(p);
        return check;
    }
}

