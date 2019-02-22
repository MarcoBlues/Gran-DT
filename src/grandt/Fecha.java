package grandt;

/**
 * Clase que representa las fechas a disputarse entre los equipos, esta misma
 * permitira que todos los equipos disputen sus partidos
 */
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Fecha implements Serializable {

    private int semana;

    public Fecha() {

    }

    /**
     * Constructor para inicializar el objeto Fecha.
     *
     * @param semana representa las semanas(fechas) a disputar
     * @see #Fecha(int semana)
     */
    public Fecha(int semana) {
        this.semana = semana;
    }

    /**
     * Establece la semana a disputar.
     *
     * @param semana representa las semanas(fechas) a disputar
     * @see #setSemana(int)
     */
    public void setSemana(int semana) {
        this.semana += semana;
    }

    /**
     * Obtiene la semana a disputar.
     *
     * @return {@link #semana}
     * @see #getSemana()
     */
    public int getSemana() {
        return semana;
    }

    /**
     * metodo que permite que juegen todos los equipos de una fecha.
     *
     * @param equipos todos los equipos que disputaran la fecha
     * @see #jugar(java.util.List)
     */
    public void jugar(List<Equipo> equipos) {
        Jugador jGol;
        Jugador jRoja = null;
        int goles, rojas, amarillas;

        for (Equipo e : equipos) {
            goles = ThreadLocalRandom.current().nextInt(0, 5);  // rango de goles que puede meter un equipo durante el partido.
            rojas = ThreadLocalRandom.current().nextInt(0, 2);
            amarillas = ThreadLocalRandom.current().nextInt(0, 4);

            for (int i = 0; i < goles; i++) {   // iteracion para setear los goles a los jugadores
                (jGol = e.getJugadores().get(ThreadLocalRandom.current().nextInt(0, e.getJugadores().size()))).setGoles(1);  // toma un jugador random de la coleccion de jugadores y le setea el gol.

                if (ThreadLocalRandom.current().nextBoolean()) {    // boolean random para establecer si el gol tuvo asistencia o no.
                    verificarJugadorRepetido(e.getJugadores(), jGol).setAsistencias(1);    // verifica que el jugador que dio la asistencia no sea el mismo que metio el gol.
                }
            }

            for (int i = 0; i < rojas; i++) {  // iteracion para setear las rojas a los jugadores.
                (jRoja = verificarJugadorRepetido(e.getJugadores(), jRoja)).setRojas(1);    // verifica que el jugador que obtuvo roja, ya no halla sacado roja anteriormente.
            }

            for (int i = 0; i < amarillas; i++) {  // iteracion para setear las amarillas a los jugadores.
                e.getJugadores().get(ThreadLocalRandom.current().nextInt(0, e.getJugadores().size())).setAmarillas(1);
            }
        }
    }

    /**
     * metodo que asigna un jugador random de la coleccion de jugadores y
     * compara si son iguales con el pasado por parametro.
     *
     * @param jugadores todos los jugadores de un equipo
     * @param jugador1 jugador que anteriormente a obtenido una tarjeta roja o
     * ha hecho un gol
     * @return el nuevo jugador asignado
     * @see #verificarJugadorRepetido(java.util.List, grandt.Jugador)
     */
    private Jugador verificarJugadorRepetido(List<Jugador> jugadores, Jugador jugador1) {
        Jugador jugador2 = jugadores.get(ThreadLocalRandom.current().nextInt(0, jugadores.size()));

        while (jugador2.equals(jugador1)) {
            jugador2 = jugadores.get(ThreadLocalRandom.current().nextInt(0, jugadores.size()));
        }
        return jugador2;
    }

}
