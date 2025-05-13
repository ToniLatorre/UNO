package uno.Logica;

import java.util.*;

public class Ordre {
    private final List<Jugador> jugadors = new ArrayList<>();
    private int indexActual = 0;
    private boolean sentitHorari = true;

    public void passarTorn() {
        if (sentitHorari) {
            indexActual = (indexActual + 1) % jugadors.size();
        } else {
            indexActual = (indexActual - 1 + jugadors.size()) % jugadors.size();
        }
    }

    public void canviarSentit() {
        sentitHorari = !sentitHorari;
        System.out.println("S'ha canviat el sentit del joc!");
    }

    public Jugador getJugadorActiu() {
        return jugadors.get(indexActual);
    }

    public void crearJugador(String nom) {
        jugadors.add(new Jugador(nom));
    }

    public void barrejarOrdre() {
        Collections.shuffle(jugadors);
    }

    public boolean getSentitHorari() {
        return sentitHorari;
    }
}