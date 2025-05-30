package uno;

import uno.UI.*;
import uno.Logica.*;
import java.util.*;

public class Partida {
    private Mazo mazo;
    private Pilo pilo;
    private Ordre ordre;
    private int numJugadors;
    private int cartesInicials;

    public Partida() {
        this.mazo = new Mazo();
        this.pilo = new Pilo();
        this.ordre = new Ordre();
        this.numJugadors = 2;
        this.cartesInicials = 7;
    }

    public static void iniciar() {
        Partida partida = new Partida();
        partida.preparar();
        partida.torn();
    }

    public void preparar() {
        crearJugadors();

        ordre.barrejarOrdre();

        repartirCartes();

        Carta primeraCartaPilo = mazo.agafarCarta();
        while (primeraCartaPilo.getTipus() != TipusCarta.NORMAL) {
            mazo.getCartes().add(0, primeraCartaPilo);
            mazo.barrejar();
            primeraCartaPilo = mazo.agafarCarta();
        }
        pilo.addCarta(primeraCartaPilo);
    }

    private void crearJugadors() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introdueix el numero de jugadors ");
        try {
            numJugadors = scanner.nextInt();
            scanner.nextLine();

            if (numJugadors < 2 || numJugadors > 10) {
                System.out.println("Numero no valid");
                numJugadors = 2;
            }
        } catch (Exception e) {
            System.out.println("Entrada no valida");
            numJugadors = 2;
        }

        for (int i = 0; i < numJugadors; i++) {
            System.out.println("Nom del jugador " + (i+1) + ": ");
            String nom = scanner.nextLine();
            ordre.crearJugador(nom);
        }
    }

    private void repartirCartes() {
        for (int i = 0; i < cartesInicials; i++) {
            for (int j = 0; j < numJugadors; j++) {
                ordre.getJugadorActiu().robarCarta(mazo);
                ordre.passarTorn();
            }
        }
    }

    public void torn() {
        boolean jocAcabat = false;

        while (!jocAcabat) {
            Jugador jugadorActiu = ordre.getJugadorActiu();
            System.out.println("\n==================================");
            System.out.println("Torn de: " + jugadorActiu.getNom());
            System.out.println("Sentit del joc: " + (ordre.getSentitHorari() ? "Horari" : "Antihorari"));

            Carta cartaAmunt = pilo.consultarCarta();
            System.out.println("Carta en joc:");
            UI.mostrarCarta(cartaAmunt);

            System.out.println("Les teves cartes:");
            UI.mostrarCartes(jugadorActiu.getCartes());

            if (jugadorActiu.potTirarCarta(cartaAmunt)) {
                tirarCarta(jugadorActiu);
            } else {
                System.out.println("No pots tirar cap carta");

                if (mazo.esBuit()) {
                    System.out.println("El mazo esta buit");
                    mazo.reiniciar(pilo);
                }

                jugadorActiu.robarCarta(mazo);
                System.out.println("Has robat una carta");

                System.out.println("Les teves cartes despres de robar:");
                UI.mostrarCartes(jugadorActiu.getCartes());

                Carta cartaRobada = jugadorActiu.getCartes().get(jugadorActiu.getCartes().size() - 1);
                if (Carta.Regles.sonCartesCompatibles(cartaRobada, cartaAmunt)) {
                    System.out.println("Pots tirar la carta que has robat. Vols tirar-la? (s/n)");
                    Scanner scanner = new Scanner(System.in);
                    String opcio = scanner.nextLine().toLowerCase();
                    if (opcio.equals("s")) {
                        jugadorActiu.tirarCarta(cartaRobada, pilo);
                        System.out.println("Has tirat:");
                        UI.mostrarCarta(cartaRobada);

                        if (cartaRobada.getTipus() != TipusCarta.NORMAL) {
                            cartaRobada.aplicarEfecte(this);
                        }
                    }
                }
            }

            if (jugadorActiu.nombreDeCartes() == 0) {
                System.out.println(jugadorActiu.getNom() + " ha guanyat!");
                jocAcabat = true;
            } else if (jugadorActiu.nombreDeCartes() == 1) {
                System.out.println(jugadorActiu.getNom() + " diu: UNO!");
            }

            if (!jocAcabat) {
                ordre.passarTorn();
            }
        }
    }

    private void tirarCarta(Jugador jugador) {
        Scanner scanner = new Scanner(System.in);
        Carta cartaAmunt = pilo.consultarCarta();
        ArrayList<Carta> cartesCompatibles = new ArrayList<>();

        for (Carta c : jugador.getCartes()) {
            if (Carta.Regles.sonCartesCompatibles(c, cartaAmunt)) {
                cartesCompatibles.add(c);
            }
        }

        System.out.println("Cartes que pots tirar:");
        for (int i = 0; i < cartesCompatibles.size(); i++) {
            System.out.println(i + ": " + cartesCompatibles.get(i));
        }

        System.out.println("Escolleix una carta (0-" + (cartesCompatibles.size()-1) + "):");
        int opcio = -1;
        try {
            opcio = scanner.nextInt();
            if (opcio >= 0 && opcio < cartesCompatibles.size()) {
                Carta cartaElegida = cartesCompatibles.get(opcio);
                jugador.tirarCarta(cartaElegida, pilo);
                System.out.println("Has tirat:");
                UI.mostrarCarta(cartaElegida);

                if (cartaElegida.getTipus() != TipusCarta.NORMAL) {
                    cartaElegida.aplicarEfecte(this);
                }
            } else {
                System.out.println("Opció no valida");
            }
        } catch (Exception e) {
            System.out.println("Entrada no valida");
        }
    }

    public Mazo getMazo() {
        return mazo;
    }

    public Pilo getPilo() {
        return pilo;
    }

    public Ordre getOrdre() {
        return ordre;
    }
}