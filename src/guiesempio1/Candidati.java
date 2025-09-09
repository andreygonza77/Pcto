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
    
    public String aggiungi(Persona p){
        if(controlloPersona(p)){
           listaCandidati.add(p);
           return "Candidato aggiunto";
        }
        else{
           return "Candidato già presente"; 
        }
        
    }
    
    public boolean controlloPersona(Persona p){
        boolean check = false;
        for(int i = 0; i < listaCandidati.size(); i++){
            if(listaCandidati.get(i).equals(p))
                check = true;
        }
        return !check;
    }
}
