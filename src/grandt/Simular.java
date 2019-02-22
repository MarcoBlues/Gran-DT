package grandt;

/**
 * Clase en donde se realizara toda la interaccion con el competidor, esta
 * contiene todos los menus con sus respectivos metodos para la funcionalidad
 * del programa.
 */
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Simular {

    private Competidor competidor;
    private List<Equipo> equipos;
    private Scanner entrada = new Scanner(System.in);

    public Simular() {

    }

    /**
     * Constructor para inicializar un objeto de tipo Simular.
     *
     * @param competidor representa el usuario a interactuar con el programa
     * @param equipos base de datos propia del competidor con todos los equipos
     * y sus respectivos jugadores
     * @see #Simular(grandt.Competidor, java.util.List)
     * @see Competidor
     * @see Equipo
     */
    public Simular(Competidor competidor, List<Equipo> equipos) {
        this.competidor = competidor;
        this.equipos = equipos;
    }

    /**
     * Obtiene el objeto de tipo competidor
     *
     * @return {@link #competidor}
     * @see #getCompetidor()
     */
    public Competidor getCompetidor() {
        return competidor;
    }

    /**
     * Establece el objeto de tipo competidor
     *
     * @param competidor representa el usuario a interactuar con el programa
     * @see #setCompetidor(Competidor competidor)
     */
    public void setCompetidor(Competidor competidor) {
        this.competidor = competidor;
    }

    /**
     * Obtiene una lista de equipos(base de datos) del competidor
     *
     * @return {@link #equipos}
     * @see #getEquipos()
     */
    public List<Equipo> getEquipos() {
        return equipos;
    }

    /**
     * Establece una lista de equipos(base de datos) del competidor
     *
     * @param equipos base de datos propia del competidor con todos los equipos
     * @see #setEquipos(java.util.List)
     */
    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    /**
     * Menu principal del grand DT para la interaccion con el usuario, aqui se
     * podra crear el equipo, comprar/vender jugadores, jugar las fechas y ver
     * las estadisticas.
     *
     * @see #simular()
     */
    public void simular() {
        int rta;

        do {
            System.out.println("\n********** Menu GRAN DT - Competidor: " + competidor.getNombre().toUpperCase() + " **********");
            System.out.println("1.- Crear Equipo Inicial.");
            System.out.println("2.- Comprar jugador.");
            System.out.println("3.- Vender jugador.");
            if (competidor.fecha.getSemana() < 28) {
                System.out.println("4.- Jugar Fecha " + competidor.fecha.getSemana() + " de 27.");
            } else {
                System.out.println("4.- Jugar Fecha (Torneo Finalizado).");
            }
            System.out.println("5.- Estadisticas.");
            System.out.println("0.- Salir.");

            System.out.print("\nEliga la opcion que desee: ");
            rta = verificarScanner(entrada);

            switch (rta) {
                case 1:
                    armarEquipoInicial();
                    break;
                case 2:
                    comprar();
                    break;
                case 3:
                    vender();
                    break;
                case 4:
                    jugar();
                    break;
                case 5:
                    if (!competidor.getMiEquipo().getJugadores().isEmpty()) {
                        menuEstadisticas();
                    } else {
                        System.out.println("ERROR!!! PRIMERO ARME SU EQUIPO INICIAL");
                    }
                    break;
                case 0:
                    System.out.println("Cerrando partida...");
                    break;
                default:
                    System.out.println("Error!!! Ingrese un valor entre 0 y 5");
            }
        } while (rta != 0);
    }

    /**
     * Metodo que permite armar el equipo inicial del competidor.
     *
     * @see #armarEquipoInicial()
     */
    private void armarEquipoInicial() {
        if (competidor.getMiEquipo().getJugadores().isEmpty()) {
            armarEquipo();
            System.out.println("Equipo creado exitosamente!");
        } else {
            System.out.println("Ya ha creado su equipo inicial anteriormente");
        }
    }

    /**
     * Metodo que permite comprar un jugador al competidor.
     *
     * @see #comprar()
     */
    private void comprar() {
        if (!competidor.getMiEquipo().getJugadores().isEmpty()) {
            System.out.println("\nComprando jugador...");
            mostrarEquipos();

            if (comprarJugador()) {
                System.out.println("Jugador comprado. Saldo actual: " + competidor.getMiEquipo().getPresupuesto());
            } else {
                System.out.println("no se ha podido comprar al jugador.");
            }
        } else {
            System.out.println("ERROR!!! PRIMERO ARME SU EQUIPO INICIAL");
        }
    }

    /**
     * Metodo que permite vender un jugador al competidor.
     *
     * @see #vender()
     */
    private void vender() {
        if (!competidor.getMiEquipo().getJugadores().isEmpty()) {
            if (venderJugador()) {
                System.out.println("Jugador vendido. Saldo actual: " + competidor.getMiEquipo().getPresupuesto());
            } else {
                System.out.println("No se ha concretado la venta.");
            }
        } else {
            System.out.println("ERROR!!! PRIMERO ARME SU EQUIPO INICIAL");
        }
    }

    /**
     * Metodo que dara a inicio a la disputacion de una nueva fecha de los
     * equipos.
     *
     * @see #jugar()
     */
    private void jugar() {
        if (!competidor.getMiEquipo().getJugadores().isEmpty()) {
            if (competidor.fecha.getSemana() < 28) {
                competidor.fecha.jugar(equipos);
                System.out.println("Se jugo la fecha " + competidor.fecha.getSemana());
                competidor.fecha.setSemana(1);
            } else {
                System.out.println("Torneo Finalizado.");
            }
        } else {
            System.out.println("ERROR!!! PRIMERO ARME SU EQUIPO INICIAL");
        }
    }

    /**
     * menu principal de busqueda de jugadores en donde se podra buscar a un
     * jugador de distintas maneras.
     *
     * @see #menuPrincipalBuscarJugadores()
     */
    public void menuPrincipalBuscarJugadores() {
        int rta;

        do {
            System.out.println("\n**********MENU PRINCIPAL**********");
            System.out.println("1.- Buscar todos los jugadores.");
            System.out.println("2.- Buscar jugadores segun posicion.");
            System.out.println("3.- Buscar Jugadores por equipo.");
            System.out.println("0.- Salir");
            System.out.print("Ingrese la opcion que desee: ");
            rta = verificarScanner(entrada);

            switch (rta) {
                case 1:
                    mostrarJugadoresEquipos();
                    break;
                case 2:
                    subMenuMostrarJugadoresSegunPosicion();
                    break;
                case 3:
                    subMenuMostrarEquipos();
                    break;
                case 0:
                    System.out.println("Saliendo del menu de creacion de equipo...");
                    break;
                default:
                    System.out.println("Error!!!!!!!! Ingrese un valor entre 0 y 3");
            }
        } while (rta != 0);
    }

    /**
     * Sub-menu que muestra todos los jugadores de una misma posicion.
     *
     * @see #subMenuMostrarJugadoresSegunPosicion()
     */
    private void subMenuMostrarJugadoresSegunPosicion() {
        int rta;

        do {
            System.out.println("\n**********SUB MENU POSICION JUGADORES**********");
            mostrarOpcionesSubMenu();
            System.out.println("0.- Salir");
            System.out.print("Ingrese la opcion que desee: ");
            rta = verificarScanner(entrada);

            switch (rta) {
                case 1:
                    mostrarJugadoresSegunPosicion(TipoPosicion.ARQUERO);
                    break;
                case 2:
                    mostrarJugadoresSegunPosicion(TipoPosicion.DEFENSOR);
                    break;
                case 3:
                    mostrarJugadoresSegunPosicion(TipoPosicion.VOLANTE);
                    break;
                case 4:
                    mostrarJugadoresSegunPosicion(TipoPosicion.DELANTERO);
                    break;
                case 0:
                    System.out.println("Saliendo del sub-menu de mostrar jugadores segun posicion...");
                    break;
                default:
                    System.out.println("Error!!! Ingrese un valor entre 0 y 4");
            }
        } while (rta != 0);
    }

    /**
     * Sub-menu que muestra los nombres de todos los equipos.
     *
     * @see #subMenuMostrarEquipos()
     */
    private void subMenuMostrarEquipos() {

        int rta;
        do {
            mostrarEquipos();
            System.out.println("0.- Salir");
            System.out.print("Ingrese la opcion que desee: ");
            rta = verificarScanner(entrada);

            while (rta < 0 || rta > 28) {
                System.out.print("Error!!! ingrese un valor entre 0 y 28: ");
                rta = verificarScanner(entrada);
            }
            if (rta != 0) {
                subMenuMostrarJugadoresPorEquipo(equipos.get(rta - 1).getJugadores(), equipos.get(rta - 1).getEquipoNombre());
            }
        } while (rta != 0);
        System.out.println("Saliendo del sub menu de mostrar equipos...");
    }

    /**
     * Sub-menu que muestra los jugadores de un solo equipo
     *
     * @param jugadores jugadores del equipo a mostrar
     * @param nombreEquipo nombre del equipo de los jugadores a mostrar
     * @see #subMenuMostrarJugadoresPorEquipo(java.util.List, java.lang.String)
     */
    private void subMenuMostrarJugadoresPorEquipo(List<Jugador> jugadores, String nombreEquipo) {
        int rta;

        do {
            System.out.println("\n**********SUB MENU EQUIPO " + nombreEquipo.toUpperCase() + "**********");
            mostrarOpcionesSubMenu();
            System.out.println("5.- Todos los jugadores");
            System.out.println("6.- Jugadores ordenados por puntaje");
            System.out.println("0.- Salir");
            System.out.print("Ingrese la opcion que desee: ");
            rta = verificarScanner(entrada);
            System.out.println();

            switch (rta) {
                case 1:
                    mostrarJugadoresEquipoSegunPosicion(TipoPosicion.ARQUERO, jugadores);
                    break;
                case 2:
                    mostrarJugadoresEquipoSegunPosicion(TipoPosicion.DEFENSOR, jugadores);
                    break;
                case 3:
                    mostrarJugadoresEquipoSegunPosicion(TipoPosicion.VOLANTE, jugadores);
                    break;
                case 4:
                    mostrarJugadoresEquipoSegunPosicion(TipoPosicion.DELANTERO, jugadores);
                    break;
                case 5:
                    mostrarJugadoresEquipo(jugadores);
                    break;
                case 6:
                    mostrarJugadoresEquipo(ordenarJugadoresPorPuntaje(jugadores));
                    break;
                case 0:
                    System.out.println("Saliendo del sub-menu de mostrar jugadores por equipo...");
                    break;
                default:
                    System.out.println("Error!!! Ingrese un valor entre 0 y 5");
            }
        } while (rta != 0);
    }

    /**
     * Metodo que muestras cadenas de string de distintas opciones utilizadas en
     * los menus.
     *
     * @see #mostrarOpcionesSubMenu()
     */
    private void mostrarOpcionesSubMenu() {
        System.out.println("1.- Arqueros.");
        System.out.println("2.- Defensores.");
        System.out.println("3.- Volantes.");
        System.out.println("4.- Delanteros.");
    }

    /**
     * Muestra todos los jugadores de todos los equipos.
     *
     * @see #mostrarJugadoresEquipos()
     */
    private void mostrarJugadoresEquipos() {
        for (Equipo equipo : equipos) {
            System.out.println("******** Jugadores de: " + equipo.getEquipoNombre() + " ********");
            mostrarJugadoresEquipo(equipo.getJugadores());
        }
    }

    /**
     * Muestra todos los jugadores de un equipo.
     *
     * @param jugadores jugadores del equipo a mostrar
     * @see #mostrarJugadoresEquipo(java.util.List)
     */
    private void mostrarJugadoresEquipo(List<Jugador> jugadores) {
        int i = 0;

        for (Jugador jugador : jugadores) {
            System.out.println(++i + ".- " + jugador);
        }
    }

    /**
     * Muestra todos los jugadores que sean de la misma posicion de todos los
     * equipos, delantero, volante, defensor o arquero.
     *
     * @param posicion representa el tipo de posicion de los jugadores a mostrar
     * @see #mostrarJugadoresSegunPosicion(TipoPosicion posicion)
     */
    private void mostrarJugadoresSegunPosicion(TipoPosicion posicion) {
        for (Equipo equipo : equipos) {
            System.out.println("******** Jugadores de: " + equipo.getEquipoNombre() + " ********");
            mostrarJugadoresEquipoSegunPosicion(posicion, equipo.getJugadores());
        }
    }

    /**
     * Muestra los jugadores segun su posicion, de un solo equipo, delantero,
     * volante, defensor o arquero.
     *
     * @param posicion representa el tipo de posicion de los jugadores a mostrar
     * @param jugadores jugadores del equipo a mostrar
     * @see #mostrarJugadoresEquipoSegunPosicion(grandt.TipoPosicion,
     * java.util.List)
     */
    private void mostrarJugadoresEquipoSegunPosicion(TipoPosicion posicion, List<Jugador> jugadores) {

        /// jugadores.stream().filter(jugador -> jugador.getTIPO_POSICION() == posicion).forEach(jugador -> System.out.println(jugador));
        int i = 0;
        for (Jugador jugador : jugadores) {
            ++i;
            if (jugador.getTIPO_POSICION() == posicion) {
                System.out.println(i + ".- " + jugador);
            }
        }
    }

    /**
     * Muestra los nombres de los equipos numeradamente y su puntaje total.
     *
     * @see #mostrarEquipos()
     */
    private void mostrarEquipos() {
        int i = 0;
        System.out.println("\n**********EQUIPOS**********");
        for (Equipo equipo : equipos) {
            if (i < 9) {
                System.out.print("0");
            }
            System.out.println(++i + ".- " + equipo);
        }
    }

    /**
     * Funcion con java 8 para ordenar la Collection de jugadores.
     *
     * @param jugadores jugadores del equipo a ordenar
     * @return lista de jugadores ordenados
     * @see #ordenarJugadoresPorPuntaje(java.util.List)
     */
    private List<Jugador> ordenarJugadoresPorPuntaje(List<Jugador> jugadores) {
        return jugadores.stream().sorted(Jugador::compareTo).collect(Collectors.toList());
    }

    // 
    /**
     * Menu de estadisticas que permitira calcular y mostrar el puntaje de todos
     * los equipos con sus respectivos jugadores, y el del equipo del
     * competidor.
     *
     * @see #menuEstadisticas()
     */
    private void menuEstadisticas() {
        int rta;

        do {
            System.out.println("\n**********MENU ESTADISTICAS**********");
            System.out.println("1.- Calcular puntaje Jugadores/Equipos");
            System.out.println("2.- Jugadores.");
            System.out.println("3.- Equipos.");
            System.out.println("4.- Mi Equipo.");
            System.out.println("5.- Ranking de equipos");
            System.out.println("0.- Salir.");
            System.out.print("Ingrese la opcion que desee: ");
            rta = verificarScanner(entrada);

            switch (rta) {
                case 1:
                    for (Equipo equipo : equipos) {
                        equipo.calcularPuntajeJugadores();
                    }
                    competidor.getMiEquipo().calcularPuntajeJugadores();
                    System.out.println("Puntajes calculados exitosamente!");
                    break;
                case 2:
                    menuPrincipalBuscarJugadores();
                    break;
                case 3:
                    subMenuMostrarEquipos();
                    break;
                case 4:
                    subMenuMostrarJugadoresPorEquipo(competidor.getMiEquipo().getJugadores(), competidor.getMiEquipo().getEquipoNombre());
                    break;
                case 5:

                    if (competidor.fecha.getSemana() == 28) {
                        if (!equipos.contains(competidor.getMiEquipo())) {
                            equipos.add(competidor.getMiEquipo());
                        }
                        equipos.stream().sorted(Equipo::compareTo).forEach(System.out::println);
                    } else {
                        System.out.println("No se termino el juego");
                    }
                    break;
                case 0:
                    System.out.println("Saliendo del menu de estadisicas...");
                    break;
                default:
                    System.out.println("Error!!! ingrese un valor entre 0 y 5");
            }
        } while (rta != 0);
    }

    /**
     * Metodo que permitira armar el equipo del competidor segun la formacion
     * que este haya elegido para su equipo.
     *
     * @see #armarEquipo()
     */
    private void armarEquipo() {
        int formacion[] = new Formacion().formacionEquipo();

        if (formacion != null) {
            armarEquipoPosicion(TipoPosicion.ARQUERO, 1);
            armarEquipoPosicion(TipoPosicion.DEFENSOR, formacion[0]);
            armarEquipoPosicion(TipoPosicion.VOLANTE, formacion[1]);
            armarEquipoPosicion(TipoPosicion.DELANTERO, formacion[2]);
        } else {
            System.out.println("Error!!! Aun no has elegido tu formacion para el equipo.");
        }
    }

    /**
     * Metodo que armara el equipo del competidor segun la posicion del jugador
     * con la formacion escogida.
     *
     * @param posicion posicion de los jugadores a escoger, delantero, volante,
     * defensor o arquero.
     * @param formacion tipo de formacion escogida por el competidor
     * @see #armarEquipoPosicion(TipoPosicion posicion, int formacion)
     * @see TipoPosicion
     */
    private void armarEquipoPosicion(TipoPosicion posicion, int formacion) {

        mostrarEquipos();
        System.out.print("\nSelecciona el equipo: ");
        int indexEquipo = verificaRangoParametroEquipo(entrada);

        System.out.println("\n*****" + posicion + "*****\n");
        mostrarJugadoresEquipoSegunPosicion(posicion, equipos.get(indexEquipo).getJugadores());

        for (int i = 0; i < formacion; i++) {
            competidor.getMiEquipo().comprarJugador(elegirJugadorSegunPosicion(equipos.get(indexEquipo), posicion));
            System.out.println("Jugador elegido exitosamente");
        }
    }

    /**
     * Metodo que permitira al competidor escoger el jugador a vender de su
     * equipo.
     *
     * @return true si se vendio el jugador, false si no se vendio
     * @see #venderJugador()
     */
    private boolean venderJugador() {

        if (competidor.getMiEquipo().getJugadores().size() > 11) {
            mostrarJugadoresEquipo(competidor.getMiEquipo().getJugadores());

            System.out.print("Seleciona el jugador: ");
            int indexJugador = verificaRangoParametroJugador(competidor.getMiEquipo(), entrada);

            competidor.getMiEquipo().venderJugador(indexJugador);
            return true;
        } else {
            System.out.println("Posees la cantidad minima de jugadores disponibles");
        }
        return false;
    }

    /**
     * Metodo que permitira al competidor escoger el jugador a comprar de la
     * base de datos para su equipo.
     *
     * @return true si se compro el jugador, false si no se compro
     * @see #comprarJugador()
     */
    private boolean comprarJugador() {

        System.out.print("Seleciona el equipo: ");
        int indexEquipo = verificaRangoParametroEquipo(entrada);

        mostrarJugadoresEquipo(equipos.get(indexEquipo).getJugadores());

        System.out.println("Saldo Actual: " + competidor.getMiEquipo().getPresupuesto());
        System.out.print("Seleciona el jugador: ");
        int indexJugador = verificaJugadorAComprar(equipos.get(indexEquipo));

        boolean estado = competidor.getMiEquipo().comprarJugador(equipos.get(indexEquipo).getJugadores().get(indexJugador));

        return estado;
    }

    /**
     * Permite escoger a un jugador, verifica si ya fue comprado y si es de la
     * posicion deseada, luego lo retorna
     *
     * @param equipo equipo que contiene los jugadores que podra elegir el
     * competidor
     * @param posicion posicion del jugador a elegir
     * @return jugador elegido por el competidor
     * @see #elegirJugadorSegunPosicion(Equipo equipo, TipoPosicion posicion)
     * @see TipoPosicion
     */
    private Jugador elegirJugadorSegunPosicion(Equipo equipo, TipoPosicion posicion) {

        System.out.print("Elija el " + posicion.name().toLowerCase() + " para el 11 titular: ");
        int rta = verificaJugadorAComprar(equipo);

        while (equipo.getJugadores().get(rta).getTIPO_POSICION() != posicion) { // verifica que el jugador a elegir sea de la misma posicion a escoger
            System.out.print("El jugador elegido no es un " + posicion.name().toLowerCase() + " ,ingrese otra opcion: ");
            rta = verificaRangoParametroJugador(equipo, entrada);
        }
        return equipo.getJugadores().get(rta);
    }

    /**
     * Verifica que el jugador a comprar no haya sido comprado anteriormente y
     * que este dentro de los parametros de la coleccion de jugadores.
     *
     * @param equipo equipo con los jugadores a verificar con el jugador elegido
     * @return index del jugador elegido
     * @see #verificaJugadorAComprar(Equipo equipo)
     * @see Equipo
     */
    private int verificaJugadorAComprar(Equipo equipo) {
        int rta = verificaRangoParametroJugador(equipo, entrada);

        while (competidor.getMiEquipo().getJugadores().contains(equipo.getJugadores().get(rta))) {    // verifica que el jugador elegido no haya sido escogido anteriormente.
            System.out.print("este jugador ya fue comprado, elija otro: ");
            rta = verificaRangoParametroJugador(equipo, entrada);
        }
        return rta;
    }

    /**
     * Verifica que el numero ingresado por el usuario este dentro de los
     * valores de la coleccion de jugadores
     *
     * @param equipo equipo con los jugadores a verificar con el jugador elegido
     * @param entrada scanner con el index del jugador elegido
     * @return index del jugador verificado
     * @see #verificaRangoParametroJugador(Equipo equipo, Scanner entrada)
     * @see Equipo
     */
    private int verificaRangoParametroJugador(Equipo equipo, Scanner entrada) {

        int rta = verificarScanner(entrada) - 1;

        while (rta < 0 || rta >= equipo.getJugadores().size()) {    // verifica que el jugador elegido este dentro del rango de parametros del arreglo
            System.out.print("Error! la opcion ingresada esta fuera de los parametros, ingrese otra opcion: ");
            rta = verificarScanner(entrada) - 1;
        }
        return rta;
    }

    /**
     * Verifica que el numero ingresado por el usuario este dentro de los
     * valores de la coleccion de equipos.
     *
     * @param entrada scanner con el index del equipo elegido
     * @return index del equipo verificado
     * @see #verificaRangoParametroEquipo(Scanner entrada)
     */
    private int verificaRangoParametroEquipo(Scanner entrada) {
        int rta = verificarScanner(entrada) - 1;

        while (rta < 0 || rta >= equipos.size()) {    // verifica que el equipo elegido este dentro del rango de parametros del arreglo
            System.out.print("Error! la opcion ingresada esta fuera de los parametros, ingrese otra opcion: ");
            rta = verificarScanner(entrada) - 1;
        }
        return rta;
    }

    /**
     * Verifica que el caracter ingresado por el usuario sea un entero.
     *
     * @param entrada scanner con el caracter ingresado
     * @return scanner con un entero
     * @see #verificarScanner(java.util.Scanner)
     */
    public int verificarScanner(Scanner entrada) {

        while (!entrada.hasNextInt()) {
            System.out.print("Solo puede ingresar numeros, intente nuevamente: ");
            entrada.next();
        }
        return entrada.nextInt();
    }
}
