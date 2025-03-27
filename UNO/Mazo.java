package UNO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {
    private List<Carta> cartas;

    public Mazo() {
        cartas = new ArrayList<>();
        getCarta();
        barrejar();
    }

    public void getCarta() {
        for (Color color : Color.values()) {
                for (int i = 0; i <= 9; i++) {
                    cartas.add(new Carta(i, color));
                }
            }
        }

    public void barrejar() {
        Collections.shuffle(cartas);
    }

    public Carta agafarCarta() {
        if (!cartas.isEmpty()) {
            return cartas.remove(0);
        } else {
            System.out.println("El mazo esta buit");
            return null;
        }
    }

    public void mostrarMazo() {
        for (Carta carta : cartas) {
            carta.mostrarCarta();
        }
    }
}
