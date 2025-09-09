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
    /*
    public boolean aggiungi(Persona p) {
    if (controlloPersona(p)) {
        listaCandidati.add(p);
        return true;
    } else {
        return false;
    }
}
    
    public boolean controlloPersona(Persona p) {
    for (Persona candidato : listaCandidati) {
        if (candidato.equals(p)) {
            return false; 
        }
    }
    return true; 
}*/
    public boolean aggiungi(Persona p) {
        boolean check = true;
        if(listaCandidati.isEmpty()){
            listaCandidati.add(p);
        }
        else{
            for(int i = 0; i < listaCandidati.size(); i++){
                Persona persona = listaCandidati.get(i);
                System.out.println("Confronto CodiceFiscale: " + persona.getCodiceFiscale() + " vs " + p.getCodiceFiscale());
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

