/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author USER
 */
public class VentanaPrincipal extends JFrame {
     private JButton btnRegistro;
    private JButton btnLogin;
    private JButton btnVerVuelos;
    private JButton btnReservar;
    private JButton btnMisReservas;
    private JButton btnSalir;

    public VentanaPrincipal() {
        setTitle("Sistema de Reservas de Vuelo");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        btnRegistro = new JButton("Registrar Usuario");
        btnLogin = new JButton("Iniciar Sesión");
        btnVerVuelos = new JButton("Buscar Vuelos");
        btnReservar = new JButton("Hacer Reserva");
        btnMisReservas = new JButton("Mis Reservas");
        btnSalir = new JButton("Salir");

        add(btnRegistro);
        add(btnLogin);
        add(btnVerVuelos);
        add(btnReservar);
        add(btnMisReservas);
        add(btnSalir);

        // Acciones
        btnRegistro.addActionListener(e -> abrirRegistro());
        btnLogin.addActionListener(e -> JOptionPane.showMessageDialog(this, "Función de login (por implementar)"));
        btnVerVuelos.addActionListener(e -> JOptionPane.showMessageDialog(this, "Función para ver vuelos (por implementar)"));
        btnReservar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Función para hacer reservas (por implementar)"));
        btnMisReservas.addActionListener(e -> JOptionPane.showMessageDialog(this, "Función para ver reservas (por implementar)"));
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void abrirRegistro() {
        RegistroUsuario reg = new RegistroUsuario();
        reg.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal menu = new VentanaPrincipal();
            menu.setVisible(true);
        });
    }
}
