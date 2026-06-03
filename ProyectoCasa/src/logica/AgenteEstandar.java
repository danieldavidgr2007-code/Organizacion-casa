// ==============================================================================
// Universidad Distrital Francisco José de Caldas
// Autores: Cristian Velosa (20252020066) y Daniel David Granados Rivera (20252020135)
// Fecha de Creación: 26/05/2026 | Modificación: 28/05/2026
// Descripción: Subclase para miembros de la casa con acceso limitado (Temática: Gekko).
// ==============================================================================
package logica;

// [CRITERIO_HERENCIA]: Segunda clase hija que extiende de Usuario.
public class AgenteEstandar extends Usuario {
    private static final long serialVersionUID = 1L;

    public AgenteEstandar(String nombre, String pass, String r1, String r2, String r3) {
        super(nombre, pass, r1, r2, r3);
    }

    // [CRITERIO_POLIMORFISMO]: Comportamiento específico para el usuario estándar.
    @Override
    public String obtenerRolYPermisos() {
        return "ROL: ESTANDAR (Gekko) - Acceso limitado: Lectura de tareas y registro de gastos propios.";
    }
}