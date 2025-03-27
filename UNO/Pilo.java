package UNO;

import java.util.*;

public class Pilo {
    private Stack<Carta> cartes;

    public Pilo() {
        cartes = new Stack<>();
    }

    public Stack<Carta> getCartes() {
        return cartes;
    }

    public void addCarta(Carta carta) {
        cartes.push(carta);
    }

    public Carta consultarCarta() {
        return cartes.peek();
    }

    public Carta agafarCarta() {
        return cartes.pop();
    }
}
