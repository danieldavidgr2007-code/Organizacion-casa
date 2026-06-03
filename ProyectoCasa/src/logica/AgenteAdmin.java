// ==============================================================================
// Universidad Distrital Francisco José de Caldas
// Autores: Cristian Velosa (20252020066) y Daniel David Granados Rivera (20252020135)
// Fecha de Creación: 26/05/2026 | Modificación: 28/05/2026
// Descripción: Subclase para usuarios con privilegios totales (Temática: Clove).
// ==============================================================================
package logica;

// [CRITERIO_HERENCIA]: Primera clase hija que extiende de Usuario.
public class AgenteAdmin extends Usuario {
    private static final long serialVersionUID = 1L;

    public AgenteAdmin(String nombre, String pass, String r1, String r2, String r3) {
        super(nombre, pass, r1, r2, r3);
    }

    // [CRITERIO_POLIMORFISMO]: Sobrescritura del comportamiento abstracto.
    @Override
    public String obtenerRolYPermisos() {
        return "ROL: ADMIN (Clove) - Acceso total: Eliminación de cuentas, modificación de presupuestos globales.";
    }
}