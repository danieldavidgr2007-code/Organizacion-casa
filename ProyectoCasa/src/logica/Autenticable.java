// ==============================================================================
// Universidad Distrital Francisco José de Caldas
// Autores: Cristian Velosa (20252020066) y Daniel David Granados Rivera (20252020135)
// Fecha de Creación: 23/05/2026 | Modificación: 23/05/2026
// Descripción: Interfaz del negocio para garantizar contratos de autenticación.
// ==============================================================================
package logica;

// [CRITERIO_INTERFACES]: Interfaz propia del negocio con métodos abstractos.
public interface Autenticable {
    boolean verificarCredenciales(String passIntento);
}