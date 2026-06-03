package gui;

import javax.swing.*;
import java.awt.*;
import logica.GestorUsuarios;
import logica.Usuario;
import excepciones.UsuarioNoEncontradoException;

public class UILogin extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private GestorUsuarios gestor;
    
    // Temática Gekko (Verdes y Amarillos)
    private Color fondoVerde = new Color(212, 240, 190);  
    private Color botonAmarillo = new Color(245, 225, 100); 
    private Color textoOscuro = new Color(40, 50, 40);

    public UILogin(GestorUsuarios gestor) {
        this.gestor = gestor;
        
        setTitle("Acceso al Sistema - Gekko Theme");
        setSize(380, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(fondoVerde);
        setLayout(null);

        JLabel lblBienvenida = new JLabel("INICIAR SESIÓN", SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        lblBienvenida.setForeground(textoOscuro);
        lblBienvenida.setBounds(10, 20, 344, 30);
        add(lblBienvenida);
        
        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setFont(new Font("Arial", Font.BOLD, 12));
        lblUser.setBounds(40, 80, 100, 20);
        add(lblUser);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(40, 105, 280, 30);
        add(txtUsuario);
        
        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setFont(new Font("Arial", Font.BOLD, 12));
        lblPass.setBounds(40, 145, 100, 20);
        add(lblPass);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(40, 170, 280, 30);
        add(txtPassword);

        JLabel lblError = new JLabel("Credenciales incorrectas o usuario no existe.", SwingConstants.CENTER);
        lblError.setForeground(Color.RED);
        lblError.setFont(new Font("Arial", Font.BOLD, 12));
        lblError.setBounds(10, 215, 344, 20);
        lblError.setVisible(false);
        add(lblError);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBackground(botonAmarillo);
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 14));
        btnIngresar.setBounds(40, 250, 280, 40);
        btnIngresar.setFocusPainted(false);
        add(btnIngresar);

        JButton btnOlvidar = new JButton("Olvidé mi contraseña");
        btnOlvidar.setFont(new Font("Arial", Font.ITALIC, 11));
        btnOlvidar.setContentAreaFilled(false);
        btnOlvidar.setBorderPainted(false);
        btnOlvidar.setBounds(40, 320, 280, 25);
        add(btnOlvidar);

        // LOGICA DE AUTENTICACION CON EL METODO EXACTO DE TU GESTOR
        btnIngresar.addActionListener(e -> {
            String user = txtUsuario.getText().trim();
            String pass = new String(txtPassword.getPassword());
            lblError.setVisible(false);

            try {
                // Se utiliza el método real buscarYAutenticar definido en GestorUsuarios.java
                Usuario usuarioLogueado = gestor.buscarYAutenticar(user, pass);
                
                if (usuarioLogueado != null) {
                    System.out.println("[CONSOLE LOG] " + usuarioLogueado.obtenerRolYPermisos());
                    this.dispose();
                    new ControladorEnergiaGUI().setVisible(true);
                } else {
                    lblError.setVisible(true);
                }
            } catch (UsuarioNoEncontradoException ex) {
                // Tu método lanza esta excepción si el usuario no existe
                lblError.setVisible(true);
            }
            this.repaint();
        });

        btnOlvidar.addActionListener(e -> {
            this.dispose();
            new UIRegistro(gestor, true).setVisible(true);
        });
    }
}