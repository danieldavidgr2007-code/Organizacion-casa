// ==============================================================================
// Universidad Distrital Francisco José de Caldas
// Autores: Cristian Velosa (20252020066) y Daniel David Granados Rivera (20252020135)
// Fecha de Creación: 24/05/2026 | Modificación: 28/05/2026
// Descripción: Superclase abstracta base para la gestión de miembros del hogar.
// ==============================================================================
package logica;
import java.io.Serializable;

// [CRITERIO_CLASE_ABSTRACTA]: Clase abstracta con encapsulamiento estricto.
// [CRITERIO_INTERFACES]: Implementación de java.io.Serializable (2da interfaz).
public abstract class Usuario implements Serializable, Autenticable {
    private static final long serialVersionUID = 1L;
    
    private String nombreUsuario;
    private String contrasena;
    private String respSeguridad1; 
    private String respSeguridad2; 
    private String respSeguridad3; 

    public Usuario(String nombreUsuario, String contrasena, String r1, String r2, String r3) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.respSeguridad1 = r1;
        this.respSeguridad2 = r2;
        this.respSeguridad3 = r3;
    }

    // [CRITERIO_CLASE_ABSTRACTA]: Método abstracto a implementar en subclases.
    public abstract String obtenerRolYPermisos();

    // Polimorfismo en implementación de interfaz
    @Override
    public boolean verificarCredenciales(String passIntento) {
        return this.contrasena.equals(passIntento);
    }

    public String getNombreUsuario() { return nombreUsuario; }
    public String getContrasena() { return contrasena; }
    public String getRespSeguridad1() { return respSeguridad1; }
    public String getRespSeguridad2() { return respSeguridad2; }
    public String getRespSeguridad3() { return respSeguridad3; }
    
    public void setContrasena(String nuevaContrasena) { 
        this.contrasena = nuevaContrasena; 
        System.out.println("[CONSOLE LOG] Contraseña actualizada internamente para: " + this.nombreUsuario);
    }
}