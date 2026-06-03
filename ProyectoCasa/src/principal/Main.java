// ==============================================================================
// Universidad Distrital Francisco José de Caldas
// Autores: Cristian Velosa (20252020066) y Daniel David Granados Rivera (20252020135)
// Fecha de Creación: 03/06/2026 | Modificación: 03/06/2026
// Descripción: Entrada del programa. Valida el kernel y dispara el flujo.
// ==============================================================================
package principal;
import logica.GestorUsuarios;
import javax.swing.SwingUtilities;
// IMPORTANTE: Asegúrate de tener el paquete gui del repositorio importado
// import gui.Ventana;

public class Main {
    public static void main(String[] args) {
        System.out.println("[CONSOLE LOG] BOOT: Iniciando Aplicativo Organización de Casa...");
        
        // Bloque try-catch-finally principal
        try {
            GestorUsuarios gestor = new GestorUsuarios();
            
            SwingUtilities.invokeLater(() -> {
                if (gestor.hayUsuariosRegistrados()) {
                    System.out.println("[CONSOLE LOG] BOOT: Usuarios detectados. Lanzando UI de Login.");
                    // Lanza tu ventana de Login aquí
                    // new UILogin(gestor).setVisible(true);
                    
                    // SIMULACIÓN DE FLUJO EXITOSO (Conexión con GitHub):
                    // Cuando el UILogin autentique exitosamente, debes ejecutar:
                    // this.dispose();
                    // new gui.Ventana().setVisible(true);
                    
                } else {
                    System.out.println("[CONSOLE LOG] BOOT: Kernel vacío. Redirigiendo a Registro.");
                    // Lanza tu ventana de Registro aquí
                    // new UIRegistro(gestor).setVisible(true);
                }
            });
            
        } catch (Exception e) {
            System.out.println("[CONSOLE ERROR] BOOT: Fallo crítico en el arranque: " + e.getMessage());
        } finally {
            System.out.println("[CONSOLE LOG] FINALLY: Proceso de arranque de la máquina virtual completado.");
        }
    }
}