package grandt;

/**
 * Clase de tipo enum que contendra el tipo de posicion en la cual se
 * especializa el jugador, delantero, volante, defensor o arquero.
 */
public enum TipoPosicion {

    ARQUERO(7),
    DEFENSOR(6),
    VOLANTE(5),
    DELANTERO(4);

    private final int puntajeGol;

    /**
     * Constructor para inicializar un objeto TipoPosicion
     *
     * @param puntajeGol cantidad de puntos que obtiene el jugador al realizar
     * un gol segun posicion.
     * @see #TipoPosicion(int puntajeGol)
     */
    TipoPosicion(int puntajeGol) {
        this.puntajeGol = puntajeGol;
    }

    /**
     * Obtiene el puntaje por gol segun su posicion.
     *
     * @return {@link #puntajeGol}
     * @see #getPuntajeGol()
     */
    public int getPuntajeGol() {
        return puntajeGol;
    }

}
