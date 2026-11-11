package structures.management;

import model.LecturaSensor;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GestorDeRed - Módulo Integrador de Estructuras de Datos
 * VERSION ESTUDIANTE
 *
 * Objetivo: Implementar la gestión de la red utilizando las colecciones de Java (JCF).
 * El éxito de este módulo no es solo que el código compile, sino que elijas
 * la estructura correcta para cada problema.
 */
public class GestorDeRed {
    // 1. Historial de Eventos: Registro lineal de todas las actividades
    private List<String> historialEventos;

    // 2. Navegador de Alertas: Permite moverse adelante y atrás entre alertas
    private Deque<String> navegadorAlertas;
    private String alertaActual;

    // 3. Ciclo de Monitoreo: Gestión de la rotación de sensores a consultar
    private Queue<String> colaMonitoreo;

    // 4. Topología de la Red: Grafo de conexiones entre estaciones (Lista de adyacencia)
    private Map<String, List<String>> topologiaRed;

    // 5. Buffer de Ingesta: Cola de espera para procesar lecturas entrantes
    private Queue<LecturaSensor> bufferIngesta;



    public GestorDeRed() {

        this.historialEventos = new LinkedList<>();
        this.navegadorAlertas = new ArrayDeque<>();
        this.colaMonitoreo = new ArrayDeque<>();
        this.topologiaRed = new HashMap<>();
        this.bufferIngesta = new ArrayDeque<>();
    }

    // =========================================================================
    // SECCIÓN 1: HISTORIAL Y EVENTOS
    // =========================================================================

    public void agregarEvento(String evento) {
        String marca = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date());
        historialEventos.add("[" + marca + "] " + evento);
    }

    public List<String> obtenerEventosRecientes(int limite) {
        if (limite <= 0) return new ArrayList<>();
        if (historialEventos.size() <= limite) return new ArrayList<>(historialEventos);
        return new ArrayList<>(historialEventos.subList(historialEventos.size() - limite, historialEventos.size()));
    }



    public void limpiarHistorialAntiguo(int mantener) {   // Conserva solo los últimos 'mantener' eventos
        int limite = Math.max(mantener, 0);   // Si 'mantener' es negativo, se usa 0 para evitar un bucle raro
        while (historialEventos.size() > limite) {   // Mientras haya más eventos de los permitidos...
            historialEventos.remove(0);   // ...elimina el más antiguo (índice 0, el primero); en LinkedList es O(1)
        }



    // =========================================================================
    // SECCIÓN 2: NAVEGACIÓN DE ALERTAS
    // =========================================================================

    public void registrarAlerta(String alerta) {
        navegadorAlertas.addLast(alerta);
        alertaActual = alerta;
    }


    public String navegarAlSiguiente() {

            // TODO 2: desplazamiento hacia adelante (ciclo).
            // Invariante: la alerta actual es siempre el ÚLTIMO elemento del Deque
            // (registrarAlerta() hace addLast y marca esa alerta como actual).
            // Avanzar = sacar el frente y reinsertarlo al final: la siguiente alerta pasa a ser la actual.
            if (navegadorAlertas.isEmpty()) return null;
            String siguiente = navegadorAlertas.pollFirst();
            navegadorAlertas.addLast(siguiente);
            alertaActual = siguiente;
            return alertaActual;
    }


    public String navegarAlAnterior() {
        // TODO 3: desplazamiento hacia atrás (operación inversa del TODO 2).
        // Retroceder = sacar el último elemento y reinsertarlo al frente:
        // la alerta que quedaba justo antes de la actual pasa a ser el nuevo último (la actual).
        if (navegadorAlertas.isEmpty()) return null;
        String saliente = navegadorAlertas.pollLast();
        navegadorAlertas.addFirst(saliente);
        alertaActual = navegadorAlertas.peekLast();
        return alertaActual;

    }

    public String getAlertaActual() { return alertaActual; }

    // =========================================================================
    // SECCIÓN 3: CICLO DE MONITOREO
    // =========================================================================

    public void programarConsulta(String idSensor) {
        // TODO 4: el sensor se agrega a la cola solo si NO está ya programado.
        if (idSensor != null && !colaMonitoreo.contains(idSensor)) {
            colaMonitoreo.add(idSensor);

        }
    }



    public String obtenerSiguienteSensor() {
            // TODO 5: rotación del ciclo: se extrae el sensor del frente y se reinserta al final.
            // poll() devuelve null si la cola está vacía, así que no hay excepción.
        String sensor = colaMonitoreo.poll();
        if (sensor == null) return null;
        colaMonitoreo.add(sensor);
        return sensor;
        }




    // =========================================================================
    // SECCIÓN 4: TOPOLOGÍA DE RED
    // =========================================================================

    public void registrarConexion(String sensorA, String sensorB) {
        // TODO 6: Implementar la conexión bidireccional entre dos sensores.
        // Debe agregar sensorB a la lista de vecinos de sensorA, y viceversa.
        // Asegurar que no se creen conexiones duplicadas.
    }

    public List<String> obtenerVecinos(String idSensor) {
        // TODO 7: Implementar la consulta de conexiones.
        // Debe devolver la lista de sensores conectados al idSensor proporcionado.
        // Retornar una lista vacía si el sensor no tiene conexiones.
        return null;
    }

    public List<String> detectarEstacionesAisladas() {
        // TODO 9: Implementar la detección de nodos huérfanos.
        // Debe retornar una lista de todos los sensores registrados que no tienen ninguna conexión.
        return null;
    }

    // =========================================================================
    // SECCIÓN 5: BUFFER DE INGESTA
    // =========================================================================

    public void recibirLectura(LecturaSensor lectura) {
        bufferIngesta.add(lectura);
    }

    public LecturaSensor procesarSiguienteLectura() {
        // TODO 8: Implementar la extracción del buffer.
        // Debe remover y devolver la lectura más antigua del buffer (FIFO).
        return null;
    }

    public List<LecturaSensor> filtrarAlertasCriticas(double limitePm25) {
        // TODO EXTRA: Usar Streams para filtrar lecturas con PM2.5 superior al límite.
        // Debe retornar una lista con las lecturas filtradas.
        return null;
    }
}
