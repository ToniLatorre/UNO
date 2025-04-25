package uno.Logica;

public class Regles {
    public static boolean sonCartesCompatibles(Carta c1, Carta c2) {
        return c1.getColor() == c2.getColor() || c1.getNumero() == c2.getNumero();
    }
}

