// ==============================================================================
// Universidad Distrital Francisco José de Caldas
// Autores: Cristian Velosa (20252020066) y Daniel David Granados Rivera (20252020135)
// Fecha de Creación: 03/06/2026 | Modificación: 03/06/2026
// Descripción: Entrada del programa. Valida el estado del kernel y dispara el flujo.
// ==============================================================================
package gui;

import logica.GestorUsuarios;

public class Main {
    
    // ESTE ES EL ÚNICO, EL ABSOLUTO, EL VERDADERO MAIN DE TODO SU PROYECTO
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // 1. Arranca la lógica y carga los usuarios del archivo .dat
                    GestorUsuarios gestorInicial = new GestorUsuarios();
                    
                    // 2. Evaluamos el flujo sin rodeos
                    if (gestorInicial.hayUsuariosRegistrados()) {
                        System.out.println("[CONSOLE LOG] Usuarios detectados. Abriendo Login.");
                        // Abre el Login directo si ya hay cuentas creadas
                        new UILogin(gestorInicial).setVisible(true);
                    } else {
                        System.out.println("[CONSOLE LOG] Colección vacía. Abriendo Registro Inicial.");
                        // Si está vacío, lo obliga a registrar el Administrador primero
                        new UIRegistro(gestorInicial, false).setVisible(true);
                    }
                } catch (Exception e) {
                    System.out.println("[ERROR CRÍTICO EN EL MAIN]: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}