package gui;

import javax.swing.*;
import java.awt.*;
import logica.GestorUsuarios;
import logica.AgenteAdmin;
import logica.Usuario;
import excepciones.PasswordInvalidaException;

public class UIRegistro extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private GestorUsuarios gestor;
    
    private Color lila = new Color(230, 216, 248);
    private Color morado = new Color(180, 150, 230);

    public UIRegistro(GestorUsuarios gestor, boolean esRecuperacion) {
        this.gestor = gestor;
        
        setTitle(esRecuperacion ? "Recuperar Cuenta - Clove" : "Registro de Usuario Maestro - Clove");
        setSize(450, 600); // Ventana más grande para que quepan todos los campos
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(lila);
        setLayout(null);

        JLabel lblTitulo = new JLabel(esRecuperacion ? "RESTABLECER CONTRASEÑA" : "CREAR USUARIO MAESTRO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setBounds(10, 20, 414, 30);
        add(lblTitulo);

        if (!esRecuperacion) {
            // ==========================================
            // MODO: CREACIÓN DE USUARIO Y PREGUNTAS
            // ==========================================
            JLabel lblU = new JLabel("Nombre de Usuario:");
            lblU.setBounds(40, 70, 350, 20); add(lblU);
            JTextField txtUser = new JTextField();
            txtUser.setBounds(40, 95, 350, 30); add(txtUser);

            JLabel lblP = new JLabel("Contraseña (Mayús, minús, Núm, Especial):");
            lblP.setBounds(40, 140, 350, 20); add(lblP);
            JPasswordField txtPass = new JPasswordField();
            txtPass.setBounds(40, 165, 350, 30); add(txtPass);

            // AQUÍ ESTÁN LAS PREGUNTAS ANTES DE CREAR EL USUARIO
            JLabel lblR1 = new JLabel("Seguridad 1: ¿Cuál es tu color favorito?");
            lblR1.setBounds(40, 210, 350, 20); add(lblR1);
            JTextField txtR1 = new JTextField();
            txtR1.setBounds(40, 235, 350, 30); add(txtR1);

            JLabel lblR2 = new JLabel("Seguridad 2: ¿Nombre de tu primera mascota?");
            lblR2.setBounds(40, 280, 350, 20); add(lblR2);
            JTextField txtR2 = new JTextField();
            txtR2.setBounds(40, 305, 350, 30); add(txtR2);

            JButton btnGuardar = new JButton("FINALIZAR Y CREAR ADMIN");
            btnGuardar.setBackground(morado);
            btnGuardar.setForeground(Color.WHITE);
            btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 16));
            btnGuardar.setBounds(40, 380, 350, 50);
            btnGuardar.setFocusPainted(false);
            add(btnGuardar);

            btnGuardar.addActionListener(e -> {
                String u = txtUser.getText().trim();
                String p = new String(txtPass.getPassword());
                String r1 = txtR1.getText().trim();
                String r2 = txtR2.getText().trim();

                if (u.isEmpty() || p.isEmpty() || r1.isEmpty() || r2.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No puedes continuar. Debes llenar el usuario, clave y preguntas de seguridad.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    Usuario admin = new AgenteAdmin(u, p, r1, r2, "no_definido");
                    gestor.registrarUsuario(admin);
                    
                    JOptionPane.showMessageDialog(this, "¡Usuario Administrador creado exitosamente!");
                    dispose();
                    new UILogin(gestor).setVisible(true);
                } catch (PasswordInvalidaException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Seguridad", JOptionPane.ERROR_MESSAGE);
                }
            });

        } else {
            // ==========================================
            // MODO: RECUPERACIÓN DE CONTRASEÑA
            // ==========================================
            JLabel lblUserRec = new JLabel("Digita tu Nombre de Usuario:");
            lblUserRec.setBounds(40, 80, 350, 20); add(lblUserRec);
            JTextField txtUserRec = new JTextField();
            txtUserRec.setBounds(40, 105, 350, 30); add(txtUserRec);

            JLabel lblR1 = new JLabel("Validación 1: ¿Color favorito?");
            lblR1.setBounds(40, 155, 350, 20); add(lblR1);
            JTextField txtR1 = new JTextField();
            txtR1.setBounds(40, 180, 350, 30); add(txtR1);

            JLabel lblR2 = new JLabel("Validación 2: ¿Nombre de tu mascota?");
            lblR2.setBounds(40, 230, 350, 20); add(lblR2);
            JTextField txtR2 = new JTextField();
            txtR2.setBounds(40, 255, 350, 30); add(txtR2);

            JButton btnValidar = new JButton("VERIFICAR RESPUESTAS");
            btnValidar.setBackground(morado);
            btnValidar.setForeground(Color.WHITE);
            btnValidar.setFont(new Font("Segoe UI", Font.BOLD, 16));
            btnValidar.setBounds(40, 330, 350, 50);
            btnValidar.setFocusPainted(false);
            add(btnValidar);

            btnValidar.addActionListener(e -> {
                try {
                    Usuario buscado = gestor.validarPreguntasSeguridad(txtUserRec.getText().trim(), txtR1.getText().trim(), txtR2.getText().trim(), "no_definido");
                    if (buscado != null) {
                        String nuevaClave = JOptionPane.showInputDialog(this, "Respuestas correctas. Ingresa tu nueva contraseña:");
                        if (nuevaClave != null && !nuevaClave.isEmpty()) {
                            gestor.actualizarPassword(buscado, nuevaClave);
                            JOptionPane.showMessageDialog(this, "¡Contraseña actualizada!");
                            dispose();
                            new UILogin(gestor).setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Las respuestas no coinciden con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Usuario no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }
}