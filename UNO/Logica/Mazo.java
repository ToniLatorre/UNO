package uno.Logica;

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
            cartes.push(new Carta(0, color));

            for (int i = 1; i <= 9; i++) {
                cartes.push(new Carta(i, color));
                cartes.push(new Carta(i, color));
            }

            for (int i = 0; i < 2; i++) {
                cartes.push(new Carta(-1, color, TipusCarta.Prohibit));
                cartes.push(new Carta(-1, color, TipusCarta.CanviSentit));
                cartes.push(new Carta(-1, color, TipusCarta.Mes2));
            }
        }

        for (int i = 0; i < 4; i++) {
            cartes.push(new Carta(-1, null, TipusCarta.CanviColor));
        }


        for (int i = 0; i < 4; i++) {
            cartes.push(new Carta(-1, null, TipusCarta.Mes4));
        }
    }

    public Stack<Carta> getCartes() {
        return cartes;
    }

    public Carta agafarCarta() {
        return cartes.pop();
    }

    public void reiniciar(Pilo pilo) {
        Carta cartaSuperior = pilo.consultarCarta();
        pilo.agafarCarta();

        while (!pilo.getCartes().isEmpty()) {
            cartes.push(pilo.agafarCarta());
        }

        pilo.addCarta(cartaSuperior);

        barrejar();
    }

    public void barrejar() {
        Collections.shuffle(cartes);
    }

    public boolean esBuit() {
        return cartes.isEmpty();
    }
}