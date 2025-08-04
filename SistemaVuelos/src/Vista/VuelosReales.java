/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import POJO.OpenSkyIntegration;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 *
 * @author Emesis
 * Ventana dedicada para mostrar y gestionar vuelos reales desde OpenSky Network
 */


public class VuelosReales extends javax.swing.JFrame {

    // Componentes de la interfaz gráfica
    private JTable tablaVuelosReales;          // Tabla donde se mostrarán los vuelos
    private DefaultTableModel modeloTabla;     // Modelo de datos para la tabla
    private JButton btnSincronizar;             // Botón para sincronizar datos con la API
    private JButton btnLimpiar;                 // Botón para limpiar la tabla
    private JButton btnExportarPDF;             // Botón para exportar datos a PDF
    private JButton btnCerrar;                  // Botón para cerrar la ventana
    private JLabel lblEstado;                   // Etiqueta para mostrar el estado de la sincronización
    private JLabel lblTotalVuelos;              // Etiqueta que muestra total de vuelos cargados
    private JProgressBar progressBar;           // Barra de progreso para indicar carga
    private JScrollPane scrollPane;             // Scroll para la tabla

    // Constructor que inicializa y configura la ventana
    public VuelosReales() {
        inicializarComponentes(); // Crea los componentes gráficos
        configurarVentana();      // Configura la ventana principal
        configurarTabla();        // Configura la tabla y sus columnas
        configurarEventos();      // Asocia eventos a botones
    }

