package uno.Logica;

public class Carta {
    private int numero;
    private Color color;

    public Carta(int numero, Color color) {
        this.numero = numero;
        this.color = color;
    }

    public int getNumero() {
        return numero;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Carta{" + "valor=" + numero + ", color=" + color + '}';
    }

    public void mostrarCarta() {
        System.out.println("Carta: " + color + " " + numero);
    }
}

