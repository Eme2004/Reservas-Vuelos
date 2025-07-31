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
 * @author USER
 */
public class Reserva extends JFrame {

    private Vuelo vuelo;
    private int idUsuario;

    private JLabel lblInfoVuelo;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JLabel lblDocumento;
    private JTextField txtDocumento;
    private JLabel lblCantidad;
    private JSpinner spnCantidad;
    private JButton btnReservar;

    public Reserva(Vuelo vuelo, int idUsuario) {
        this.vuelo = vuelo;
        this.idUsuario = idUsuario;

        setTitle("Reservar Vuelo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.anchor = GridBagConstraints.WEST;

        // Información vuelo
        lblInfoVuelo = new JLabel("Vuelo: " + vuelo.getOrigen() + " → " + vuelo.getDestino());
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblInfoVuelo, gbc);

        gbc.gridwidth = 1;

        // Nombre
        lblNombre = new JLabel("Nombre:");
        gbc.gridx = 0; gbc.gridy = 1;
        add(lblNombre, gbc);

        txtNombre = new JTextField(20);
        gbc.gridx = 1;
        add(txtNombre, gbc);

        // Documento
        lblDocumento = new JLabel("Documento:");
        gbc.gridx = 0; gbc.gridy = 2;
        add(lblDocumento, gbc);

        txtDocumento = new JTextField(20);
        gbc.gridx = 1;
        add(txtDocumento, gbc);

        // Cantidad asientos
        lblCantidad = new JLabel("Cantidad asientos:");
        gbc.gridx = 0; gbc.gridy = 3;
        add(lblCantidad, gbc);

        spnCantidad = new JSpinner(new SpinnerNumberModel(1, 1, vuelo.getAsientosDisponibles(), 1));
        gbc.gridx = 1;
        add(spnCantidad, gbc);

        // Botón reservar
        btnReservar = new JButton("Reservar");
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnReservar, gbc);

        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarReserva();
            }
        });
    }

    private void realizarReserva() {
    String nombre = txtNombre.getText().trim();
    String documento = txtDocumento.getText().trim();
    int cantidad = (int) spnCantidad.getValue();

    if(nombre.isEmpty() || documento.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
        return;
    }

    if(cantidad > vuelo.getAsientosDisponibles()) {
        JOptionPane.showMessageDialog(this, "No hay suficientes asientos disponibles.");
        return;
    }

    ReservaDAO dao = new ReservaDAO();
    boolean exito = dao.insertarReserva(vuelo.getIdVuelo(), idUsuario, nombre, documento, cantidad);

    if(exito) {
        JOptionPane.showMessageDialog(this, "Reserva realizada con éxito para " + nombre + ".");
        vuelo.setAsientosDisponibles(vuelo.getAsientosDisponibles() - cantidad);
        dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Error al realizar la reserva. Intente de nuevo.");
    }
}

}