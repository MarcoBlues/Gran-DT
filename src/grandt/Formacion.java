package grandt;

/**
 * Clase que representara las distintas formaciones que podra elegir el
 * competidor para su equipo.
 */
import java.util.Scanner;

public final class Formacion {

    public static final int[] FORMACION_1 = {4, 3, 3};
    public static final int[] FORMACION_2 = {4, 4, 2};
    public static final int[] FORMACION_3 = {3, 5, 2};
    public static final int[] FORMACION_4 = {3, 4, 3};
    public static final int[] FORMACION_5 = {5, 4, 1};
    public static final int[] FORMACION_6 = {4, 5, 1};
    public static final int[] FORMACION_7 = {5, 3, 2};
    public static final int[] FORMACION_8 = {3, 6, 1};

    public Formacion() {

    }

    /**
     * metodo que dara a escoger el tipo de formacion que desea para su equipo.
     *
     * @return la formacion de tipo arreglo elegida por el competidor
     * @see #formacionEquipo()
     */
    public int[] formacionEquipo() {

        Scanner entrada = new Scanner(System.in);
        int rta;
        int formacion[] = new int[3];

        do {
            System.out.println("\n**********FORMACIONES**********\n"
                    + "    1. 4-3-3.\n    2. 4-4-2.\n    3. 3-5-2.\n"
                    + "    4. 3-4-3.\n    5. 5-4-1.\n    6. 4-5-1.\n"
                    + "    7. 5-3-2.\n    8. 3-6-1.\n    0. Salir.");
            System.out.print("Elija la formacion que desee: ");
            rta = new Simular().verificarScanner(entrada);

            switch (rta) {
                case 1:
                    formacion = Formacion.FORMACION_1;
                    break;
                case 2:
                    formacion = Formacion.FORMACION_2;
                    break;
                case 3:
                    formacion = Formacion.FORMACION_3;
                    break;
                case 4:
                    formacion = Formacion.FORMACION_4;
                    break;
                case 5:
                    formacion = Formacion.FORMACION_5;
                    break;
                case 6:
                    formacion = Formacion.FORMACION_6;
                    break;
                case 7:
                    formacion = Formacion.FORMACION_7;
                    break;
                case 8:
                    formacion = Formacion.FORMACION_8;
                    break;
                case 0:
                    System.out.println("Saliendo del menu de formaciones...");
                    return null;
                default:
                    System.out.println("Error!!! ingrese una opcion entre el 0 y el 8");
            }
        } while (rta < 0 || rta > 8);
        return formacion;
    }
}
