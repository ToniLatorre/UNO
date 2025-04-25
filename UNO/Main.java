package uno;

import uno.Logica.*;
import uno.UI.*;

public class Main {
    public static void main(String[] args) {
        Carta c1 = new Carta(1, Color.Groc);
        Carta c2 = new Carta(3, Color.Vermell);
        Carta c3 = new Carta(6, Color.Blau);
        Carta c4 = new Carta(9, Color.Verd);
        Carta c5 = new Carta(4, Color.Vermell);
        Carta c6 = new Carta(7, Color.Vermell);
        Carta c7 = new Carta(2, Color.Blau);
        Carta c8 = new Carta(5, Color.Verd);

        Jugador j1 = new Jugador("Jugador 1");
        j1.addCarta(c1);
        j1.addCarta(c2);
        j1.addCarta(c3);
        j1.addCarta(c4);

        Jugador j2 = new Jugador("Jugador 2");
        j1.addCarta(c5);
        j1.addCarta(c6);
        j1.addCarta(c7);
        j1.addCarta(c8);

        for (Carta c : j1.getCartes()) {
            UI.mostrarCarta(c);
        }

        for (Carta c : j2.getCartes()) {
            UI.mostrarCarta(c);
        }

        UI.mostrarCartes(j1.getCartes());
    }
}