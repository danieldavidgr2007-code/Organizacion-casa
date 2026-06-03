// ==============================================================================
// Universidad Distrital Francisco José de Caldas
// Autores: Cristian Velosa (20252020066) y Daniel David Granados Rivera (20252020135)
// Fecha de Creación: 03/06/2026 | Modificación: 03/06/2026
// Descripción: Entrada del programa. Valida el estado del kernel y dispara el flujo.
// ==============================================================================
package principal;
import logica.GestorUsuarios;
import javax.swing.SwingUtilities;
import gui.UILogin;
import gui.UIRegistro;

public class Main {
    public static void main(String[] args) {
        System.out.println("[CONSOLE LOG] BOOT: Iniciando Aplicativo Organización de Casa...");
        
        try {
            GestorUsuarios gestor = new GestorUsuarios();
            
            SwingUtilities.invokeLater(() -> {
                // Si hay usuarios, obligamos Login; si no, forzamos creación de Admin
                if (gestor.hayUsuariosRegistrados()) {
                    System.out.println("[CONSOLE LOG] BOOT: Usuarios detectados. Lanzando UI de Login.");
                    new UILogin(gestor).setVisible(true);
                } else {
                    System.out.println("[CONSOLE LOG] BOOT: Kernel vacío. Redirigiendo a Registro de Admin.");
                    // 'false' indica modo registro, no recuperación
                    new UIRegistro(gestor, false).setVisible(true);
                }
            });
            
        } catch (Exception e) {
            System.out.println("[CONSOLE ERROR] BOOT: Fallo crítico en el arranque: " + e.getMessage());
        } finally {
            System.out.println("[CONSOLE LOG] FINALLY: Proceso de arranque completado.");
        }
    }
}