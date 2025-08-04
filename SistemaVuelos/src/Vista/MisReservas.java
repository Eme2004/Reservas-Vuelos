/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import DAO.ReservaDAO;
import DAO.ReservaInfo;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
/**
 *
 * @author Emesis
 */
/**
 * Clase que representa la ventana donde se muestran las reservas del usuario.
 * Hereda de JFrame e incluye una tabla para listar las reservas.
 */
public class MisReservas extends javax.swing.JFrame {
    
    // Modelo de tabla para gestionar las filas y columnas de la tabla de reservas
    private DefaultTableModel modelo;

    // ID del usuario cuyas reservas se van a mostrar
    private int idUsuario;
    
    /**
     * Constructor de la clase MisReservas.
     * Inicializa componentes, centra la ventana, recibe el ID del usuario,
     * inicializa la tabla y carga las reservas desde la base de datos.
     * 
     * @param idUsuario ID del usuario autenticado para filtrar reservas
     */
    public MisReservas(int idUsuario) {
        initComponents();           // Inicializa los componentes gráficos (tabla, botones, etc.)
        setLocationRelativeTo(null); // Centra la ventana en pantalla
        this.idUsuario = idUsuario;  // Guarda el ID del usuario
        inicializarTabla();          // Configura la tabla con las columnas necesarias
        cargarReservas();            // Carga y muestra las reservas del usuario en la tabla
        
        // Debug: muestra en consola el ID recibido para confirmar que está correcto
        System.out.println("ID de usuario recibido en MisReservas: " + idUsuario);
    }
    
    /**
     * Método que configura el modelo de la tabla para mostrar las columnas correctas
     * y asocia el modelo a la tabla visual.
     */
    private void inicializarTabla() {
        modelo = new DefaultTableModel(
            // Definición de los nombres de las columnas que tendrá la tabla
            new String[]{"ID Reserva", "Vuelo", "Origen", "Destino", "Fecha Vuelo", "Cantidad", "Fecha Reserva"}, 0
        );
        tablaReservas.setModel(modelo); // Asocia el modelo con la tabla visible
    }
    
    /**
     * Método que obtiene las reservas del usuario desde la base de datos mediante el DAO,
     * y las agrega al modelo para que se muestren en la tabla.
     */
    private void cargarReservas() {
        modelo.setRowCount(0); // Limpia filas previas para refrescar la tabla
        ReservaDAO dao = new ReservaDAO(); // Instancia el objeto para acceder a datos de reservas
        List<ReservaInfo> reservas = dao.obtenerReservasPorUsuario(idUsuario); // Trae las reservas

        // Recorre la lista de reservas y añade cada una como una fila en la tabla
        for (ReservaInfo r : reservas) {
            modelo.addRow(new Object[]{
                r.getIdReserva(),
                r.getNombreVuelo(),
                r.getOrigen(),
                r.getDestino(),
                r.getFechaVuelo(),
                r.getCantidad(),
                r.getFechaReserva()
            });
        }
}
   private void crearPDF(String filePath) throws FileNotFoundException {
    try {
        // Crear documento
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        // Fuentes
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 10);
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);

        // Título del documento
        Paragraph titulo = new Paragraph("REPORTE DE MIS RESERVAS", titleFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        document.add(titulo);

        // Fecha de generación
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Paragraph fecha = new Paragraph("Fecha de generación: " + dateFormat.format(new Date()), normalFont);
        fecha.setAlignment(Element.ALIGN_RIGHT);
        fecha.setSpacingAfter(20);
        document.add(fecha);

        // Verificar si hay reservas
        if (modelo.getRowCount() == 0) {
            Paragraph sinReservas = new Paragraph("No tienes reservas registradas.", normalFont);
            sinReservas.setAlignment(Element.ALIGN_CENTER);
            document.add(sinReservas);
        } else {
            // Agregar encabezados
            Paragraph encabezado = new Paragraph("LISTADO DE RESERVAS DE VUELOS", headerFont);
            encabezado.setSpacingAfter(15);
            document.add(encabezado);
            
            // Crear tabla con las columnas de tu modelo
            PdfPTable table = new PdfPTable(7); // 7 columnas
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);
            
            // Establecer anchos relativos de las columnas
            float[] columnWidths = {1f, 1.5f, 2f, 2f, 2f, 1f, 2f};
            table.setWidths(columnWidths);
            
            // Agregar encabezados de la tabla
            String[] headers = {"ID Reserva", "Vuelo", "Origen", "Destino", "Fecha Vuelo", "Cantidad", "Fecha Reserva"};
            Font headerTableFont = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
            
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, headerTableFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(8);
                cell.setBackgroundColor(new com.itextpdf.text.BaseColor(230, 230, 230));
                table.addCell(cell);
            }
            
            // Agregar datos de las reservas
            Font dataFont = new Font(Font.FontFamily.HELVETICA, 8);
            
