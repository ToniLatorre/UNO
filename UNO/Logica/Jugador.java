package uno.Logica;

import java.util.*;

public class Jugador {
    private String nom;
    private List<Carta> cartes;

    public Jugador(String nom) {
        this.nom = nom;
        this.cartes = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void tirarCarta(Carta carta, Pilo pilo) {
        if (cartes.remove(carta)) {
            pilo.addCarta(carta);
        }
    }

    public boolean potTirarCarta(Carta cartaAmunt) {
        for (Carta carta : cartes) {
            if (Regles.sonCartesCompatibles(carta, cartaAmunt)) {
                return true;
            }
        }
        return false;
    }

    public int nombreDeCartes(){
        return cartes.size();
    }

    public void robarCarta(Mazo mazo){
        cartes.add(mazo.agafarCarta());
    }

    public ArrayList<Carta> getCartes() {
        return new ArrayList<>(cartes);
    }
}
