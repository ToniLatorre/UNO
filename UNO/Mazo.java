package UNO;

import java.util.*;

public class Mazo {
    private Stack<Carta> cartes;

    public Mazo() {
        cartes = new Stack<>();
        inicialitzarMazo();
        barrejar();
    }

    private void inicialitzarMazo() {
        for (Color color : Color.values()) {
            for (int i = 0; i <= 9; i++) {
                cartes.push(new Carta(i, color));
            }
        }
    }

    public Stack<Carta> getCartes() {
        return cartes;
    }

    public Carta agafarCarta() {
        return cartes.pop();
    }

    public void reiniciar(Pilo pilo) {
        while (!pilo.getCartes().isEmpty()) {
            cartes.push(pilo.agafarCarta());
        }
        barrejar();
    }

    public void barrejar() {
        Collections.shuffle(cartes);
    }
}