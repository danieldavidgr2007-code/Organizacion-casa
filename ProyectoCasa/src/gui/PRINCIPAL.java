package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import logica.GestorUsuarios;
import logica.Usuario;

public class PRINCIPAL extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Usuario usuarioActivo;
    private GestorUsuarios gestor;

    // --- PALETA VALORANT MODIFICADA ---
    private final Color FONDO_NEGRO = new Color(18, 18, 18);
    private final Color PANEL_OSCURO = new Color(28, 28, 28);
    private final Color COLOR_NEON = new Color(0, 212, 255);     // Cyan eléctrico (Neon)
    private final Color COLOR_RAZE = new Color(241, 90, 41);     // Naranja (Raze)
    private final Color TEXTO_BLANCO = Color.WHITE;

    public PRINCIPAL(Usuario usuarioActivo, GestorUsuarios gestor) {
        this.usuarioActivo = usuarioActivo;
        this.gestor = gestor;

        setTitle("SmartHome Manager - Agente: " + usuarioActivo.getUsername());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 670, 600);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(FONDO_NEGRO);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // --- CONTENEDOR DEL ENCABEZADO (Previene la transposición de textos) ---
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(PANEL_OSCURO);
        panelHeader.setBounds(30, 30, 595, 110);
        panelHeader.setBorder(BorderFactory.createLineBorder(COLOR_NEON, 1));
        panelHeader.setLayout(null);
        contentPane.add(panelHeader);

        // Título Principal
        JLabel lblBiemvenidoASmart = new JLabel("BIENVENIDO A SMART HOME MANAGER");
        lblBiemvenidoASmart.setHorizontalAlignment(SwingConstants.CENTER);
        lblBiemvenidoASmart.setForeground(TEXTO_BLANCO);
        lblBiemvenidoASmart.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblBiemvenidoASmart.setBounds(10, 25, 575, 30);
        panelHeader.add(lblBiemvenidoASmart);
        
        // Banner de Sesión (Separado limpiamente abajo del título)
        JLabel lblBanner = new JLabel("AGENTE ACTIVO: " + usuarioActivo.getUsername().toUpperCase());
        lblBanner.setHorizontalAlignment(SwingConstants.CENTER);
        lblBanner.setForeground(COLOR_NEON);
        lblBanner.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblBanner.setBounds(10, 65, 575, 20);
        panelHeader.add(lblBanner);
        
        // --- BOTÓN EMPEZAR (Estilo Neon) ---
        JButton btnNewButton = new JButton("INICIAR SISTEMA");
        btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
        btnNewButton.setBackground(FONDO_NEGRO);
        btnNewButton.setForeground(COLOR_NEON);
        btnNewButton.setBorder(BorderFactory.createLineBorder(COLOR_NEON, 3));
        btnNewButton.setBounds(123, 210, 410, 120);
        btnNewButton.setFocusPainted(false);
        contentPane.add(btnNewButton);
        
        // --- BOTÓN SALIR (Estilo Raze) ---
        JButton btnNewButton_1 = new JButton("DESCONECTARSE");
        btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnNewButton_1.setBackground(FONDO_NEGRO);
        btnNewButton_1.setForeground(COLOR_RAZE);
        btnNewButton_1.setBorder(BorderFactory.createLineBorder(COLOR_RAZE, 2));
        btnNewButton_1.setBounds(123, 390, 410, 50);
        btnNewButton_1.setFocusPainted(false);
        contentPane.add(btnNewButton_1);

        // Acciones de los botones
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                // Exige que hayas pegado el ControladorEnergiaGUI.java con parámetros que te di antes
                new ControladorEnergiaGUI(usuarioActivo, gestor).setVisible(true);
            }
        });

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}