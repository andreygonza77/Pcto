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
    if (controlloPersona(p)) {
        listaCandidati.add(p);
        return true; // Persona aggiunta con successo
    } else {
        return false; // Persona già presente, non aggiunta
    }
}

// Controlla se la persona è già presente nella lista
public boolean controlloPersona(Persona p) {
    for (Persona candidato : listaCandidati) {
        if (candidato.equals(p)) {
            System.out.println("Persona già presente: " + candidato);  // Debug
            return false; // La persona è già presente
        }
    }
    return true; // La persona non è presente
}
}
