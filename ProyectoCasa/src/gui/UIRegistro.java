package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import logica.GestorUsuarios;

public class UIRegistro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNuevoUsuario;
    private JPasswordField txtNuevoPassword;
    private GestorUsuarios gestor;
    private boolean vieneDeLogin;

    // --- PALETA VALORANT ---
    private final Color FONDO_NEGRO = new Color(18, 18, 18);
    private final Color COLOR_CLOVE = new Color(254, 150, 180);  // Rosa/Lavanda Clove
    private final Color COLOR_REYNA = new Color(178, 62, 137);  // Magenta Reyna
    private final Color TEXTO_BLANCO = Color.WHITE;

    public UIRegistro(GestorUsuarios gestor, boolean vieneDeLogin) {
        this.gestor = gestor;
        this.vieneDeLogin = vieneDeLogin;

        setTitle("SmartHome - Crear Cuenta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 670, 550);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(FONDO_NEGRO);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblReg = new JLabel(vieneDeLogin ? "REGISTRAR NUEVO AGENTE" : "REGISTRO DE ADMINISTRADOR INICIAL");
        lblReg.setHorizontalAlignment(SwingConstants.CENTER);
        lblReg.setForeground(COLOR_CLOVE);
        lblReg.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblReg.setBounds(0, 40, 656, 40);
        contentPane.add(lblReg);

        JLabel lblUser = new JLabel("Defina su Usuario:");
        lblUser.setForeground(TEXTO_BLANCO);
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblUser.setBounds(123, 130, 200, 30);
        contentPane.add(lblUser);

        // CAMPO USUARIO (Borde Clove)
        txtNuevoUsuario = new JTextField();
        txtNuevoUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtNuevoUsuario.setBackground(new Color(30, 30, 30));
        txtNuevoUsuario.setForeground(TEXTO_BLANCO);
        txtNuevoUsuario.setCaretColor(TEXTO_BLANCO);
        txtNuevoUsuario.setBorder(BorderFactory.createLineBorder(COLOR_CLOVE, 2));
        txtNuevoUsuario.setBounds(123, 165, 420, 40);
        contentPane.add(txtNuevoUsuario);

        JLabel lblPass = new JLabel("Defina su Contraseña:");
        lblPass.setForeground(TEXTO_BLANCO);
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblPass.setBounds(123, 230, 200, 30);
        contentPane.add(lblPass);

        // CAMPO CONTRASEÑA (Borde Clove)
        txtNuevoPassword = new JPasswordField();
        txtNuevoPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtNuevoPassword.setBackground(new Color(30, 30, 30));
        txtNuevoPassword.setForeground(TEXTO_BLANCO);
        txtNuevoPassword.setCaretColor(TEXTO_BLANCO);
        txtNuevoPassword.setBorder(BorderFactory.createLineBorder(COLOR_CLOVE, 2));
        txtNuevoPassword.setBounds(123, 265, 420, 40);
        contentPane.add(txtNuevoPassword);

        // BOTÓN CREAR (Borde Reyna)
        JButton btnGuardar = new JButton("CREAR E INYECTAR PERFIL");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnGuardar.setBackground(FONDO_NEGRO);
        btnGuardar.setForeground(COLOR_REYNA);
        btnGuardar.setBorder(BorderFactory.createLineBorder(COLOR_REYNA, 2));
        btnGuardar.setBounds(123, 370, 420, 45);
        btnGuardar.setFocusPainted(false);
        contentPane.add(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = txtNuevoUsuario.getText().trim();
                String pass = String.valueOf(txtNuevoPassword.getPassword()).trim();

                if (user.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No sea flojo, meta datos en los campos.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (gestor.registrarUsuario(user, pass)) {
                    JOptionPane.showMessageDialog(null, "Usuario creado melamente.");
                    dispose();
                    new UILogin(gestor).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Ese tag ya existe, sea original, mi pez.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}