package UNO;

import java.util.Random;

public class Carta {
    private int numero;
    private Color color;

    public Carta() {
        Random random = new Random();
        this.numero = random.nextInt(10); // Números del 0 al 9
        this.color = Color.values()[random.nextInt(Color.values().length - 1)]; // Sin contar NEGRO
    }

    public int getNumero() {
        return numero;
    }

    public Color getColor() {
        return color;
    }

    public void mostrarCarta() {
        System.out.println("Carta: " + color + " " + numero);
    }
}

