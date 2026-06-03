package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import logica.GestorUsuarios;

public class PRINCIPAL extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PRINCIPAL frame = new PRINCIPAL();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PRINCIPAL() {
        setTitle("SmartHome Manager - Universidad Distrital");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 670, 647);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblBiemvenidoASmart = new JLabel("BIENVENIDO A SMART HOME MANAGER");
        lblBiemvenidoASmart.setHorizontalAlignment(SwingConstants.CENTER);
        lblBiemvenidoASmart.setForeground(new Color(0, 0, 0));
        lblBiemvenidoASmart.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblBiemvenidoASmart.setBounds(0, 50, 656, 30);
        contentPane.add(lblBiemvenidoASmart);
        
        JLabel lblImgPequena_2 = new JLabel("");
        lblImgPequena_2.setOpaque(true);
        lblImgPequena_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblImgPequena_2.setBackground(new Color(154, 205, 50));
        lblImgPequena_2.setBounds(0, 0, 656, 150);
        contentPane.add(lblImgPequena_2);
        
        JButton btnNewButton = new JButton("EMPEZAR");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
        btnNewButton.setBackground(new Color(154, 205, 50));
        btnNewButton.setBounds(123, 212, 420, 150);
        btnNewButton.setFocusPainted(false);
        contentPane.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("SALIR");
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_1.setBackground(new Color(220, 53, 69));
        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setBounds(123, 400, 420, 50);
        btnNewButton_1.setFocusPainted(false);
        contentPane.add(btnNewButton_1);

        // CONTROL DE FLUJO DIRECTO CON TU GESTOR DE USUARIOS LOGICA
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                GestorUsuarios gestor = new GestorUsuarios();
                
                if (gestor.hayUsuariosRegistrados()) {
                    System.out.println("[CONSOLE LOG] Usuarios detectados en la colección. Abriendo Login.");
                    new UILogin(gestor).setVisible(true);
                } else {
                    System.out.println("[CONSOLE LOG] Colección vacía. Abriendo Registro de Administrador.");
                    new UIRegistro(gestor, false).setVisible(true);
                }
            }
        });

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("[CONSOLE LOG] Saliendo del aplicativo.");
                System.exit(0);
            }
        });
    }
}