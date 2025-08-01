/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

/**
 *
 * @author Emesis
 */
public class VentanaReservas extends javax.swing.JFrame {
    // Variable para almacenar el ID del usuario actual
    private int idUsuario;

    // Constructor que recibe el ID del usuario que ha iniciado sesión
    public VentanaReservas(int idUsuario) {
        initComponents();          // Inicializa los componentes gráficos de la ventana
        this.idUsuario = idUsuario; // Asigna el ID recibido a la variable de la clase
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
}
    /**
     * Creates new form VentanaReservas
     */
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnBuscarVuelos = new javax.swing.JButton();
        btnVerReservas = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/vuelo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnBuscarVuelos.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnBuscarVuelos.setText("Buscar Vuelos");
        btnBuscarVuelos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarVuelosActionPerformed(evt);
            }
        });

        btnVerReservas.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnVerReservas.setText("Ver Mis Reservas");
        btnVerReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerReservasActionPerformed(evt);
            }
        });

        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnCerrarSesion.setText("Cerra Sesion");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btnBuscarVuelos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVerReservas)
                .addGap(34, 34, 34))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(160, 160, 160))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarVuelos)
                    .addComponent(btnVerReservas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarVuelosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVuelosActionPerformed
        // Acción que se ejecuta al presionar el botón "Buscar Vuelos"
    // Crea una nueva ventana de búsqueda de vuelos, pasando el ID del usuario actual
    VentanaBusqueda busqueda = new VentanaBusqueda(idUsuario);
    // Muestra la ventana de búsqueda
    busqueda.setVisible(true);
    // Cierra la ventana actual para evitar duplicados
    this.dispose();
    }//GEN-LAST:event_btnBuscarVuelosActionPerformed

    private void btnVerReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerReservasActionPerformed
        // Acción que se ejecuta al presionar el botón "Ver Reservas"
    // Crea una nueva ventana para mostrar las reservas del usuario actual
    MisReservas ventana = new MisReservas(idUsuario);
    // Muestra la ventana de reservas
    ventana.setVisible(true);
    }//GEN-LAST:event_btnVerReservasActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
     // Acción que se ejecuta al presionar el botón "Cerrar Sesión"
    // Termina la ejecución de la aplicación completamente
    System.exit(0);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarVuelos;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnVerReservas;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
