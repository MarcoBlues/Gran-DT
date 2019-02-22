package grandt;

/**
 * Esta clase posee las caracteristicas de un jugador tanto como atributos
 * acerca de su rendimiento en el partido y sus datos personales.
 */
import java.io.Serializable;
import java.util.Objects;

public class Jugador implements Serializable, Comparable<Jugador> {

    private static final long serialVersionUID = -5309838498103281073L;

    private int cotizacion;
    private int goles = 0;
    private int asistencias = 0;
    private int rojas = 0;
    private int amarillas = 0;
    private int puntajeJugador = 0;
    private TipoPosicion TIPO_POSICION;
    private String nombre;
    private String apellido;

    public Jugador() {

    }

    /**
     * Constructor para inicializar el objeto Jugador.
     *
     * @param TIPO_POSICION es el tipo de posicion que posee el jugador
     * @param cotizacion valor del jugador en el mercado
     * @param nombre nombre del jugador
     * @param apellido apellido del jugador
     * @see #Jugador(TipoPosicion TIPO_POSICION, int cotizacion, String nombre,
     * String apellido)
     */
    public Jugador(TipoPosicion TIPO_POSICION, int cotizacion, String nombre, String apellido) {
        this.TIPO_POSICION = TIPO_POSICION;
        this.cotizacion = cotizacion;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    /**
     * calcula y obtiene el puntaje de un jugador basado en su estadisticas en
     * los partidos, goles, asistencias, rojas y amarillas
     *
     * @return {@link #puntajeJugador}
     * @see #calcularPuntajeJugador()
     */
    public int calcularPuntajeJugador() {
        return puntajeJugador = (goles * TIPO_POSICION.getPuntajeGol()) + (asistencias * 2) - (rojas * 2) - amarillas;
    }

    /**
     * Obtiene los goles de un jugador.
     *
     * @return {@link #goles}
     * @see #getGoles()
     */
    public int getGoles() {
        return goles;
    }

    /**
     * Incrementa los goles de un jugador.
     *
     * @param goles cantidad de goles hechos por un jugador
     * @see #setGoles(int goles)
     */
    public void setGoles(int goles) {
        this.goles += goles;
    }

    /**
     * Obtiene las asistencias de un jugador.
     *
     * @return {@link #asistencias}
     * @see #getAsistencias()
     */
    public int getAsistencias() {
        return asistencias;
    }

    /**
     * Incrementa las asistencias de un jugador.
     *
     * @param asistencias cantidad de asistencias del jugador
     * @see #setAsistencias(int asistencias)
     */
    public void setAsistencias(int asistencias) {
        this.asistencias += asistencias;
    }

    /**
     * Obtiene la cantidad de tarjetas rojas del jugador.
     *
     * @return {@link #rojas}
     * @see #getRojas()
     */
    public int getRojas() {
        return rojas;
    }

    /**
     * Incrementa la cantidad de tarjetas rojas del jugador.
     *
     * @param rojas Cantidad de tarjetas rojas de un jugador
     * @see #setRojas(int rojas)
     */
    public void setRojas(int rojas) {
        this.rojas += rojas;
    }

    /**
     * Obtiene la cantidad de tarjetas amarillas del jugador.
     *
     * @return {@link #amarillas}
     * @see #getAmarillas()
     */
    public int getAmarillas() {
        return amarillas;
    }

    /**
     * Incrementa la cantidad de tarjetas amarillas del jugador.
     *
     * @param amarillas cantidad de tarjetas amarillas de un jugador
     * @see #setAmarillas(int amarillas)
     */
    public void setAmarillas(int amarillas) {
        this.amarillas += amarillas;
    }

    /**
     * Obtiene la cotizacion de un jugador en el mercado.
     *
     * @return {@link #cotizacion}
     * @see #getCotizacion()
     */
    public int getCotizacion() {
        return cotizacion;
    }

    /**
     * Establece la cotizacion de un jugador en el mercado.
     *
     * @param cotizacion valor del jugador en el mercado
     * @see #setCotizacion(int cotizacion)
     */
    public void setCotizacion(int cotizacion) {
        this.cotizacion = cotizacion;
    }

    /**
     * Obtiene el puntaje total del jugador.
     *
     * @return {@link #puntajeJugador}
     * @see #getPuntajeJugador()
     */
    public int getPuntajeJugador() {
        return puntajeJugador;
    }

    /**
     * Establece el puntaje del jugador.
     *
     * @param puntajeJugador son los puntos del jugador que varian segun su
     * rendimiento en el partido
     * @see #setPuntajeJugador(int puntajeJugador)
     */
    public void setPuntajeJugador(int puntajeJugador) {
        this.puntajeJugador = puntajeJugador;
    }

    /**
     * Obtiene el tipo de posicion del jugador.
     *
     * @return {@link #TIPO_POSICION}
     * @see #getTIPO_POSICION()
     */
    public TipoPosicion getTIPO_POSICION() {
        return TIPO_POSICION;
    }

    /**
     * Establece el tipo de posicion del jugador.
     *
     * @param TIPO_POSICION Es el tipo de posicion del jugador, delantero,
     * defensor, volante o arquero.
     * @see #setTIPO_POSICION(TipoPosicion TIPO_POSICION)
     */
    public void setTIPO_POSICION(TipoPosicion TIPO_POSICION) {
        this.TIPO_POSICION = TIPO_POSICION;
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return {@link #nombre}
     * @see #getNombre()
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Estable el nombre del jugador.
     *
     * @param nombre nombre del jugador
     * @see #setNombre(String nombre)
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del jugador.
     *
     * @return {@link #apellido}
     * @see #getApellido()
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del jugador.
     *
     * @param apellido apellido del jugador
     * @see #setApellido(String apellido)
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene hash del objeto jugador segun su nombre, apellido, posicion,
     * cotizacion y puntaje.
     *
     * @return el valor hash del objeto
     * @see Jugador#hashCode()
     * @see #hashCode()
     */
    @Override
    public int hashCode() {
        int rta = nombre.hashCode();
        rta = 31 * rta + apellido.hashCode();
        rta = 31 * rta + TIPO_POSICION.hashCode();
        rta = 31 * rta + cotizacion;
        rta = 31 * rta + puntajeJugador;
        return rta;
    }

    /**
     * Compara el contenido del objeto jugador
     *
     * @param obj objeto a comparar
     * @return true si es igual, false si no lo es.
     * @see Jugador#equals(Object obj)
     * @see #equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Jugador other = (Jugador) obj;
        if (this.cotizacion != other.cotizacion) {
            return false;
        }
        if (this.puntajeJugador != other.puntajeJugador) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        return this.TIPO_POSICION == other.TIPO_POSICION;
    }

    /**
     * Compara el objeto jugador del parametro y determina si es igual, mayor o
     * menor.
     *
     * @param o Objeto de tipo jugador a comparar
     * @return 1 si es mayor, -1 si es menor o 0 si es igual
     */
    @Override
    public int compareTo(Jugador o) {
        if (puntajeJugador < o.puntajeJugador) {
            return -1;
        }
        if (puntajeJugador > o.puntajeJugador) {
            return 1;
        }
        return 0;
    }

    /**
     * Retorna una cadena de string con los atributos representativos del
     * jugador
     *
     * @return cadena de string
     * @see Jugador#toString()
     * @see #toString()
     */
    @Override
    public String toString() {
        return nombre + " " + apellido + " [" + TIPO_POSICION.name().toLowerCase()
                + ", Cotizacion: " + cotizacion + ", Puntaje: " + puntajeJugador
                + ", Goles: " + goles + ", Asistencias: " + asistencias + ", Rojas: "
                + rojas + ", Amarillas: " + amarillas + ']';
    }

}
