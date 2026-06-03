package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

/**
 * Interfaz Gráfica Principal para el Controlador de Energía Doméstica.
 * Implementa principios de psicología del color y un diseño basado en paneles.
 */
public class ControladorEnergiaGUI extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Definición de la paleta de colores (Psicología del Color)
    private final Color COLOR_MENU = new Color(30, 58, 138);       // Azul Oscuro: Tecnología y confianza
    private final Color COLOR_FONDO = new Color(243, 244, 246);    // Gris Claro: Claridad y minimalismo
    private final Color COLOR_AHORRO = new Color(16, 185, 129);    // Verde: Ecología y acciones positivas
    private final Color COLOR_ALERTA = new Color(239, 68, 68);     // Rojo: Apagado y advertencias de consumo
    private final Color COLOR_TEXTO_BLANCO = Color.WHITE;
    private final Color COLOR_TEXTO_OSCURO = new Color(31, 41, 55);

    public ControladorEnergiaGUI() {
        // 1. Configuración básica de la ventana (Run y End del programa)
        setTitle("Controlador de Energía Doméstica");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en la pantalla
        getContentPane().setLayout(new BorderLayout());

        // 2. Inicializar componentes de la interfaz
        inicializarMenuLateral();
        inicializarPanelPrincipal();
    }

    /**
     * Crea el menú lateral de navegación.
     */
    private void inicializarMenuLateral() {
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(0, 128, 128));
        panelMenu.setPreferredSize(new Dimension(220, getHeight()));
        panelMenu.setLayout(new GridLayout(6, 1, 10, 10));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Título del menú
        JLabel lblTitulo = new JLabel("Smart Home", SwingConstants.CENTER);
        lblTitulo.setForeground(COLOR_TEXTO_BLANCO);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
        panelMenu.add(lblTitulo);

        // Botones de navegación
        panelMenu.add(crearBotonMenu("Dashboard"));
        panelMenu.add(crearBotonMenu("Dispositivos"));
        panelMenu.add(crearBotonMenu("Reglas y Horarios"));
        panelMenu.add(crearBotonMenu("Simulador de Ahorro"));

        // Añadir el menú al lado izquierdo de la ventana
        getContentPane().add(panelMenu, BorderLayout.WEST);
    }

    /**
     * Cree los botones del menú lateral con un estilo unificado.
     */
    private JButton crearBotonMenu(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(new Color(154, 205, 50));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        boton.setFocusPainted(false);
        boton.setBorder(new LineBorder(Color.LIGHT_GRAY));
        return boton;
    }

    /**
     * Crea el panel central donde se muestran los datos y controles.
     */
    private void inicializarPanelPrincipal() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(COLOR_FONDO);
        panelPrincipal.setLayout(new BorderLayout(20, 20));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Cabecera del panel principal
        JLabel lblBienvenida = new JLabel("Resumen de Consumo Actual");
        lblBienvenida.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblBienvenida.setForeground(COLOR_TEXTO_OSCURO);
        panelPrincipal.add(lblBienvenida, BorderLayout.NORTH);

        // Centro: Tarjetas de información de dispositivos
        JPanel panelTarjetas = new JPanel(new GridLayout(1, 3, 20, 20));
        panelTarjetas.setBackground(COLOR_FONDO);

        panelTarjetas.add(crearTarjetaDispositivo("Paneles Solares", "Generando: 1.5 kWh", COLOR_AHORRO, true));
        panelTarjetas.add(crearTarjetaDispositivo("Termostato", "Consumo: 2.0 kWh", COLOR_ALERTA, false));
        panelTarjetas.add(crearTarjetaDispositivo("Enchufe Sala", "Consumo: 0.1 kWh", COLOR_MENU, true));

        panelPrincipal.add(panelTarjetas, BorderLayout.CENTER);

        // Sur: Botón de acción general
        JButton btnApagarTodo = new JButton("Apagar Todos los Dispositivos (Modo Ahorro)");
        btnApagarTodo.setBackground(COLOR_ALERTA);
        btnApagarTodo.setForeground(COLOR_TEXTO_BLANCO);
        btnApagarTodo.setFont(new Font("Arial", Font.BOLD, 18));
        btnApagarTodo.setFocusPainted(false);
        
        // Evento del botón (Muestra de Resultados en consola/GUI)
        btnApagarTodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, 
                    "Regla de seguridad activada: Todos los dispositivos de alto consumo han sido apagados.", 
                    "Modo Ahorro Activado", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });

        panelPrincipal.add(btnApagarTodo, BorderLayout.SOUTH);

        // Añadir el panel principal al centro de la ventana
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
    }

    /**
     * Crea una tarjeta visual para representar el estado de un dispositivo.
     */
    private JPanel crearTarjetaDispositivo(String nombre, String estado, Color colorBorde, boolean encendido) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(colorBorde, 3, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

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

        // Añadir componentes a la tarjeta
        tarjeta.add(Box.createVerticalGlue());
        tarjeta.add(lblNombre);
        tarjeta.add(Box.createRigidArea(new Dimension(0, 10)));
        tarjeta.add(lblEstado);
        tarjeta.add(Box.createRigidArea(new Dimension(0, 15)));
        tarjeta.add(btnAccion);
        tarjeta.add(Box.createVerticalGlue());

        return tarjeta;
    }

    /**
     * Método principal para ejecutar el programa (Run del Programa).
     */
    public static void main(String[] args) {
        // Asegurar que la interfaz se ejecute en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ControladorEnergiaGUI ventana = new ControladorEnergiaGUI();
                ventana.setVisible(true);
            }
        });
    }
