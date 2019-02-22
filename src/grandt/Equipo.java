package grandt;

/**
 * Esta clase Representa a un equipo que disputara los partidos con sus
 * respectivos jugadores.
 */
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Equipo implements Serializable, Comparable<Equipo> {

    private static final long serialVersionUID = -5309838498103281073L;

    private String equipoNombre;
    private int presupuesto;
    private List<Jugador> jugadores;
    private int puntaje;

    public Equipo() {

    }

    /**
     * Constructor para inicializar el objeto equipo
     *
     * @param equipoNombre nombre del equipo
     * @param presupuesto presupuesto del equipo para comprar jugadores
     * @param jugadores jugadores del equipo
     * @see #Equipo(java.lang.String, int, java.util.LinkedList)
     */
    public Equipo(String equipoNombre, int presupuesto, LinkedList<Jugador> jugadores) {
        this.equipoNombre = equipoNombre;
        this.presupuesto = presupuesto;
        this.jugadores = jugadores;
        this.puntaje = 0;
    }

    /**
     * Metodo para la compra de un jugador que el competidor eligira.
     *
     * @param j jugador a comprar para mi equipo
     * @return true si se compro, false si no se compro
     * @see #comprarJugador(Jugador j)
     */
    public boolean comprarJugador(Jugador j) {
        if (presupuesto >= j.getCotizacion()) {
            jugadores.add(j);
            presupuesto -= j.getCotizacion();
            return true;
        } else {
            System.out.println("No tenes suficientes fondos para comprar el jugador.\n");
        }
        return false;
    }

    /**
     * Metodo para la venta de un jugador del equipo del cometidor
     *
     * @param i index del jugador a vender del equipo del competidor
     * @see #venderJugador(int i)
     */
    public void venderJugador(int i) {
        presupuesto += jugadores.get(i).getCotizacion();
        jugadores.remove(jugadores.get(i));
    }

    /**
     * Calcula el puntaje de todos los jugadores del equipo.
     *
     * @return {@link #puntaje}
     * @see #calcularPuntajeJugadores()
     */
    public int calcularPuntajeJugadores() {
        puntaje = 0;

        for (Jugador jugador : jugadores) {
            puntaje += jugador.calcularPuntajeJugador();
        }
        return puntaje;
    }

    /**
     * Obtiene el nombre del equipo.
     *
     * @return {@link #equipoNombre}
     * @see #getEquipoNombre()
     */
    public String getEquipoNombre() {
        return equipoNombre;
    }

    /**
     * Establece el nombre del equipo.
     *
     * @param equipoNombre nombre del equipo
     * @see #setEquipoNombre(String equipoNombre)
     */
    public void setEquipoNombre(String equipoNombre) {
        this.equipoNombre = equipoNombre;
    }

    /**
     * Obtiene el presupuesto del equipo.
     *
     * @return {@link #presupuesto}
     * @see #getPresupuesto()
     */
    public int getPresupuesto() {
        return presupuesto;
    }

    /**
     * Establece el presupuesto del equipo
     *
     * @param presupesto presupuesto del equipo para comprar jugadores
     * @see #setPresupuesto(int presupesto)
     */
    public void setPresupuesto(int presupesto) {
        this.presupuesto = presupesto;
    }

    /**
     * Obtiene los jugadores del equipo.
     *
     * @return {@link #jugadores}
     * @see #getJugadores()
     */
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Obtiene los jugadores del equipo.
     *
     * @param jugadores jugadores del equipo
     * @see #setJugadores(java.util.List)
     */
    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    /**
     * Obtiene el puntaje total del equipo.
     *
     * @return {@link #puntaje}
     * @see #getPuntaje()
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * Establece el puntaje total del equipo.
     *
     * @param puntaje puntaje total de todos los jugadores del equipo
     * @see #setPuntaje(int puntaje)
     */
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * Obtiene el hash del objeto equipo
     *
     * @return hash del objeto equipo segun su nombre
     * @see Equipo#hashCode()
     * @see #hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 31 * Objects.hashCode(this.equipoNombre);
        return hash;
    }

    /**
     * Compara el contenido del objeto equipo
     *
     * @param obj objeto a comparar
     * @return true si es igual, false si no lo es.
     * @see Equipo#equals(Object obj)
     * @see #equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipo other = (Equipo) obj;
        return Objects.equals(this.equipoNombre, other.equipoNombre);
    }

    /**
     * Compara el objeto equipo del parametro y determina si es igual, mayor o
     * menor.
     *
     * @param o Objeto de tipo equipo a comparar
     * @see Equipo#compareTo(Equipo o)
     * @see #compareTo(grandt.Equipo)
     */
    @Override
    public int compareTo(Equipo o) {

        if (puntaje < o.getPuntaje()) {
            return -1;
        }
        if (puntaje > o.getPuntaje()) {
            return 1;
        }
        return 0;
    }

    /**
     * Retorna una cadena de string con los atributos representativos del equipo
     *
     * @return cadena de string
     * @see Equipo#toString()
     * @see #toString()
     */
    @Override
    public String toString() {
        return equipoNombre + ", " + puntaje + " puntos";
    }
}
