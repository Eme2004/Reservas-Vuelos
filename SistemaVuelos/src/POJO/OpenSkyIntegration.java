/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Emesis
 */
public class OpenSkyIntegration {

    // Interfaz para callbacks que notifican cuando se obtienen vuelos o cuando hay error
    public interface VuelosCallback {
        // Se llama cuando se obtienen los vuelos exitosamente, pasando la lista
        void onVuelosObtenidos(List<VueloReal> vuelos);
        // Se llama si ocurre un error, pasando el mensaje de error
        void onError(String error);
    }

    // Método estático para obtener vuelos en tiempo real desde la API de OpenSky
    public static void obtenerVuelosEnTiempoReal(VuelosCallback callback) {
        // Crear un hilo nuevo para no bloquear la interfaz gráfica mientras se obtiene la data
        new Thread(() -> {
            try {
                // URL pública de la API REST de OpenSky para obtener todos los estados de vuelo
                URL url = new URL("https://opensky-network.org/api/states/all");

                // Abrir conexión HTTP con la URL
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET"); // Método GET para obtener datos

                // Obtener código de respuesta HTTP para verificar si la llamada fue exitosa
                int status = con.getResponseCode();
                if (status != 200) { // Si no es código 200 OK
                    callback.onError("Código de respuesta: " + status); // Reportar error
                    return; // Terminar ejecución
                }

                // Leer la respuesta JSON de la API línea por línea
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                // Construir la cadena completa de respuesta JSON
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();        // Cerrar buffer de lectura
                con.disconnect();  // Cerrar conexión HTTP

                // Parsear la cadena JSON en objeto JSONObject
                JSONObject json = new JSONObject(response.toString());
                // Extraer arreglo 'states' que contiene información de vuelos
                JSONArray states = json.getJSONArray("states");

                // Crear lista donde se guardarán los vuelos válidos
                List<VueloReal> vuelos = new ArrayList<>();

                // Iterar sobre cada vuelo (cada elemento es un JSONArray)
                for (int i = 0; i < states.length(); i++) {
                    JSONArray vueloArray = states.getJSONArray(i);

                    // Extraer datos por índice según documentación OpenSky
                    String callsign = vueloArray.optString(1).trim(); // Código de vuelo (avión)
                    String origen = vueloArray.optString(2);           // Origen - no siempre está disponible
                    String destino = vueloArray.optString(3);          // Destino - no disponible normalmente
                    Double altitud = vueloArray.optDouble(7, 0.0);     // Altitud en metros
                    Double velocidad = vueloArray.optDouble(9, 0.0);   // Velocidad en m/s
                    Long timestamp = vueloArray.optLong(4, System.currentTimeMillis() / 1000L); // Última actualización (epoch segundos)

                    // Convertir timestamp a fecha legible yyyy-MM-dd HH:mm:ss
                    String fechaActualizacion = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .format(new Date(timestamp * 1000));

                    // Agregar solo vuelos que tengan callsign válido (no vacío)
                    if (callsign != null && !callsign.isEmpty()) {
                        vuelos.add(new VueloReal(callsign, origen, destino, altitud, velocidad, fechaActualizacion));
                    }
                }

                // Llamar callback con la lista de vuelos obtenida
                callback.onVuelosObtenidos(vuelos);

            } catch (Exception e) {
                // Si hay error (por ejemplo, fallo conexión o parseo), notificar con mensaje
                callback.onError(e.getMessage());
            }
        }).start(); // Iniciar el hilo para que corra paralelo
    }

    // Clase interna que representa un vuelo real con sus atributos principales
    public static class VueloReal {
        private final String callsign;           // Código identificador del vuelo
        private final String origen;             // Ciudad o aeropuerto origen (si está disponible)
        private final String destino;            // Ciudad o aeropuerto destino (normalmente vacío)
        private final double altitud;            // Altitud en metros
        private final double velocidad;          // Velocidad en m/s
        private final String fechaActualizacion; // Fecha/hora de última actualización (formato string)

        // Constructor para inicializar todos los campos del vuelo
        public VueloReal(String callsign, String origen, String destino, double altitud, double velocidad, String fechaActualizacion) {
            this.callsign = callsign;
            this.origen = origen;
            this.destino = destino;
            this.altitud = altitud;
            this.velocidad = velocidad;
            this.fechaActualizacion = fechaActualizacion;
        }

        // Métodos get para acceder a cada atributo

        public String getCallsign() { return callsign; }

        public String getOrigen() { return origen != null ? origen : "N/A"; }

        public String getDestino() { return destino != null ? destino : "N/A"; }

        public double getAltitud() { return altitud; }

        public double getVelocidad() { return velocidad; }

        public String getFechaActualizacion() { return fechaActualizacion; }
    }
}

