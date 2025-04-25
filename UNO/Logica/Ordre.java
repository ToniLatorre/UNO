package uno.Logica;

import java.util.*;

public class Ordre {
    private final List<Jugador> jugadors = new ArrayList<>();
    private int indexActual = 0;

    public void passarTorn() {
        indexActual = (indexActual + 1) % jugadors.size();
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
}
