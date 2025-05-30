package Views;

import Controlers.SistemaInmobiliaria;
import Models.Inmueble;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class SistemaInmobiliarioGUI extends JFrame {
    private SistemaInmobiliaria sistema = new SistemaInmobiliaria();
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel mainPanel;

    public SistemaInmobiliarioGUI() {
        setTitle("Sistema Inmobiliario");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main Panel
        mainPanel = new JPanel(new CardLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton registerButton = new JButton("Registrar Inmueble");
        JButton searchButton = new JButton("Buscar Inmueble");
        JButton listButton = new JButton("Listar Inmuebles");
        JButton ventaButton = new JButton("Venta"); // New button for "Venta" tab

        buttonPanel.add(registerButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(listButton);
        buttonPanel.add(ventaButton); // Add "Venta" button

        add(buttonPanel, BorderLayout.NORTH);

        // Table for displaying properties
        tableModel = new DefaultTableModel(new String[]{"ID", "Tipo", "Estado", "Área", "Precio"}, 0);
        table = new JTable(tableModel);

        // Panels
        JPanel registerPanel = createRegisterPanel();
        JPanel searchPanel = createSearchPanel();
        JPanel listPanel = createListPanel();
        JPanel ventaPanel = createVentaPanel(); // Create "Venta" panel

        mainPanel.add(registerPanel, "Register");
        mainPanel.add(searchPanel, "Search");
        mainPanel.add(listPanel, "List");
        mainPanel.add(ventaPanel, "Venta"); // Add "Venta" panel to mainPanel

        // Button Actions
        registerButton.addActionListener(e -> showPanel("Register"));
        searchButton.addActionListener(e -> showPanel("Search"));
        listButton.addActionListener(e -> showPanel("List"));
        ventaButton.addActionListener(e -> showPanel("Venta")); // Action to show "Venta" panel

        setVisible(true);
    }

    private JPanel createVentaPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table Model with Checkbox Column
        DefaultTableModel ventaTableModel = new DefaultTableModel(new Object[]{"ID", "Tipo", "Estado", "Área", "Precio", "Vendido"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class; // Checkbox for "Vendido"
            }
        };
        JTable ventaTable = new JTable(ventaTableModel);
        JScrollPane tableScrollPane = new JScrollPane(ventaTable);

        // Lower Section: Sales Total + Update Button
        JPanel lowerPanel = new JPanel(new FlowLayout());
        JLabel ventasLabel = new JLabel("Total Ventas: ");
        JLabel ventasTotalLabel = new JLabel("0.0");

        JButton actualizarButton = new JButton("Actualizar ventas");
        JButton recargarButton = new JButton("Recargar propiedades"); // Botón para refrescar la lista

        lowerPanel.add(ventasLabel);
        lowerPanel.add(ventasTotalLabel);
        lowerPanel.add(actualizarButton);
        lowerPanel.add(recargarButton);

        // Add sections to main panel
        panel.add(tableScrollPane, BorderLayout.CENTER);
        panel.add(lowerPanel, BorderLayout.SOUTH);

        // Método para cargar la lista actual de inmuebles en la tabla
        Runnable cargarPropiedades = () -> {
            ventaTableModel.setRowCount(0);
            List<Inmueble> properties = sistema.listarInmuebles();
            for (Inmueble inmueble : properties) {
                ventaTableModel.addRow(new Object[]{
                        inmueble.getId(),
                        inmueble.getTipo(),
                        inmueble.getEstado(),
                        inmueble.getArea(),
                        inmueble.getPrecio(),
                        "Vendido".equalsIgnoreCase(inmueble.getEstado()) // Si ya está vendido, marcar checkbox
                });
            }
        };

        // Primera carga al crear el panel
        cargarPropiedades.run();

        // Acción del botón "Actualizar ventas"
        actualizarButton.addActionListener(e -> {
            double totalVentas = 0.0;
            for (int i = 0; i < ventaTableModel.getRowCount(); i++) {
                boolean vendido = (boolean) ventaTableModel.getValueAt(i, 5); // Check "Vendido" column
                if (vendido) {
                    totalVentas += (double) ventaTableModel.getValueAt(i, 4); // Add price to total sales
                    ventaTableModel.setValueAt("Vendido", i, 2); // Update state to "Vendido"
                }
            }
            ventasTotalLabel.setText(String.valueOf(totalVentas)); // Update sales total
        });

        // Acción del botón "Recargar propiedades"
        recargarButton.addActionListener(e -> cargarPropiedades.run());

        return panel;
    }


    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(0, 2));

        JTextField idField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField areaField = new JTextField();
        JComboBox<String> stateCombo = new JComboBox<>(new String[]{"Disponible", "Vendido"});
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Casa", "Departamento", "Terreno"});

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Precio:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Área:"));
        inputPanel.add(areaField);
        inputPanel.add(new JLabel("Estado:"));
        inputPanel.add(stateCombo);
        inputPanel.add(new JLabel("Tipo:"));
        inputPanel.add(typeCombo);

        JPanel dynamicPanel = new JPanel(new GridLayout(0, 2));
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(dynamicPanel, BorderLayout.CENTER);

        typeCombo.addActionListener(e -> {
            dynamicPanel.removeAll();
            String selectedType = (String) typeCombo.getSelectedItem();
            switch (selectedType) {
                case "Casa" -> {
                    JTextField metrosConstruccionField = new JTextField();
                    JTextField habitacionesField = new JTextField();
                    JTextField pisosField = new JTextField();

                    dynamicPanel.add(new JLabel("Metros de Construcción:"));
                    dynamicPanel.add(metrosConstruccionField);
                    dynamicPanel.add(new JLabel("Habitaciones:"));
                    dynamicPanel.add(habitacionesField);
                    dynamicPanel.add(new JLabel("Pisos:"));
                    dynamicPanel.add(pisosField);

                    dynamicPanel.putClientProperty("fields", new JTextField[]{metrosConstruccionField, habitacionesField, pisosField});
                }
                case "Departamento" -> {
                    JTextField habitacionesField = new JTextField();
                    JTextField alicuotaField = new JTextField();
                    JTextField amenidadesField = new JTextField();

                    dynamicPanel.add(new JLabel("Habitaciones:"));
                    dynamicPanel.add(habitacionesField);
                    dynamicPanel.add(new JLabel("Alicuota:"));
                    dynamicPanel.add(alicuotaField);
                    dynamicPanel.add(new JLabel("Amenidades:"));
                    dynamicPanel.add(amenidadesField);

                    dynamicPanel.putClientProperty("fields", new JTextField[]{habitacionesField, alicuotaField, amenidadesField});
                }
                case "Terreno" -> {
                    JTextField ubicacionField = new JTextField();
                    JTextField cerramientoField = new JTextField();
                    JTextField serviciosBasicosField = new JTextField();

                    dynamicPanel.add(new JLabel("Ubicación:"));
                    dynamicPanel.add(ubicacionField);
                    dynamicPanel.add(new JLabel("Cerramiento:"));
                    dynamicPanel.add(cerramientoField);
                    dynamicPanel.add(new JLabel("Servicios Básicos:"));
                    dynamicPanel.add(serviciosBasicosField);

                    dynamicPanel.putClientProperty("fields", new JTextField[]{ubicacionField, cerramientoField, serviciosBasicosField});
                }
            }
            dynamicPanel.revalidate();
            dynamicPanel.repaint();
        });

        JButton saveButton = new JButton("Guardar");
        panel.add(saveButton, BorderLayout.SOUTH);

        saveButton.addActionListener(event -> {
            String id = idField.getText();
            double price = Double.parseDouble(priceField.getText());
            double area = Double.parseDouble(areaField.getText());
            String state = (String) stateCombo.getSelectedItem();
            String type = (String) typeCombo.getSelectedItem();

            JTextField[] fields = (JTextField[]) dynamicPanel.getClientProperty("fields");
            switch (type) {
                case "Casa" -> {
                    double metrosConstruccion = Double.parseDouble(fields[0].getText());
                    int habitaciones = Integer.parseInt(fields[1].getText());
                    int pisos = Integer.parseInt(fields[2].getText());
                    sistema.crearInmueble(id, price, area, state, type, metrosConstruccion, habitaciones, pisos);
                }
                case "Departamento" -> {
                    int habitaciones = Integer.parseInt(fields[0].getText());
                    double alicuota = Double.parseDouble(fields[1].getText());
                    String amenidades = fields[2].getText();
                    sistema.crearInmueble(id, price, area, state, type, habitaciones, alicuota, amenidades);
                }
                case "Terreno" -> {
                    String ubicacion = fields[0].getText();
                    String cerramiento = fields[1].getText();
                    String serviciosBasicos = fields[2].getText();
                    sistema.crearInmueble(id, price, area, state, type, ubicacion, cerramiento, serviciosBasicos);
                }
            }
            idField.setText("");
            priceField.setText("");
            areaField.setText("");
            stateCombo.setSelectedIndex(0);
            typeCombo.setSelectedIndex(0);
            dynamicPanel.removeAll();
            dynamicPanel.revalidate();
            dynamicPanel.repaint();
        });

        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Panel de búsqueda
        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        JComboBox<String> searchTypeCombo = new JComboBox<>(new String[]{"Tipo", "Estado"});
        JComboBox<String> valueCombo = new JComboBox<>();

        inputPanel.add(new JLabel("Buscar por:"));
        inputPanel.add(searchTypeCombo);
        inputPanel.add(new JLabel("Valor:"));
        inputPanel.add(valueCombo);

        JButton searchButton = new JButton("Buscar");
        inputPanel.add(searchButton);

        // Crear una tabla LOCAL para mostrar resultados de búsqueda
        DefaultTableModel searchTableModel = new DefaultTableModel(new String[]{"ID", "Tipo", "Estado", "Área"}, 0);
        JTable searchTable = new JTable(searchTableModel);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(searchTable), BorderLayout.CENTER);

        // Actualizar opciones de valueCombo según lo que se seleccione
        searchTypeCombo.addActionListener(e -> {
            valueCombo.removeAllItems();
            String selectedType = (String) searchTypeCombo.getSelectedItem();
            if ("Tipo".equals(selectedType)) {
                valueCombo.addItem("Casa");
                valueCombo.addItem("Departamento");
                valueCombo.addItem("Terreno");
            } else if ("Estado".equals(selectedType)) {
                valueCombo.addItem("Disponible");
                valueCombo.addItem("Vendido");
            }
        });

        // Acción del botón "Buscar"
        searchButton.addActionListener(event -> {
            String searchType = (String) searchTypeCombo.getSelectedItem();
            String value = (String) valueCombo.getSelectedItem();

            // Limpiar resultados anteriores
            searchTableModel.setRowCount(0);

            List<Inmueble> results = searchType.equals("Tipo") ?
                    sistema.buscarInmueblePorTipo(value) :
                    sistema.buscarInmueblePorEstado(value);

            // Mostrar los resultados en la tabla local
            for (Inmueble inmueble : results) {
                searchTableModel.addRow(new Object[]{
                        inmueble.getId(),
                        inmueble.getTipo(),
                        inmueble.getEstado(),
                        inmueble.getArea()
                });
            }
        });

        return panel;
    }



    private JPanel createListPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton refreshButton = new JButton("Actualizar");
        panel.add(refreshButton, BorderLayout.NORTH);

        refreshButton.addActionListener(e -> listProperties());

        return panel;
    }

    private void listProperties() {
        tableModel.setRowCount(0); // Clear table
        List<Inmueble> properties = sistema.listarInmuebles();
        for (Inmueble inmueble : properties) {
            tableModel.addRow(new Object[]{inmueble.getId(), inmueble.getTipo(), inmueble.getEstado(), inmueble.getArea(), inmueble.getPrecio()});
        }
    }

    private void showPanel(String panelName) {
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel, panelName);
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    public static void main(String[] args) {
        new SistemaInmobiliarioGUI();
    }
}