/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import DAO.usuarioDAO;
import POJO.usuario;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author USER
 */
public class RegistroUsuario extends JFrame{
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnRegistrar;

    public RegistroUsuario() {
        setTitle("Registro de Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);

        txtNombre = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtNombre, gbc);

        // Correo
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Correo:"), gbc);

        txtCorreo = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtCorreo, gbc);

        // Contraseña
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Contraseña:"), gbc);

        txtContrasena = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(txtContrasena, gbc);

        // Botón registrar
        btnRegistrar = new JButton("Registrar");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(btnRegistrar, gbc);

        add(panel);

        // Evento del botón
        btnRegistrar.addActionListener(e -> registrarUsuario());
    }

    private void registrarUsuario() {
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();

        if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Todos los campos son obligatorios.");
            return;
        }

        usuario usuario = new usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);

        usuarioDAO dao = new usuarioDAO();
        if (dao.insertar(usuario)) {
            JOptionPane.showMessageDialog(this, "✅ Usuario registrado correctamente.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "❌ Error: el correo ya está en uso o hay un problema.");
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtCorreo.setText("");
        txtContrasena.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegistroUsuario ventana = new RegistroUsuario();
            ventana.setVisible(true);
        });
    }
}
