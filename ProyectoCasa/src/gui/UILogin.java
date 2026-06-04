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
import logica.Usuario;

public class UILogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private GestorUsuarios gestor;

    // --- PALETA DE COLORES VALORANT ---
    private final Color FONDO_NEGRO = new Color(18, 18, 18);
    private final Color COLOR_CHAMBER = new Color(212, 175, 55); // Dorado elegante
    private final Color COLOR_OMEN = new Color(74, 82, 138);     // Azul grisáceo/morado
    private final Color COLOR_REYNA = new Color(178, 62, 137);   // Magenta oscuro
    private final Color COLOR_NEON = new Color(0, 212, 255);     // Cyan eléctrico
    private final Color COLOR_GEKKO = new Color(165, 230, 40);   // Verde radioactivo
    private final Color TEXTO_BLANCO = Color.WHITE;

    public UILogin(GestorUsuarios gestor) {
        this.gestor = gestor;
        
        setTitle("SmartHome - Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 670, 600);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(FONDO_NEGRO);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // TÍTULO (Estilo Chamber)
        JLabel lblLogin = new JLabel("INICIAR SESIÓN");
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setForeground(COLOR_CHAMBER); 
        lblLogin.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblLogin.setBounds(0, 40, 656, 40);
        contentPane.add(lblLogin);

        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setForeground(TEXTO_BLANCO);
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblUser.setBounds(123, 120, 100, 30);
        contentPane.add(lblUser);

        // CAMPO DE TEXTO USUARIO (Estilo Omen)
        txtUsuario = new JTextField();
        txtUsuario.setBounds(123, 155, 420, 40);
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtUsuario.setBackground(new Color(30, 30, 30));
        txtUsuario.setForeground(TEXTO_BLANCO);
        txtUsuario.setCaretColor(TEXTO_BLANCO);
        txtUsuario.setBorder(BorderFactory.createLineBorder(COLOR_OMEN, 2));
        contentPane.add(txtUsuario);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setForeground(TEXTO_BLANCO);
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblPass.setBounds(123, 210, 100, 30);
        contentPane.add(lblPass);

        // CAMPO DE TEXTO CONTRASEÑA (Estilo Reyna)
        txtPassword = new JPasswordField();
        txtPassword.setBounds(123, 245, 420, 40);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtPassword.setBackground(new Color(30, 30, 30));
        txtPassword.setForeground(TEXTO_BLANCO);
        txtPassword.setCaretColor(TEXTO_BLANCO);
        txtPassword.setBorder(BorderFactory.createLineBorder(COLOR_REYNA, 2));
        contentPane.add(txtPassword);

        // BOTÓN INGRESAR (Estilo Gekko)
        JButton btnIngresar = new JButton("INGRESAR");
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnIngresar.setBackground(FONDO_NEGRO);
        btnIngresar.setForeground(COLOR_GEKKO);
        btnIngresar.setBorder(BorderFactory.createLineBorder(COLOR_GEKKO, 2));
        btnIngresar.setBounds(123, 330, 420, 45);
        btnIngresar.setFocusPainted(false);
        contentPane.add(btnIngresar);

        // BOTÓN REGISTRAR (Estilo Neon)
        JButton btnRegistrarNuevo = new JButton("REGISTRAR NUEVO USUARIO");
        btnRegistrarNuevo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegistrarNuevo.setBackground(FONDO_NEGRO);
        btnRegistrarNuevo.setForeground(COLOR_NEON);
        btnRegistrarNuevo.setBorder(BorderFactory.createLineBorder(COLOR_NEON, 2));
        btnRegistrarNuevo.setBounds(123, 390, 420, 45);
        btnRegistrarNuevo.setFocusPainted(false);
        contentPane.add(btnRegistrarNuevo);

        // BOTÓN RECUPERAR CONTRASEÑA (Estilo enlace sutil Chamber)
        JButton btnRecuperar = new JButton("¿Olvidó su contraseña? (Recuperar)");
        btnRecuperar.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        btnRecuperar.setBackground(FONDO_NEGRO);
        btnRecuperar.setForeground(COLOR_CHAMBER);
        btnRecuperar.setBorder(BorderFactory.createEmptyBorder()); 
        btnRecuperar.setBounds(123, 450, 420, 30);
        btnRecuperar.setFocusPainted(false);
        contentPane.add(btnRecuperar);

        // --- EVENTOS DE LOS BOTONES ---

        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = txtUsuario.getText().trim();
                String pass = String.valueOf(txtPassword.getPassword()).trim();
                
                if (gestor.autenticar(user, pass)) {
                    Usuario usuarioLogueado = gestor.obtenerUsuario(user);
                    dispose();
                    new PRINCIPAL(usuarioLogueado, gestor).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Póngase serio. Usuario o contraseña incorrectos.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnRegistrarNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new UIRegistro(gestor, true).setVisible(true);
            }
        });

        // LA BENDITA LÓGICA DE RECUPERAR CONTRASEÑA
        btnRecuperar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userRecuperar = JOptionPane.showInputDialog(null, 
                        "Ingrese su nombre de usuario para buscarle la contraseña:", 
                        "Recuperación del Sistema", 
                        JOptionPane.QUESTION_MESSAGE);
                
                if (userRecuperar != null && !userRecuperar.trim().isEmpty()) {
                    Usuario u = gestor.obtenerUsuario(userRecuperar.trim());
                    if (u != null) {
                        JOptionPane.showMessageDialog(null, 
                                "Anote pues, no sea memoria de teflón. Su contraseña es: " + u.getPassword(), 
                                "Recuperación Exitosa", 
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, 
                                "Ese usuario no existe en la base de datos, mi pez.", 
                                "Error", 
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}