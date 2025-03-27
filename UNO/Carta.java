package UNO;

import java.util.Random;

public class Carta {
    private int numero;
    public Color color;

    public Carta(int i, Color color) {
        Random random = new Random();
        this.numero = random.nextInt(10);
        this.color = Color.values()[random.nextInt(Color.values().length - 1)];
    }

    public void mostrarCarta() {
        System.out.println("Carta: " + color + " " + numero);
    }
}

