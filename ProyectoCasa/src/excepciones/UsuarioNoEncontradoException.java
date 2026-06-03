// ==============================================================================
// Universidad Distrital Francisco José de Caldas
// Autores: Cristian Velosa (20252020066) y Daniel David Granados Rivera (20252020135)
// Fecha de Creación: 22/05/2026 | Modificación: 22/05/2026
// Descripción: Excepción personalizada para fallos en búsqueda de usuarios.
// ==============================================================================
package excepciones;

// [CRITERIO_EXCEPCIONES]: Segunda excepción propia heredando de Exception.
public class UsuarioNoEncontradoException extends Exception {
    public UsuarioNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}