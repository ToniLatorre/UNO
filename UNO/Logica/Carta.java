package uno.Logica;

import uno.Partida;

import java.util.Scanner;

public class Carta {
    private int numero;
    private Color color;
    private TipusCarta tipus;

    public Carta(int numero, Color color) {
        this.numero = numero;
        this.color = color;
        this.tipus = TipusCarta.NORMAL;
    }

    public Carta(int numero, Color color, TipusCarta tipus) {
        this.numero = numero;
        this.color = color;
        this.tipus = tipus;
    }

    public int getNumero() {
        return numero;
    }

    public Color getColor() {
        return color;
    }

    public TipusCarta getTipus() {
        return tipus;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        if (tipus == TipusCarta.NORMAL) {
            return "Carta{" + "valor=" + numero + ", color=" + color + '}';
        } else {
            return "Carta{" + "tipus=" + tipus + ", color=" + color + '}';
        }
    }

    public void mostrarCarta() {
        if (tipus == TipusCarta.NORMAL) {
            System.out.println("Carta: " + color + " " + numero);
        } else {
            System.out.println("Carta: " + color + " " + tipus);
        }
    }

    public void aplicarEfecte(Partida partida) {
        if (tipus == TipusCarta.NORMAL) {
            return;
        }

        switch (tipus) {
            case Prohibit:
                partida.getOrdre().passarTorn();
                System.out.println("Es salta el torn del següent jugador!");
                break;
            case CanviSentit:
                partida.getOrdre().canviarSentit();
                System.out.println("Es canvia la direcció del joc!");
                break;
            case Mes2:
                partida.getOrdre().passarTorn();
                Jugador jugadorSeguent = partida.getOrdre().getJugadorActiu();
                System.out.println( jugadorSeguent.getNom() + " ha de robar 2 cartes!");
                for (int i = 0; i < 2; i++) {
                    if (partida.getMazo().esBuit()) {
                        partida.getMazo().reiniciar(partida.getPilo());
                    }
                    jugadorSeguent.robarCarta(partida.getMazo());
                }
                break;
            case CanviColor:
                Scanner scanner = new Scanner(System.in);
                System.out.println("Escull un color (GROC, VERMELL, BLAU, VERD):");
                String colorStr = scanner.nextLine().toUpperCase();
                try {
                    Color nouColor = Color.valueOf(colorStr);
                    this.setColor(nouColor);
                    System.out.println("Efecte CANVI_COLOR: Color canviat a " + nouColor);
                } catch (IllegalArgumentException e) {
                    System.out.println("Color no vàlid");
                }
                break;
            case Mes4:
                Scanner scanner4 = new Scanner(System.in);
                System.out.println("Escull un color (GROC, VERMELL, BLAU, VERD):");
                String colorStr4 = scanner4.nextLine().toUpperCase();
                try {
                    Color nouColor = Color.valueOf(colorStr4);
                    this.setColor(nouColor);
                    System.out.println("Color canviat a " + nouColor);
                } catch (IllegalArgumentException e) {
                    System.out.println("Color no vàlid");
                    this.setColor(Color.Vermell);
                }

                partida.getOrdre().passarTorn();
                Jugador jugadorSeguent4 = partida.getOrdre().getJugadorActiu();
                System.out.println( jugadorSeguent4.getNom() + " ha de robar 4 cartes!");
                for (int i = 0; i < 4; i++) {
                    if (partida.getMazo().esBuit()) {
                        partida.getMazo().reiniciar(partida.getPilo());
                    }
                    jugadorSeguent4.robarCarta(partida.getMazo());
                }
                break;
        }
    }

    public static class Regles {
        public static boolean sonCartesCompatibles(Carta c1, Carta c2) {
            if ((c1.getTipus() == TipusCarta.CanviColor || c1.getTipus() == TipusCarta.Mes4) &&
                    c1.getColor() != null) {
                return true;
            }

            return c1.getColor() == c2.getColor() ||
                    (c1.getTipus() == TipusCarta.NORMAL && c2.getTipus() == TipusCarta.NORMAL && c1.getNumero() == c2.getNumero()) || (c1.getTipus() != TipusCarta.NORMAL && c1.getTipus() == c2.getTipus());
        }
    }

    public class Especials {
        public void Especial(Partida partida) {
            System.out.println("Les cartes fan accions");
        }
    }

    public class Comodins {
        public void Comodi(Partida partida) {
            System.out.println("Les cartes canvien el color");
        }
    }

    class Prohibit extends Especials {
        public void Especial(Partida partida) {
            System.out.println("Es salta el torn del següent jugador!");
            partida.getOrdre().passarTorn();
        }
    }

    class Mes2 extends Especials {
        public void Especial(Partida partida) {
            partida.getOrdre().passarTorn();
            Jugador jugadorSeguent = partida.getOrdre().getJugadorActiu();
            System.out.println( jugadorSeguent.getNom() + " ha de robar 2 cartes!" );
            for (int i = 0; i < 2; i++) {
                if (partida.getMazo().esBuit()) {
                    partida.getMazo().reiniciar(partida.getPilo());
                }
                jugadorSeguent.robarCarta(partida.getMazo());
            }
        }
    }

    class Mes4 extends Comodins {
        public void Comodi(Partida partida) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Escull un color (GROC, VERMELL, BLAU, VERD):");
            String colorStr = scanner.nextLine().toUpperCase();
            try {
                Color nouColor = Color.valueOf(colorStr);
                setColor(nouColor);
                System.out.println("El color a canviat a " + nouColor);
            } catch (IllegalArgumentException e) {
                System.out.println("Color no vàlid");
            }

            partida.getOrdre().passarTorn();
            Jugador jugadorSeguent = partida.getOrdre().getJugadorActiu();
            System.out.println( jugadorSeguent.getNom() + " ha de robar 4 cartes!" );
            for (int i = 0; i < 4; i++) {
                if (partida.getMazo().esBuit()) {
                    partida.getMazo().reiniciar(partida.getPilo());
                }
                jugadorSeguent.robarCarta(partida.getMazo());
            }
        }
    }

    class CanviSentit extends Especials {
        public void Especial(Partida partida) {
            System.out.println("Es canvia la direcció del joc!");
            partida.getOrdre().canviarSentit();
        }
    }

    class CanviColor extends Comodins {
        public void Comodi(Partida partida) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Escull un color (GROC, VERMELL, BLAU, VERD):");
            String colorStr = scanner.nextLine().toUpperCase();
            try {
                Color nouColor = Color.valueOf(colorStr);
                setColor(nouColor);
                System.out.println("Efecte CANVI_COLOR: Color canviat a " + nouColor);
            } catch (IllegalArgumentException e) {
                System.out.println("Color no vàlid");
            }
        }
    }
}