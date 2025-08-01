/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import DAO.ReservaDAO;
import POJO.Vuelo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Emesis
 */
/**
 * Clase que representa la ventana para realizar una reserva de un vuelo.
 * Permite ingresar datos del cliente, cantidad de asientos y realizar la reserva.
 */
public class Reserva extends JFrame {

    private Vuelo vuelo;       // Objeto vuelo que se va a reservar
    private int idUsuario;     // ID del usuario que realiza la reserva

    // Componentes gráficos
    private JLabel lblInfoVuelo;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JLabel lblDocumento;
    private JTextField txtDocumento;
    private JLabel lblCantidad;
    private JSpinner spnCantidad;
    private JButton btnReservar;

    /**
     * Constructor que inicializa la ventana con los datos del vuelo y el usuario.
     * Configura la interfaz gráfica con campos para el cliente y cantidad de asientos.
     * 
     * @param vuelo Objeto Vuelo con información del vuelo a reservar
     * @param idUsuario ID del usuario que realiza la reserva
     */
    public Reserva(Vuelo vuelo, int idUsuario) {
        this.vuelo = vuelo;
        this.idUsuario = idUsuario;

        // Configuración básica de la ventana
        setTitle("Reservar Vuelo");
        setSize(400, 300);
        setLocationRelativeTo(null);                    // Centra la ventana en pantalla
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Solo cierra esta ventana, no toda la app
        setLayout(new GridBagLayout());                 // Usamos GridBagLayout para organización flexible

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);                // Margen alrededor de cada componente
        gbc.anchor = GridBagConstraints.WEST;            // Alinear componentes a la izquierda

        // Mostrar información del vuelo (origen → destino)
        lblInfoVuelo = new JLabel("Vuelo: " + vuelo.getOrigen() + " → " + vuelo.getDestino());
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.gridwidth = 2;                              // Ocupa dos columnas
        add(lblInfoVuelo, gbc);

        gbc.gridwidth = 1;                              // Volver a una columna

        // Etiqueta y campo para nombre del cliente
        lblNombre = new JLabel("Nombre:");
        gbc.gridx = 0; 
        gbc.gridy = 1;
        add(lblNombre, gbc);

        txtNombre = new JTextField(20);
        gbc.gridx = 1;
        add(txtNombre, gbc);

        // Etiqueta y campo para documento del cliente
        lblDocumento = new JLabel("Documento:");
        gbc.gridx = 0; 
        gbc.gridy = 2;
        add(lblDocumento, gbc);

        txtDocumento = new JTextField(20);
        gbc.gridx = 1;
        add(txtDocumento, gbc);

        // Etiqueta y spinner para cantidad de asientos a reservar
        lblCantidad = new JLabel("Cantidad asientos:");
        gbc.gridx = 0; 
        gbc.gridy = 3;
        add(lblCantidad, gbc);

        // Spinner con mínimo 1 y máximo la cantidad de asientos disponibles del vuelo
        spnCantidad = new JSpinner(new SpinnerNumberModel(1, 1, vuelo.getAsientosDisponibles(), 1));
        gbc.gridx = 1;
        add(spnCantidad, gbc);

        // Botón para realizar la reserva
        btnReservar = new JButton("Reservar");
        gbc.gridx = 0; 
        gbc.gridy = 4; 
        gbc.gridwidth = 2;                              // Ocupa dos columnas
        gbc.anchor = GridBagConstraints.CENTER;        // Centrar el botón
        add(btnReservar, gbc);

        // Acción al hacer clic en el botón "Reservar"
        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarReserva();
            }
        });
    }

    /**
     * Método que se ejecuta al hacer clic en "Reservar".
     * Valida los datos, intenta insertar la reserva en la base de datos y actualiza la cantidad de asientos.
     */
    private void realizarReserva() {
        String nombre = txtNombre.getText().trim();
        String documento = txtDocumento.getText().trim();
        int cantidad = (int) spnCantidad.getValue();

        // Validar que los campos no estén vacíos
        if(nombre.isEmpty() || documento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        // Validar que la cantidad solicitada no supere la disponibilidad
        if(cantidad > vuelo.getAsientosDisponibles()) {
            JOptionPane.showMessageDialog(this, "No hay suficientes asientos disponibles.");
            return;
        }

        // Crear instancia DAO para insertar la reserva
        ReservaDAO dao = new ReservaDAO();
        boolean exito = dao.insertarReserva(vuelo.getIdVuelo(), idUsuario, nombre, documento, cantidad);

        if(exito) {
            JOptionPane.showMessageDialog(this, "Reserva realizada con éxito para " + nombre + ".");
            // Actualizar localmente la cantidad de asientos disponibles
            vuelo.setAsientosDisponibles(vuelo.getAsientosDisponibles() - cantidad);
            dispose();   // Cerrar la ventana de reserva
        } else {
            JOptionPane.showMessageDialog(this, "Error al realizar la reserva. Intente de nuevo.");
        }
    }
}