            for (int i = 0; i < modelo.getRowCount(); i++) {
                // Columna 0: ID Reserva
                String idReserva = modelo.getValueAt(i, 0) != null ? modelo.getValueAt(i, 0).toString() : "";
                table.addCell(new PdfPCell(new Phrase(idReserva, dataFont)));
                
                // Columna 1: Vuelo
                String vuelo = modelo.getValueAt(i, 1) != null ? modelo.getValueAt(i, 1).toString() : "";
                table.addCell(new PdfPCell(new Phrase(vuelo, dataFont)));
                
                // Columna 2: Origen
                String origen = modelo.getValueAt(i, 2) != null ? modelo.getValueAt(i, 2).toString() : "";
                table.addCell(new PdfPCell(new Phrase(origen, dataFont)));
                
                // Columna 3: Destino
                String destino = modelo.getValueAt(i, 3) != null ? modelo.getValueAt(i, 3).toString() : "";
                table.addCell(new PdfPCell(new Phrase(destino, dataFont)));
                
                // Columna 4: Fecha Vuelo
                String fechaVuelo = modelo.getValueAt(i, 4) != null ? modelo.getValueAt(i, 4).toString() : "";
                table.addCell(new PdfPCell(new Phrase(fechaVuelo, dataFont)));
                
                // Columna 5: Cantidad
                String cantidad = modelo.getValueAt(i, 5) != null ? modelo.getValueAt(i, 5).toString() : "";
                PdfPCell cantidadCell = new PdfPCell(new Phrase(cantidad, dataFont));
                cantidadCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cantidadCell);
                
                // Columna 6: Fecha Reserva
                String fechaReserva = modelo.getValueAt(i, 6) != null ? modelo.getValueAt(i, 6).toString() : "";
                table.addCell(new PdfPCell(new Phrase(fechaReserva, dataFont)));
            }
            
            document.add(table);
            
            // Agregar resumen al final
            Paragraph resumen = new Paragraph("\nTotal de reservas: " + modelo.getRowCount(), normalFont);
            resumen.setSpacingBefore(20);
            document.add(resumen);
        }

        // Cerrar el documento
        document.close();
        
    } catch (DocumentException e) {
        e.printStackTrace();
        throw new RuntimeException("Error al generar el PDF: " + e.getMessage());
    }
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaReservas = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        btnGenerarPdf = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaReservas.setBackground(new java.awt.Color(204, 204, 255));
        tablaReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Reserva", "Vuelo", "Origen", "Destino", "Fecha Vuelo", "Cantidad", "Fecha Reserva"
            }
        ));
        tablaReservas.setShowGrid(true);
        jScrollPane1.setViewportView(tablaReservas);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnCancelar.setText("Cancelar Reserva");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnRegresar.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnGenerarPdf.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnGenerarPdf.setText("Generar PDF");
        btnGenerarPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPdfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnCancelar)
                .addGap(104, 104, 104)
                .addComponent(btnGenerarPdf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegresar)
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(btnCancelar)
                    .addComponent(btnGenerarPdf))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
 * Evento que se ejecuta al presionar el botón "Cancelar".
 * Permite cancelar la reserva seleccionada en la tabla después de confirmar.
 *
 * @param evt Evento generado por el clic en el botón
 */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
          // Verificar que se haya seleccionado alguna fila
           int fila = tablaReservas.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Selecciona una reserva.");
        return; // Salir si no hay selección
    }

    // Obtener el ID de la reserva de la columna 0 de la fila seleccionada
    int idReserva = (int) modelo.getValueAt(fila, 0);

    // Mostrar cuadro de diálogo para confirmar la cancelación
    int confirm = JOptionPane.showConfirmDialog(this, "¿Deseas cancelar la reserva?", "Confirmar", JOptionPane.YES_NO_OPTION);

    // Si el usuario confirma la cancelación
    if (confirm == JOptionPane.YES_OPTION) {
        ReservaDAO dao = new ReservaDAO();

        // Intentar cancelar la reserva en la base de datos
        if (dao.cancelarReserva(idReserva)) {
            JOptionPane.showMessageDialog(this, "Reserva cancelada.");
            cargarReservas(); // Refrescar la tabla para mostrar cambios
        } else {
            JOptionPane.showMessageDialog(this, "Error al cancelar.");
        }
    }
    }//GEN-LAST:event_btnCancelarActionPerformed
    /**
 * Evento que se ejecuta al presionar el botón "Regresar".
 * Cierra la ventana actual y abre la ventana principal.
 *
 * @param evt Evento generado por el clic en el botón
 */
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // Crear instancia de la ventana principal
    Ventanaprincipal principal = new Ventanaprincipal();

    // Mostrar la ventana principal
    principal.setVisible(true);

    // Cerrar la ventana actual para liberar recursos
    this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnGenerarPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPdfActionPerformed
       try {
        // Crear un JFileChooser para que el usuario elija dónde guardar
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar PDF de Reservas");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF", "pdf"));
        fileChooser.setSelectedFile(new File("mis_reservas.pdf"));
        
        int userSelection = fileChooser.showSaveDialog(this);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
            
            // Asegurar que termine en .pdf
            if (!filePath.toLowerCase().endsWith(".pdf")) {
                filePath += ".pdf";
            }
            
            // Llamar tu método
            crearPDF(filePath);
            
            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, 
                "PDF generado exitosamente en:\n" + filePath, 
                "PDF Creado", 
                JOptionPane.INFORMATION_MESSAGE);
                
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Error al generar el PDF: " + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
        
    }//GEN-LAST:event_btnGenerarPdfActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGenerarPdf;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaReservas;
    // End of variables declaration//GEN-END:variables
}
