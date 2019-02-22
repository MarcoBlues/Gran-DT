package grandt;

/**
 * Esta clase representa al competidor(usuario) que se logueara para dar inicio
 * al gran dt.
 */
import java.io.Serializable;

public class Competidor implements Serializable {

    private static final long serialVersionUID = -5309838498103281073L;

    private String nombre;
    private Equipo miEquipo;
    protected Fecha fecha;

    public Competidor() {

    }

    /**
     * Contructor para inicializar el objeto Competidor.
     *
     * @param nombre nombre del competidor
     * @param miEquipo equipo que arma el competidor con sus jugadores elegidos
     * @param fecha objeto que contiene las fechas a disputar para los partidos
     * @see Equipo
     * @see Fecha
     * @see #Competidor(String nombre, Equipo miEquipo, Fecha fecha)
     */
    public Competidor(String nombre, Equipo miEquipo, Fecha fecha) {
        this.nombre = nombre;
        this.miEquipo = miEquipo;
        this.fecha = fecha;
    }

    /**
     * Establece el nombre del competidor.
     *
     * @param nombre nombre del competidor
     * @see #setNombre(String nombre)
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el equipo del competidor.
     *
     * @param miEquipo equipo que arma el competidor con sus jugadores elegidos
     * @see #setMiEquipo(Equipo miEquipo)
     */
    public void setMiEquipo(Equipo miEquipo) {
        this.miEquipo = miEquipo;
    }

    /**
     * Establece la fecha a disputar del competidor.
     *
     * @param fecha objeto que contiene las fechas a disputar para los partidos
     * @see #setFecha(Fecha fecha)
     */
    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la fecha a disputar del competidor.
     *
     * @return {@link #fecha}
     * @see #getFecha()
     */
    public Fecha getFecha() {
        return fecha;
    }

    /**
     * Obtiene el equipo del competidor.
     *
     * @return {@link #miEquipo}
     * @see #getMiEquipo()
     */
    public Equipo getMiEquipo() {
        return miEquipo;
    }

    /**
     * Obtiene el nombre del competidor.
     *
     * @return {@link #nombre}
     * @see #getNombre()
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna una cadena de string con los atributos representativos
     * competidor.
     *
     * @return cadena de string
     * @see Jugador#toString()
     * @see #toString()
     */
    @Override
    public String toString() {
        return "Competidor{" + "nombreCompetidor=" + nombre + ", miEquipo=" + miEquipo + '}';
    }

}