    // Método para crear y configurar todos los componentes visuales
    private void inicializarComponentes() {
        // Definir columnas para la tabla
        String[] columnas = {"Callsign", "Origen", "Destino", "Altitud (m)", "Velocidad (km/h)", "Última Actualización"};
        
        // Crear modelo de tabla con las columnas, filas iniciales 0
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evitar edición directa en la tabla
            }
        };

        // Crear tabla usando el modelo
        tablaVuelosReales = new JTable(modeloTabla);
        // Añadir la tabla dentro de un JScrollPane para permitir desplazamiento
        scrollPane = new JScrollPane(tablaVuelosReales);

        // Crear los botones con texto e íconos Unicode para mejor UI
        btnSincronizar = new JButton(" Sincronizar Vuelos Reales");
        btnLimpiar = new JButton("️ Limpiar Tabla");
        btnExportarPDF = new JButton(" Exportar a PDF");
        btnCerrar = new JButton(" Cerrar");

        // Crear etiquetas para estado y conteo de vuelos
        lblEstado = new JLabel("Listo para sincronizar vuelos reales");
        lblTotalVuelos = new JLabel("Total de vuelos: 0");

        // Crear barra de progreso y configurarla inicialmente invisible
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);        // Mostrar texto dentro de la barra
        progressBar.setString("Esperando...");     // Texto inicial
        progressBar.setVisible(false);              // Oculta hasta que se inicie sincronización
    }

    // Configura la ventana principal: tamaño, layout y agrega componentes
    private void configurarVentana() {
        setTitle("Vuelos Reales en Tiempo Real - OpenSky Network");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana
        setSize(900, 600);                                  // Tamaño ventana
        setLocationRelativeTo(null);                        // Centrar en pantalla
        setLayout(new BorderLayout());                      // Layout principal

        // Panel superior con botones alineados a la izquierda
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(btnSincronizar);
        panelSuperior.add(btnLimpiar);
        panelSuperior.add(btnExportarPDF);
        panelSuperior.add(btnCerrar);

        // Panel inferior para mostrar estado y progreso
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBorder(BorderFactory.createTitledBorder("Estado"));

        // Panel para etiquetas de estado
        JPanel panelEstado = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEstado.add(lblEstado);
        panelEstado.add(Box.createHorizontalStrut(20)); // Espacio horizontal entre etiquetas
        panelEstado.add(lblTotalVuelos);

        panelInferior.add(panelEstado, BorderLayout.NORTH);
        panelInferior.add(progressBar, BorderLayout.SOUTH);

        // Panel central que incluye información descriptiva y la tabla
        JPanel panelCentral = new JPanel(new BorderLayout());

        // Etiqueta con información HTML sobre la fuente de datos
        JLabel lblInfo = new JLabel("<html><center>" +
            "<b>Vuelos Reales desde OpenSky Network</b><br>" +
            "Datos actualizados en tiempo real de vuelos comerciales mundiales<br>" +
            "<small>Haz clic en 'Sincronizar' para obtener los últimos datos</small>" +
            "</center></html>");
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        lblInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interno

        panelCentral.add(lblInfo, BorderLayout.NORTH);    // Info arriba
        panelCentral.add(scrollPane, BorderLayout.CENTER); // Tabla en centro

        // Añadir los paneles a la ventana principal en sus posiciones
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    // Configuración específica de la tabla para mejorar usabilidad y apariencia
    private void configurarTabla() {
        tablaVuelosReales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Solo una fila seleccionada
        tablaVuelosReales.getTableHeader().setReorderingAllowed(false);          // No permitir reordenar columnas
        tablaVuelosReales.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);     // Ajustar ancho automático

        // Configurar ancho preferido para cada columna para mejor visualización
        tablaVuelosReales.getColumnModel().getColumn(0).setPreferredWidth(100); // Callsign
        tablaVuelosReales.getColumnModel().getColumn(1).setPreferredWidth(150); // Origen
        tablaVuelosReales.getColumnModel().getColumn(2).setPreferredWidth(150); // Destino
        tablaVuelosReales.getColumnModel().getColumn(3).setPreferredWidth(100); // Altitud
        tablaVuelosReales.getColumnModel().getColumn(4).setPreferredWidth(120); // Velocidad
        tablaVuelosReales.getColumnModel().getColumn(5).setPreferredWidth(180); // Fecha

        // Centrar contenido numérico para Altitud y Velocidad
        tablaVuelosReales.getColumnModel().getColumn(3).setCellRenderer(
            new javax.swing.table.DefaultTableCellRenderer() {{
                setHorizontalAlignment(SwingConstants.CENTER);
            }}
        );
        tablaVuelosReales.getColumnModel().getColumn(4).setCellRenderer(
            new javax.swing.table.DefaultTableCellRenderer() {{
                setHorizontalAlignment(SwingConstants.CENTER);
            }}
        );
    }

    // Asocia los eventos (listeners) a los botones
    private void configurarEventos() {
        // Evento para sincronizar vuelos con la API
        btnSincronizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sincronizarVuelos();
            }
        });

        // Evento para limpiar la tabla
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarTabla();
            }
        });

        // Evento para exportar la tabla a PDF
        btnExportarPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarAPDF();
            }
        });

        // Evento para cerrar la ventana
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    // Método que obtiene datos desde OpenSky, actualiza la tabla y estado
    private void sincronizarVuelos() {
        // Deshabilitar botón para evitar múltiples clics mientras sincroniza
        btnSincronizar.setEnabled(false);
        btnSincronizar.setText("Sincronizando...");
        progressBar.setVisible(true);            // Mostrar barra progreso
        progressBar.setIndeterminate(true);     // Barra con animación indeterminada
        progressBar.setString("Obteniendo vuelos desde OpenSky Network...");
        lblEstado.setText("Consultando API de OpenSky Network...");

        // Llamada a la clase que hace la consulta a OpenSky (asíncrona)
        OpenSkyIntegration.obtenerVuelosEnTiempoReal(new OpenSkyIntegration.VuelosCallback() {
            @Override
            public void onVuelosObtenidos(java.util.List<OpenSkyIntegration.VueloReal> vuelos) {
                SwingUtilities.invokeLater(() -> {
                    modeloTabla.setRowCount(0); // Limpiar tabla

                    // Recorrer vuelos y agregar fila para cada uno
                    for (OpenSkyIntegration.VueloReal vuelo : vuelos) {
                        Object[] fila = {
                            vuelo.getCallsign(),
                            vuelo.getOrigen(),
                            vuelo.getDestino(),
                            String.format("%.0f", vuelo.getAltitud()),
                            String.format("%.1f", vuelo.getVelocidad() * 3.6), // m/s a km/h
                            vuelo.getFechaActualizacion()
                        };
                        modeloTabla.addRow(fila);
                    }

                    // Actualizar etiquetas y estado con éxito
                    actualizarEstado(vuelos.size(), true);
                });
            }

            @Override
            public void onError(String error) {
                SwingUtilities.invokeLater(() -> {
                    lblEstado.setText(" Error: " + error);
                    JOptionPane.showMessageDialog(VuelosReales.this,
                        "Error al sincronizar vuelos:\n" + error,
                        "Error de Sincronización",
                        JOptionPane.ERROR_MESSAGE);

                    actualizarEstado(0, false); // Reactivar botón y ocultar barra
                });
            }
        });
    }

    // Actualiza los estados visuales luego de sincronizar o error
    private void actualizarEstado(int totalVuelos, boolean exito) {
        btnSincronizar.setEnabled(true);                     // Reactivar botón
        btnSincronizar.setText(" Sincronizar Vuelos Reales");
        progressBar.setVisible(false);                        // Ocultar barra de progreso
        progressBar.setIndeterminate(false);

        if (exito) {
            lblEstado.setText(" Sincronización exitosa - " +
                new SimpleDateFormat("HH:mm:ss").format(new Date()));  // Hora de actualización
            lblTotalVuelos.setText("Total de vuelos: " + totalVuelos);
        } else {
            // Si fallo, mostrar cantidad que haya (posiblemente 0)
            lblTotalVuelos.setText("Total de vuelos: " + modeloTabla.getRowCount());
        }
    }

    // Limpia la tabla previo confirmación del usuario
    private void limpiarTabla() {
        int respuesta = JOptionPane.showConfirmDialog(this,
            "¿Estás seguro de que deseas limpiar la tabla de vuelos reales?",
            "Confirmar Limpieza",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            modeloTabla.setRowCount(0);         // Quitar todas las filas
            lblTotalVuelos.setText("Total de vuelos: 0");
            lblEstado.setText("Tabla limpiada - Listo para sincronizar");
        }
    }

    // Permite exportar el contenido actual de la tabla a un archivo PDF
    private void exportarAPDF() {
        if (modeloTabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                "No hay vuelos reales para exportar.\nPrimero sincroniza algunos vuelos.",
                "Sin Datos",
                JOptionPane.WARNING_MESSAGE);
            return; // No continuar si no hay datos
        }

        try {
            // Abrir diálogo para seleccionar ruta y nombre del archivo PDF
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar PDF de Vuelos Reales");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos PDF", "pdf"));
            fileChooser.setSelectedFile(new java.io.File("vuelos_reales_" +
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".pdf"));

            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();

                // Añadir extensión .pdf si no existe
                if (!filePath.toLowerCase().endsWith(".pdf")) {
                    filePath += ".pdf";
                }

                // Crear el PDF con el contenido de la tabla
                crearPDFVuelosReales(filePath);

                JOptionPane.showMessageDialog(this,
                    "PDF de vuelos reales generado exitosamente en:\n" + filePath,
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
    }

    // Método para generar el PDF con los datos de vuelos reales
    private void crearPDFVuelosReales(String filePath) throws Exception {
        try {
            // Crear documento PDF usando iText
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            com.itextpdf.text.pdf.PdfWriter.getInstance(document, new java.io.FileOutputStream(filePath));
            document.open();

            // Definir estilos de fuente para diferentes textos
            com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 18, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font normalFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 10);
            com.itextpdf.text.Font headerFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 14, com.itextpdf.text.Font.BOLD);

            // Agregar título centrado
            com.itextpdf.text.Paragraph titulo = new com.itextpdf.text.Paragraph("VUELOS REALES EN TIEMPO REAL", titleFont);
            titulo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            titulo.setSpacingAfter(10);
            document.add(titulo);

            // Subtítulo con fuente normal
            com.itextpdf.text.Paragraph subtitulo = new com.itextpdf.text.Paragraph("Datos obtenidos desde OpenSky Network", normalFont);
            subtitulo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            subtitulo.setSpacingAfter(20);
            document.add(subtitulo);

            // Fecha de generación alineada a la derecha
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            com.itextpdf.text.Paragraph fecha = new com.itextpdf.text.Paragraph("Fecha de generación: " + dateFormat.format(new java.util.Date()), normalFont);
            fecha.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            fecha.setSpacingAfter(20);
            document.add(fecha);

            // Si no hay vuelos, mostrar mensaje central
            if (modeloTabla.getRowCount() == 0) {
                com.itextpdf.text.Paragraph sinVuelos = new com.itextpdf.text.Paragraph("No hay vuelos reales disponibles.", normalFont);
                sinVuelos.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                document.add(sinVuelos);
            } else {
                // Título para la tabla
                com.itextpdf.text.Paragraph encabezado = new com.itextpdf.text.Paragraph("LISTADO DE VUELOS EN TIEMPO REAL", headerFont);
                encabezado.setSpacingAfter(15);
                document.add(encabezado);

                // Crear tabla con 6 columnas para datos
                com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(6);
                table.setWidthPercentage(100);      // Ancho al 100% de la página
                table.setSpacingBefore(10);

                // Anchos relativos de cada columna
                float[] columnWidths = {1.5f, 2f, 2f, 1.2f, 1.5f, 2.5f};
                table.setWidths(columnWidths);

                // Añadir encabezados con estilo
                String[] headers = {"Callsign", "Origen", "Destino", "Altitud (m)", "Velocidad (km/h)", "Última Actualización"};
                com.itextpdf.text.Font headerTableFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 9, com.itextpdf.text.Font.BOLD);

                for (String header : headers) {
                    com.itextpdf.text.pdf.PdfPCell cell = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(header, headerTableFont));
                    cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    cell.setPadding(8);
                    cell.setBackgroundColor(new com.itextpdf.text.BaseColor(230, 230, 230));
                    table.addCell(cell);
                }

                // Agregar filas con datos de la tabla Swing
                com.itextpdf.text.Font dataFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 8);

                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                    // Callsign
                    String callsign = modeloTabla.getValueAt(i, 0) != null ? modeloTabla.getValueAt(i, 0).toString() : "";
                    table.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(callsign, dataFont)));

                    // Origen
                    String origen = modeloTabla.getValueAt(i, 1) != null ? modeloTabla.getValueAt(i, 1).toString() : "";
                    table.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(origen, dataFont)));

                    // Destino
                    String destino = modeloTabla.getValueAt(i, 2) != null ? modeloTabla.getValueAt(i, 2).toString() : "";
                    table.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(destino, dataFont)));

                    // Altitud centrada
                    String altitud = modeloTabla.getValueAt(i, 3) != null ? modeloTabla.getValueAt(i, 3).toString() : "";
                    com.itextpdf.text.pdf.PdfPCell altitudCell = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(altitud, dataFont));
                    altitudCell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    table.addCell(altitudCell);

                    // Velocidad centrada
                    String velocidad = modeloTabla.getValueAt(i, 4) != null ? modeloTabla.getValueAt(i, 4).toString() : "";
                    com.itextpdf.text.pdf.PdfPCell velocidadCell = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(velocidad, dataFont));
                    velocidadCell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    table.addCell(velocidadCell);

                    // Fecha actualización
                    String fechaActualizacion = modeloTabla.getValueAt(i, 5) != null ? modeloTabla.getValueAt(i, 5).toString() : "";
                    table.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(fechaActualizacion, dataFont)));
                }

                document.add(table);

                // Espacio después de la tabla
                document.add(new com.itextpdf.text.Paragraph("\n"));

                // Resumen con título
                com.itextpdf.text.Paragraph resumen = new com.itextpdf.text.Paragraph("RESUMEN DE DATOS", headerFont);
                resumen.setSpacingBefore(20);
                resumen.setSpacingAfter(10);
                document.add(resumen);

                // Información adicional y notas al pie
                com.itextpdf.text.Paragraph info = new com.itextpdf.text.Paragraph(
                    "• Total de vuelos mostrados: " + modeloTabla.getRowCount() + "\n" +
                    "• Fuente de datos: OpenSky Network (https://opensky-network.org)\n" +
                    "• Los datos se actualizan en tiempo real\n" +
                    "• Las coordenadas y velocidades son aproximadas\n" +
                    "• Solo se muestran vuelos con altitud superior a 1000 metros",
                    normalFont);
                info.setSpacingAfter(10);
                document.add(info);

                // Nota legal con fuente en cursiva y justificada
                com.itextpdf.text.Paragraph nota = new com.itextpdf.text.Paragraph(
                    "NOTA: Los datos de vuelos son proporcionados por OpenSky Network y son solo para fines informativos. " +
                    "La precisión y disponibilidad de los datos pueden variar.",
                    new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 8, com.itextpdf.text.Font.ITALIC));
                nota.setAlignment(com.itextpdf.text.Element.ALIGN_JUSTIFIED);
                nota.setSpacingBefore(20);
                document.add(nota);
            }

            // Cerrar documento para finalizar PDF
            document.close();

        } catch (Exception e) {
            // Propagar excepción si falla la generación del PDF
            throw new Exception("Error al generar el PDF de vuelos reales: " + e.getMessage());
        }
    }

    // Método para mostrar esta ventana desde otras clases
    public static void mostrarVentana() {
        SwingUtilities.invokeLater(() -> {
            try {
                // Intentar usar el look and feel del sistema operativo
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Si falla, usar look and feel por defecto
            }

            // Crear y mostrar la ventana
            new VuelosReales().setVisible(true);
        });
    }
}
