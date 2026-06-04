// ==============================================================================
// Universidad Distrital Francisco José de Caldas
// Autores: Cristian Velosa (20252020066) y Daniel David Granados Rivera (20252020135)
// Fecha de Creación: 28/05/2026 | Modificación: 04/06/2026
// Descripción: Interfaz Gráfica Principal con Separación de Módulos.
//              Control de adición ubicado exclusivamente en la pestaña Dispositivos.
// ==============================================================================
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

/**
 * Interfaz Gráfica Principal para el Controlador de Energía Doméstica.
 */
public class ControladorEnergiaGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private JPanel panelContenedor;
    private CardLayout cardLayout;
    
    // Contenedor global de las tarjetas del Dashboard
    private JPanel panelTarjetas;
    
    // Paleta de colores (Psicología del Color)
    private final Color COLOR_MENU = new Color(30, 58, 138);       // Azul: Tecnología
    private final Color COLOR_FONDO = new Color(243, 244, 246);    // Gris Claro: Minimalismo
    private final Color COLOR_AHORRO = new Color(16, 185, 129);    // Verde: Ecología
    private final Color COLOR_ALERTA = new Color(239, 68, 68);     // Rojo: Alertas/Apagado
    private final Color COLOR_TEXTO_BLANCO = Color.WHITE;
    private final Color COLOR_TEXTO_OSCURO = new Color(31, 41, 55);

    public ControladorEnergiaGUI() {
        setTitle("Controlador de Energía Doméstica - SmartHome Manager");
        setSize(1050, 650); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        getContentPane().setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        // Inicializar el contenedor de tarjetas antes de construir las pantallas
        panelTarjetas = new JPanel(new GridLayout(0, 3, 20, 20));
        panelTarjetas.setBackground(COLOR_FONDO);

        inicializarMenuLateral();
        construirVistas();
    }

    private void inicializarMenuLateral() {
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(0, 128, 128));
        panelMenu.setPreferredSize(new Dimension(240, getHeight()));
        panelMenu.setLayout(new GridLayout(6, 1, 10, 10));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        JLabel lblTitulo = new JLabel("Smart Home", SwingConstants.CENTER);
        lblTitulo.setForeground(COLOR_TEXTO_BLANCO);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
        panelMenu.add(lblTitulo);

        JButton btnDashboard = crearBotonMenu("Dashboard");
        JButton btnDispositivos = crearBotonMenu("Dispositivos");
        JButton btnReglas = crearBotonMenu("Reglas y Horarios");
        JButton btnSimulador = crearBotonMenu("Simulador de Ahorro");

        // Navegación fluida entre pantallas
        btnDashboard.addActionListener(e -> cardLayout.show(panelContenedor, "VISTA_DASHBOARD"));
        btnDispositivos.addActionListener(e -> cardLayout.show(panelContenedor, "VISTA_DISPOSITIVOS"));
        btnReglas.addActionListener(e -> cardLayout.show(panelContenedor, "VISTA_REGLAS"));
        btnSimulador.addActionListener(e -> cardLayout.show(panelContenedor, "VISTA_SIMULADOR"));

        panelMenu.add(btnDashboard);
        panelMenu.add(btnDispositivos);
        panelMenu.add(btnReglas);
        panelMenu.add(btnSimulador);

        getContentPane().add(panelMenu, BorderLayout.WEST);
    }

    private void construirVistas() {
        JPanel vistaDashboard = crearPanelDashboard();
        JPanel vistaDispositivos = crearPanelDispositivos(); // Vinculamos la nueva pantalla de control
        JPanel vistaReglas = crearPanelPlaceholder("Sección Reglas y Horarios - Próximamente");
        JPanel vistaSimulador = crearPanelPlaceholder("Sección Simulador de Ahorro - Próximamente");

        panelContenedor.add(vistaDashboard, "VISTA_DASHBOARD");
        panelContenedor.add(vistaDispositivos, "VISTA_DISPOSITIVOS");
        panelContenedor.add(vistaReglas, "VISTA_REGLAS");
        panelContenedor.add(vistaSimulador, "VISTA_SIMULADOR");

        cardLayout.show(panelContenedor, "VISTA_DASHBOARD");
        getContentPane().add(panelContenedor, BorderLayout.CENTER);
    }

    /**
     * 1. PANTALLA DASHBOARD: Exclusiva para monitorear el consumo actual
     */
    private JPanel crearPanelDashboard() {
        JPanel panelDashboard = new JPanel();
        panelDashboard.setBackground(COLOR_FONDO);
        panelDashboard.setLayout(new BorderLayout(20, 20));
        panelDashboard.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Cabecera limpia sin controles de agregar
        JLabel lblBienvenida = new JLabel("Dashboard: Resumen de Consumo Actual");
        lblBienvenida.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblBienvenida.setForeground(COLOR_TEXTO_OSCURO);
        panelDashboard.add(lblBienvenida, BorderLayout.NORTH);

        // Rejilla de tarjetas (vinculada al JScrollPane)
        JScrollPane scrollTarjetas = new JScrollPane(panelTarjetas);
        scrollTarjetas.setBorder(null);
        scrollTarjetas.setBackground(COLOR_FONDO);
        scrollTarjetas.getViewport().setBackground(COLOR_FONDO);
        panelDashboard.add(scrollTarjetas, BorderLayout.CENTER);

        // Botón de acción global en la base
        JButton btnApagarTodo = new JButton("Apagar Todos los Dispositivos (Modo Ahorro Crítico)");
        btnApagarTodo.setBackground(COLOR_ALERTA);
        btnApagarTodo.setForeground(COLOR_TEXTO_BLANCO);
        btnApagarTodo.setFont(new Font("Arial", Font.BOLD, 18));
        btnApagarTodo.setFocusPainted(false);
        btnApagarTodo.setPreferredSize(new Dimension(0, 50));
        
        btnApagarTodo.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, 
                "Regla de seguridad activada: Todos los dispositivos activos han sido apagados temporalmente.", 
                "Modo Ahorro Activado", 
                JOptionPane.INFORMATION_MESSAGE);
        });

        panelDashboard.add(btnApagarTodo, BorderLayout.SOUTH);

        return panelDashboard;
    }

    /**
     * 2. PANTALLA DISPOSITIVOS: Donde aparece la sección para seleccionar e integrar elementos
     */
    private JPanel crearPanelDispositivos() {
        JPanel panelDispositivos = new JPanel();
        panelDispositivos.setBackground(COLOR_FONDO);
        panelDispositivos.setLayout(new BorderLayout(20, 20));
        panelDispositivos.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Cabecera de la sección de administración
        JLabel lblTitulo = new JLabel("Administración y Registro de Dispositivos");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitulo.setForeground(COLOR_TEXTO_OSCURO);
        panelDispositivos.add(lblTitulo, BorderLayout.NORTH);

        // Panel central: Contenedor estético para la barra de selección
        JPanel panelCentro = new JPanel(new GridBagLayout());
        panelCentro.setBackground(COLOR_FONDO);

        // Subpanel contenedor estilizado como una tarjeta de configuración
        JPanel panelControlAgregar = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelControlAgregar.setBackground(Color.WHITE);
        panelControlAgregar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));

        JLabel lblSeleccionar = new JLabel("Seleccione el dispositivo a vincular:");
        lblSeleccionar.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSeleccionar.setForeground(COLOR_TEXTO_OSCURO);
        
        String[] opcionesDispositivos = {
            "1. Bombillo", 
            "2. Televisor", 
            "3. Nevera", 
            "4. Calefacción", 
            "5. Dispositivo 24/7"
        };
        JComboBox<String> comboDispositivos = new JComboBox<>(opcionesDispositivos);
        comboDispositivos.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboDispositivos.setPreferredSize(new Dimension(180, 30));
        comboDispositivos.setBackground(Color.WHITE);

        JButton btnAgregar = new JButton("Vincular al Sistema");
        btnAgregar.setBackground(COLOR_AHORRO);
        btnAgregar.setForeground(COLOR_TEXTO_BLANCO);
        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAgregar.setFocusPainted(false);
        btnAgregar.setPreferredSize(new Dimension(180, 30));

        panelControlAgregar.add(lblSeleccionar);
        panelControlAgregar.add(comboDispositivos);
        panelControlAgregar.add(btnAgregar);
        
        panelCentro.add(panelControlAgregar);
        panelDispositivos.add(panelCentro, BorderLayout.CENTER);

        // --- LÓGICA DE AGREGACIÓN DESDE ESTA PANTALLA ---
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) comboDispositivos.getSelectedItem();
                String nombreDispositivo = seleccion.substring(3); // Quita el índice numérico
                String consumoText = "";
                Color colorBorde = COLOR_MENU;

                switch (seleccion) {
                    case "1. Bombillo":
                        consumoText = "Consumo: 0.05 kWh";
                        colorBorde = COLOR_AHORRO;
                        break;
                    case "2. Televisor":
                        consumoText = "Consumo: 0.25 kWh";
                        colorBorde = COLOR_MENU;
                        break;
                    case "3. Nevera":
                        consumoText = "Consumo: 1.20 kWh";
                        colorBorde = COLOR_ALERTA;
                        break;
                    case "4. Calefacción":
                        consumoText = "Consumo: 2.00 kWh";
                        colorBorde = COLOR_ALERTA;
                        break;
                    case "5. Dispositivo 24/7":
                        consumoText = "Consumo: 0.40 kWh";
                        colorBorde = new Color(142, 68, 173); // Morado para fijos
                        break;
                }

                // Generar tarjeta física e inyectarla al panel global del Dashboard
                JPanel nuevaTarjeta = crearTarjetaDispositivo(nombreDispositivo, consumoText, colorBorde, true);
                panelTarjetas.add(nuevaTarjeta);
                
                panelTarjetas.revalidate();
                panelTarjetas.repaint();

                // Mensaje de éxito al usuario
                JOptionPane.showMessageDialog(panelDispositivos, 
                        nombreDispositivo + " agregado con éxito al sistema.", 
                        "Dispositivo Vinculado", JOptionPane.INFORMATION_MESSAGE);

                // Enfoque inteligente: Llevar al usuario directamente al Dashboard para ver su nueva tarjeta
                cardLayout.show(panelContenedor, "VISTA_DASHBOARD");
            }
        });

        return panelDispositivos;
    }

    private JPanel crearTarjetaDispositivo(String nombre, String estado, Color colorBorde, boolean encendido) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(colorBorde, 3, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Botón superior derecho "X" para desvincular el dispositivo
        JPanel panelCierre = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelCierre.setBackground(Color.WHITE);
        JButton btnEliminar = new JButton("X");
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 11));
        btnEliminar.setForeground(Color.LIGHT_GRAY);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setFocusPainted(false);
        panelCierre.add(btnEliminar);
        tarjeta.add(panelCierre);

        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblEstado = new JLabel(estado);
        lblEstado.setFont(new Font("Arial", Font.PLAIN, 14));
        lblEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblEstado.setForeground(Color.GRAY);

        JButton btnAccion = new JButton(encendido ? "Encendido" : "Apagado");
        btnAccion.setBackground(encendido ? COLOR_AHORRO : COLOR_ALERTA);
        btnAccion.setForeground(COLOR_TEXTO_BLANCO);
        btnAccion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAccion.setFocusPainted(false);

        btnAccion.addActionListener(e -> {
            if (btnAccion.getText().equals("Encendido")) {
                btnAccion.setText("Apagado");
                btnAccion.setBackground(COLOR_ALERTA);
            } else {
                btnAccion.setText("Encendido");
                btnAccion.setBackground(COLOR_AHORRO);
            }
        });

        // Eliminar tarjeta físicamente de la interfaz del Dashboard
        btnEliminar.addActionListener(e -> {
            panelTarjetas.remove(tarjeta);
            panelTarjetas.revalidate();
            panelTarjetas.repaint();
        });

        tarjeta.add(Box.createVerticalGlue());
        tarjeta.add(lblNombre);
        tarjeta.add(Box.createRigidArea(new Dimension(0, 10)));
        tarjeta.add(lblEstado);
        tarjeta.add(Box.createRigidArea(new Dimension(0, 15)));
        tarjeta.add(btnAccion);
        tarjeta.add(Box.createVerticalGlue());

        return tarjeta;
    }

    private JPanel crearPanelPlaceholder(String mensaje) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_FONDO);
        JLabel label = new JLabel(mensaje);
        label.setFont(new Font("Tahoma", Font.BOLD, 18));
        label.setForeground(COLOR_TEXTO_OSCURO);
        panel.add(label);
        return panel;
    }

    private JButton crearBotonMenu(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(new Color(154, 205, 50));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        boton.setFocusPainted(false);
        boton.setBorder(new LineBorder(Color.LIGHT_GRAY));
        return boton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ControladorEnergiaGUI().setVisible(true);
        });
    }
}
