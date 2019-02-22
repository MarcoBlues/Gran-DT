package grandt;

/**
 * Clase principal donde el usuario(competidor) podra dar a inicio con la
 * interaccion con el programa, en donde podra crear, cargar y comenzar su
 * partida.
 *
 * @author Briatore Marco
 * @author Ravanni Gianni
 * @author Bertolotti Marcos
 */
import Utilidad.BaseDatos;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GranDT_Final {

    static Scanner entrada = new Scanner(System.in);
    static String nombre = null;
    static Simular progreso = null;
    static BaseDatos archivo = new BaseDatos();

    public static void main(String[] args) {

        menuGranDT();
    }

    /**
     * Menu de logueo del gran DT, en donde el competidor podra interactuar con
     * el, crear una partida, cargarla e iniciarla para dar comienzo al
     * programa.
     *
     * @see #menuGranDT()
     */
    private static void menuGranDT() {
        int rta;

        do {
            System.out.println("\n********** Menu GRAN DT **********");
            System.out.println("1. Crear nueva partida.");
            System.out.println("2. Cargar partida existente.");
            System.out.println("3. Iniciar mi partida.");
            System.out.println("0. Salir.");
            System.out.print("\nEliga la opcion que desee: ");
            rta = new Simular().verificarScanner(entrada);

            switch (rta) {
                case 1:
                    crearCuenta();
                    break;
                case 2:
                    cargarCuenta();
                    break;
                case 3:
                    iniciarPartida();
                    break;
                case 0:
                    System.out.println("Saliendo del GRAN DT...");
                    break;
                default:
                    System.out.println("¡Error! Ingresa un valor correcto.");
            }
        } while (rta != 0);
    }

    /**
     * Metodo que permitira crear la cuenta del competidor.
     *
     * @see #crearCuenta()
     */
    private static void crearCuenta() {
        System.out.print("Ingrese el nombre de la nueva cuenta: ");
        nombre = entrada.next();

        List<Equipo> equipos = archivo.cargarJugadoresJackson();

        progreso = new Simular(new Competidor(nombre, new Equipo(nombre, 250000, new LinkedList<>()), new Fecha(1)), equipos);

        new BaseDatos(nombre + ".json").guardarPartida(progreso);

        System.out.println("¡Cuenta creada exitosamente!");
    }

    /**
     * Metodo que permitira cargar la partida del competidor.
     *
     * @see #cargarCuenta()
     */
    private static void cargarCuenta() {
        System.out.print("Ingrese el nombre de la cuenta a cargar: ");
        nombre = entrada.next();

        progreso = archivo.cargarPartida(new File(nombre + ".json"));
    }

    /**
     * Metodo que permitira iniciar la partida del competidor si fue creada o
     * cargada anteriormente.
     *
     * @see #iniciarPartida()
     */
    private static void iniciarPartida() {
        if (progreso != null) {
            new Simular(progreso.getCompetidor(), progreso.getEquipos()).simular();
            new BaseDatos(nombre + ".json").guardarPartida(progreso);
        } else {
            System.out.println("¡Error! ¡Primero carga o cree una partida!");
        }
    }
}
