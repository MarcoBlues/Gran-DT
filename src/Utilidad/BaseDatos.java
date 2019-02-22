package Utilidad;

/**
 * Clase generica que representa a la base de datos de los jugadores de nuestro
 * programa, permite guardar y cargar la partida del competidor.
 */
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import grandt.Equipo;
import grandt.Simular;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseDatos<T> {

    private File archivo;

    /**
     * Constructor para inicializar el objeto BaseDatos.
     *
     * @param nombreArchivoMasFormato cadena que contiene el nombre del archivo
     * a cargar y/o guardar.
     * @see #BaseDatos(String nombreArchivoMasFormato)
     */
    public BaseDatos(String nombreArchivoMasFormato) {
        archivo = new File(nombreArchivoMasFormato);
    }

    public BaseDatos() {

    }

    /*
    public List<Equipo> cargarArchivo() {

        File file = new File("ARCHIVOS_DE_EQUIPOS.txt");
        List<Equipo> equipos = new ArrayList<>();
        try {
            FileInputStream entrada = new FileInputStream(file);    // flujo de entrada
            ObjectInputStream reader = new ObjectInputStream(entrada);   // objetos que vamos a leer
            equipos = (List<Equipo>) reader.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        return equipos;
    }

    public void escribirArchivo(ArrayList<Equipo> equipos) {
        FileOutputStream salida = null;
        ObjectOutputStream writer = null;
        
        try {
            System.out.println("Escribiendo en archivo");
            archivo.createNewFile();
            salida = new FileOutputStream(archivo);    // flujo de salida
            writer = new ObjectOutputStream(salida); // objeto para la salida para la escritura
            writer.writeObject(equipos);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (salida != null) {
                try {
                    salida.close();
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
     */
    /**
     * Metodo que permite guardar la partida del competidor y el archivo de
     * jugadores.
     *
     * @param t tipo generico, representa la partida del competidor y la base de
     * datos
     * @see #guardarPartida(java.lang.Object)
     */
    public void guardarPartida(T t) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(archivo, t);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Metodo que permite cargar la partida del competidor junto a su base de
     * datos propia al competidor.
     *
     * @param archi tipo File que representa el archivo de competidor a cargar
     * @return objeto de tipo Simular
     * @see #cargarPartida(File archi)
     * @see Simular
     */
    public Simular cargarPartida(File archi) {

        Simular progreso = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            if (archi.exists()) {
                progreso = mapper.readValue(archi, Simular.class);
                System.out.println("¡Cuenta cargada exitosamente!");
            } else {
                System.out.println("¡La cuenta NO existe!");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return progreso;
    }

    /**
     * Metodo que permite cargar la base de datos por defecto del programa
     *
     * @return objeto de tipo Equipo con todos los jugadores
     * @see #cargarJugadoresJackson()
     * @see Equipo
     */
    public List<Equipo> cargarJugadoresJackson() {

        List<Equipo> equipos = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            equipos = mapper.readValue(new File("archivo_jugadores.json"), new TypeReference<List<Equipo>>() {
            });
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return equipos;
    }
    /*
    public <T> List<T> cargarJacksonGenerico(T t) {

        try {
            ObjectMapper mapper = new ObjectMapper();

            t = mapper.readValue(archivo, new TypeReference<List<T>>() {
            });
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return (List<T>) t;
    }*/

 /*
// Esta funcion vamos a dejarla en stand by hasta el ultimo dia de entrega,despues la borraremos
    public static final String[] NOMBRES_EQUIPOS = {"Argentinos Juniors", "Arsenal de Sarandí",
        "Atlético Tucumán", "Banfield", "Belgrano de Córdoba", "Boca Juniors",
        "Chacarita", "Colón de Santa Fe", "Defensa y Justicia", "Estudiantes",
        "Gimnasia LP", "Godoy Cruz", "Huracán", "Independiente", "Lanús",
        "Newell`s Old Boys", "Olimpo", "Patronato", "Racing", "River Plate",
        "Rosario Central", "San Lorenzo", "San Martín de San Juan",
        "Talleres", "Temperley", "Tigre", "Unión de Santa Fe", "Vélez Sarsfield"};

    
    public static final String BANDERAS[] = {"Argentina", "Paraguay", "Peru",
        "Uruguay", "Colombia", "Ecuador", "Venezuela",
        "Chile", "Estados Unidos", "Armenia", "Tomey"};
     
    public Equipo[] AlgotParaArchiDGian(Equipo[] equipo) {

        List<Jugador> jugador = new LinkedList<>();

        String indexAnt = "0";
        String lineReaded;
        String[] contacto;

        File file = new File("archivoJugadores.txt");
        try {
            if (file.exists()) {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while ((lineReaded = bufferedReader.readLine()) != null) {

                    contacto = lineReaded.split("_");

                    if (contacto[0].compareTo(indexAnt) != 0) {
                        jugador = new LinkedList<>();
                    }

                    jugador.add(new Jugador(posicion(contacto[1]), Integer.parseInt(contacto[9]), contacto[3], contacto[4]));
                    equipo[Integer.parseInt(contacto[0])] = new Equipo(NOMBRES_EQUIPOS[Integer.parseInt(contacto[0])], 100000, (LinkedList<Jugador>) jugador);

                    indexAnt = contacto[0];
                }
            }
        } catch (IOException e) {
            System.out.println("Se cago todo");
        }
        return equipo;
    }

    private TipoPosicion posicion(String posicion) {
        TipoPosicion POSICION = null;

        switch (posicion) {
            case "0":
                POSICION = TipoPosicion.ARQUERO;
                break;
            case "1":
                POSICION = TipoPosicion.DEFENSOR;
                break;
            case "2":
                POSICION = TipoPosicion.VOLANTE;
                break;
            case "3":
                POSICION = TipoPosicion.DELANTERO;
                break;
        }
        return POSICION;
    }
     */
}
