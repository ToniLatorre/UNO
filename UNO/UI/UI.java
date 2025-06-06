package uno.UI;

import java.util.*;
import uno.Logica.Carta;
import uno.Logica.TipusCarta;

public class UI {
    // ANSI escape codes
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String WHITE = "\u001B[37m";

    private static String pintarCarta(Carta carta) {
        String color = WHITE;

        if (carta.getColor() != null) {
            switch (carta.getColor()) {
                case Groc:
                    color = YELLOW;
                    break;
                case Vermell:
                    color = RED;
                    break;
                case Blau:
                    color = BLUE;
                    break;
                case Verd:
                    color = GREEN;
                    break;
            }
        }

        String cartaText;
        if (carta.getTipus() == TipusCarta.NORMAL) {
            cartaText = String.format("""
                %s┌─────────┐%s
                %s│ %d       │%s
                %s│         │%s
                %s│   UNO   │%s
                %s│         │%s
                %s│       %d │%s
                %s└─────────┘%s""",
                    color, RESET,
                    color, carta.getNumero(), RESET,
                    color, RESET,
                    color, RESET,
                    color, RESET,
                    color, carta.getNumero(), RESET,
                    color, RESET);
        } else {
            // Special card display
            String simbol = "";
            switch (carta.getTipus()) {
                case Prohibit:
                    simbol = "✕";
                    break;
                case CanviSentit:
                    simbol = "->";
                    break;
                case Mes2:
                    simbol = "+2";
                    break;
                case CanviColor:
                    simbol = "[]";
                    break;
                case Mes4:
                    simbol = "+4";
                    break;
                default:
                    simbol = "?";
            }

            cartaText = String.format("""
                %s┌─────────┐%s
                %s│ %s       │%s
                %s│         │%s
                %s│   %s   │%s
                %s│         │%s
                %s│       %s │%s
                %s└─────────┘%s""",
                    color, RESET,
                    color, simbol, RESET,
                    color, RESET,
                    color, simbol, RESET,
                    color, RESET,
                    color, simbol, RESET,
                    color, RESET);
        }

        return cartaText;
    }

    public static void mostrarCarta(Carta carta) {
        System.out.println(pintarCarta(carta));
    }

    public static void mostrarCartes(ArrayList<Carta> cartes) {
        int quantitat = cartes.size();
        String[][] cartesPintades = new String[quantitat][];

        for (int i = 0; i < quantitat; i++) {
            cartesPintades[i] = pintarCarta(cartes.get(i)).split("\n");
        }

        int altura = cartesPintades[0].length;

        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < quantitat; j++) {
                System.out.print(cartesPintades[j][i] + "  ");
            }
            System.out.println();
        }
        System.out.print("   ");
        for (int j = 0; j < quantitat; j++) {
            System.out.printf("(%2s)         ", j);
        }
        System.out.println();
    }
}