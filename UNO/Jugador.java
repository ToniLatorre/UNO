package UNO;

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

    public void addCarta(Carta carta) {
        cartes.add(carta);
    }

    public void tirarCarta(Carta carta) {
        cartes.remove(carta);
    }

    public ArrayList<Carta> getCartes() {
        return new ArrayList<>(cartes);
    }
}
